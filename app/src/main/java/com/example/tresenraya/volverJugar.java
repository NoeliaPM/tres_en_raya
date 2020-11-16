package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class volverJugar extends AppCompatActivity {

    Button volverAjugar, terminar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogvolverjugar);




        System.out.println("Volver a jugar...");

        volverAjugar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                System.out.println("Volver a jugar boton...");
                intent = new Intent(vista.getContext(), segundaAct.class);
                startActivityForResult(intent,0);
            }
        });

        terminar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                intent = new Intent(vista.getContext(), actividad_historico.class);
                startActivityForResult(intent,0);
            }
        });
    }






}
