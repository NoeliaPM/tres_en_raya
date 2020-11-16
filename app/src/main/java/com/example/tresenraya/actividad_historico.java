package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class actividad_historico extends AppCompatActivity {
    Button botonfin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_historico);
        String[]archivos=fileList();
        TextView tv=(TextView) findViewById(R.id.textView7);
        botonfin=(Button)findViewById(R.id.button_terminarFinal);
        if(existe(archivos,"historico.txt")){
            try{
                InputStreamReader archivo= new InputStreamReader(openFileInput("historico.txt"));
                BufferedReader br= new BufferedReader(archivo);
                String linea=br.readLine();
                String todo="";
                while(linea!=null){
                    todo=todo+linea+"\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                tv.setText(todo);
            }
            catch(IOException ie){
            }
        }
        botonfin.setOnClickListener(new View.OnClickListener(){
            public void onClick (View vista){
                finishAffinity();
            }
        });

    }
    public boolean existe(String[] archivos, String busqueda){
        for(int i=0; i<archivos.length;i++){
            if(busqueda.equals(archivos[i])) {
                return true;
            }
        }
        return false;
    }

}


