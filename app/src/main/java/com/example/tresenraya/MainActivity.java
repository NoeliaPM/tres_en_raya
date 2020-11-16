package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static int milisegundos = 30000;
    MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mediaPlayer = MediaPlayer.create(this, R.raw.wii);
        mediaPlayer.start();


        final ImageButton botonComenzar = (ImageButton)findViewById(R.id.botonComenzar);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

        //Botón para pasar a la siguiente actividad
                botonComenzar.setVisibility(View.VISIBLE); //Esto hace que el botón se haga visible
                mediaPlayer.stop();
                botonComenzar.setOnClickListener(new View.OnClickListener(){ //Poner a la escucha el botón para que pase a la siguente act
                    public void onClick (View vista){ //Ponemos botón a la escucha
                        Intent intent = new Intent(vista.getContext(),segundaAct.class); //Instanciamos la nueva actividad
                        startActivityForResult(intent,0); //Iniciamos la nueva actividad
                        finish();
                    }
                });

            }
        }, milisegundos);

    }
}




