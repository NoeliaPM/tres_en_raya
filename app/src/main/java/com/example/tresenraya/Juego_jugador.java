package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Juego_jugador extends AppCompatActivity {
    private int[]CASILLAS;
    private Partida partida;
    private int jugadores;
    TextView n,e;
    String nom1,nom2,ed1,ed2;
    LinearLayout ll;
    AlertDialog.Builder builder;
    AlertDialog alertDialog, alertDialog2;
    LayoutInflater li,li2;
    View v,v2;
    private boolean cpu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_vs_jugador);
        n=(TextView) findViewById(R.id.j1nombre);
        e=(TextView) findViewById(R.id.j1edad);

        //Cogemos el nombre y la edad de los jugadores
        Bundle b=getIntent().getExtras();
        nom1=b.getString("n1");
        nom2=b.getString("n2");
        ed1=b.getString("e1");
        ed2=b.getString("e2");
        cpu=b.getBoolean("cpu");

        ll = (LinearLayout) findViewById(R.id.juego);
        n.setText(nom1);
        e.setText(ed1);
        ll.setBackgroundResource(R.drawable.j2);

        //array para las posiciones de las casillas

        CASILLAS= new int [9];
        CASILLAS[0]=R.id.a1;
        CASILLAS[1]=R.id.a2;
        CASILLAS[2]=R.id.a3;
        CASILLAS[3]=R.id.b1;
        CASILLAS[4]=R.id.b2;
        CASILLAS[5]=R.id.b3;
        CASILLAS[6]=R.id.c1;
        CASILLAS[7]=R.id.c2;
        CASILLAS[8]=R.id.c3;

        partida=new Partida();

    }

    public void aJugar(View vista){

        ImageView imagen;
        for (int cadaCasilla:CASILLAS){
            imagen=(ImageView)findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.vacio);

        }
    }
    //hacemos que reciba una casilla por parámetro
    public void toque(View mivista) {
        int casilla = 0;
        for (int i = 0; i < 9; i++) {
            if (CASILLAS[i] == mivista.getId()) {

                casilla = i;
                break;
            }
        }

        //toast para ver si las casillas están bien numeradas

         /*  Toast toast = Toast.makeText(this, "Has pulsado la casilla " + casilla, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        */

         if(partida.comprueba_casilla(casilla)==false){
             return;
         }

        //nada más marcar, cambia de turno
         marca(casilla);

         //para almacenar el resultado del movimiento y comprobar si alguien gana
         int resultado=partida.turno();
         System.out.println("resultado: " + resultado);
         if (resultado>0){
             termina(resultado);
             return;
         }

        if(cpu){
            System.out.println("calculando cpu...");
            marca(partida.cpu());

            resultado = partida.turno();
            System.out.println("resultado cpu: " + resultado);
            if (resultado>0){
                termina(resultado);
                return;
            }
        }
    }

    private void termina (int resultado){
        String mensaje="";
        OutputStreamWriter archivo;
        try{
            archivo = new OutputStreamWriter(openFileOutput("historico.txt", Activity.MODE_APPEND));
            if(resultado==1){
                mensaje="Gana el jugador "+nom1;
                archivo.write("El jugador: "+ nom1+" con edad: "+ed1+" gana el juego.\n");
                archivo.write("El jugador: "+ nom2+" con edad: "+ed2+" pierde el juego.\n");
                archivo.flush();
                archivo.close();
            }
            else if(resultado==2){
                mensaje="Gana el jugador "+nom2;
                archivo.write("El jugador: "+ nom2+" con edad: "+ed2+" gana el juego.\n");
                archivo.write("El jugador: "+ nom1+" con edad: "+ed1+" pierde el juego.\n");
                archivo.flush();
                archivo.close();
            }
            else {
                mensaje="Empate";
                archivo.write("Los jugadores: "+nom1+" y "+nom2+" han empatado.\n");
                archivo.flush();
                archivo.close();
            }
        }
        catch(IOException ie){ }

        //AlertDialog volver a jugar
        builder = new AlertDialog.Builder(Juego_jugador.this);
        li = getLayoutInflater();
        v = li.inflate(R.layout.dialogvolverjugar, null);
        builder.setView(v);
        alertDialog = builder.create();

        TextView ganadorText =  v.findViewById(R.id.ganador);
        ganadorText.setText(mensaje);

        builder.setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                System.out.println("Volver a jugar boton...");
                Intent intent = new Intent(getBaseContext(), segundaAct.class);
                startActivityForResult(intent,0);
            }
        });
        builder.setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

               Intent intent = new Intent(getBaseContext(), actividad_historico.class);
               startActivityForResult(intent,0);
               finish(); //Para que no se pueda volver a esta pantalla
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();

        //partida=null;

    }

    //para saber si la imagen es un círculo o una cruz
    private void marca (int casilla){
        ImageView imagen;
        imagen=(ImageView)findViewById(CASILLAS[casilla]);

        if (partida.jugador==1){
            n.setText(nom2);
            e.setText(ed2);
            View view = this.getWindow().getDecorView();
            ll.setBackgroundResource(R.drawable.j1);
            imagen.setImageResource(R.drawable.circul);}

        else{
            n.setText(nom1);
            e.setText(ed1);
            View view = this.getWindow().getDecorView();
            ll.setBackgroundResource(R.drawable.j2);
            imagen.setImageResource((R.drawable.cruz));

        }

    }
    //Menu reglas del juego
    public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_reglas, mimenu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem opcion){
        int id=opcion.getItemId();
        if(id==R.id.reglasMenu) {
            abrirReglas(null);
            return true;
        }

        return super.onOptionsItemSelected(opcion);

    }
    public void abrirReglas(View v){
        Intent regla=new Intent(this, reglas_juego.class);
        startActivity(regla);
    }

}
