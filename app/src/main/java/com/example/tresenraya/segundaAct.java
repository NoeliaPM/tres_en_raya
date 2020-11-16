package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class segundaAct extends AppCompatActivity {
    Button jugarPrinci,botonjugador, botonCPU;
    AlertDialog.Builder builder1,builder2;
    AlertDialog alertDialog, alertDialog2;
    LayoutInflater li,li2;
    View v,v2;
    EditText etn1, etn2, ete1, ete2,etCPU;
    String nombre1, nombre2, edad1, edad2,eCPU;
    Intent intent;
    boolean cpu = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        //BOTONES ACTIVIDAD
        botonjugador=(Button)findViewById(R.id.botonrosa);
        botonCPU=(Button)findViewById(R.id.botonverde);
        jugarPrinci=(Button)findViewById(R.id.buttonJugar);



        //ON CLICK BOTÓN ROSA
        botonjugador.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                //ALERT DIALOG DE JUGADORVSJUGADOR
                builder1 = new AlertDialog.Builder(segundaAct.this);
                li = getLayoutInflater();
                v = li.inflate(R.layout.alertdialog, null);
                builder1.setView(v);
                alertDialog = builder1.create();
                //EDITTEXT ALERT DIALOG JUGADORVSJUFADOR
                etn1=(EditText) v.findViewById(R.id.editTextNombre1);
                etn2=(EditText) v.findViewById(R.id.editTextNombre2);
                ete1=(EditText) v.findViewById(R.id.editTextEdad1);
                ete2=(EditText) v.findViewById(R.id.editTextEdad2);

                //BOTÓN OK JUGADORVSJUGADOR
                builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        nombre1=etn1.getText().toString();//Cogemos valor del editText
                        nombre2=etn2.getText().toString();//Cogemos valor del editText
                        edad1=ete1.getText().toString();
                        edad2=ete2.getText().toString();
                        if(nombre1.isEmpty() || nombre2.isEmpty() || edad1.isEmpty() || edad2.isEmpty()){
                            Toast toast1 =Toast.makeText(getApplicationContext(), "Tienes que rellenar todos los campos", Toast.LENGTH_SHORT);
                            toast1.show();
                        }
                        else{
                            if (nombre1.equals(nombre2)){
                                Toast toast1 =Toast.makeText(getApplicationContext(), "Tienes que introducir dos nombres distintos. Por favor, vuelve a introducir los datos de los jugadores", Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                            else{
                                jugarPrinci.setEnabled(true);
                                botonjugador.setEnabled(false);
                                botonCPU.setEnabled(false);
                            }
                        }

                    }
                });
            builder1.setNegativeButton("Cancelar", null);
            builder1.setCancelable(false);
            AlertDialog dialog=builder1.create();
            dialog.show();
            }
        });

        //ON CLICK BOTÓN VERDE
        botonCPU.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                //ALERDIALOG DE JUGADORVSCPU
                builder2 = new AlertDialog.Builder(segundaAct.this);
                builder2.setNegativeButton("Cancelar", null);
                li2 = getLayoutInflater();
                v2 = li2.inflate(R.layout.alertdialogcpu, null);
                builder2.setView(v2);
                alertDialog2 = builder2.create();
                //EDITTEXT JUGADORVSCPU
                etCPU=(EditText) v2.findViewById(R.id.editTextedadCPU);
                cpu=true;


                //BOTÓN OK JUGADORVSCPU
                builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eCPU=etCPU.getText().toString();
                        jugarPrinci.setEnabled(true);
                        botonjugador.setEnabled(false);
                        botonCPU.setEnabled(false);
                    }
                });
                AlertDialog dialog2=builder2.create();
                dialog2.show();
            }
        });

        //ON CLICK BOTÓN JUGAR
        jugarPrinci.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                intent = new Intent(vista.getContext(), Juego_jugador.class);
                if(cpu){
                    System.out.println("Iniciando juego con cpu...");
                    intent.putExtra("n1","Humano");
                    intent.putExtra("n2","cpu");
                    intent.putExtra("e1",eCPU);
                    intent.putExtra("e2",edad2);
                    intent.putExtra("cpu",cpu);
                }else{
                    intent.putExtra("n1",nombre1);
                    intent.putExtra("n2",nombre2);
                    intent.putExtra("e1",edad1);
                    intent.putExtra("e2",edad2);
                }
                startActivityForResult(intent,0);
            }
        });
    }

    //MENÚ CON INFO
    @Override
    public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menuinfojuego, mimenu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem opcion){
        int id=opcion.getItemId();
        if(id==R.id.infoMenu) {
            abrirInfo(null);
            return true;
        }

        return super.onOptionsItemSelected(opcion);

    }
    public void abrirInfo(View v){
        Intent info=new Intent(this, Informacion.class);
        startActivity(info);
    }



}



