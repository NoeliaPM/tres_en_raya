package com.example.tresenraya;

import java.util.Random;

public class Partida {
    public int jugador;
    private int [] casillas;
    private final int [][]COMBINACIONES ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //constructor
    public Partida(){
        jugador=1;
        //rellenar casillas
        casillas=new int [9];
        for(int i=0; i<9; i++){
            casillas[i]=0;
        }
    }

    //MÉTODO TURNO //PARA SABER QUIEN GANA
    public int turno(){

        boolean empate=true;
        boolean ult_movimiento=true;
        for (int i=0; i< COMBINACIONES.length; i++){
            for (int pos: COMBINACIONES[i]){
                if (casillas[pos]!=jugador)
                    ult_movimiento=false;
                if(casillas[pos]==0){
                    empate=false;
                }
            }

            //para saber quien ha hecho 3 en raya
            if (ult_movimiento)
                return jugador;
            ult_movimiento=true;
        }
        //si hay empate que devuelva un 3
        if (empate){
            return 3;
        }
        //para que no supere el num de jugadores
        jugador++;
        if(jugador>2){
            jugador=1;
        }
        return 0;
    }
    //para saber si se puede clickar o no
    public boolean comprueba_casilla(int casilla){

        if(casillas[casilla]!=0){
            return false;
        }else{
            casillas[casilla]=jugador;
        }
        return true;
    }
    //calcula la casilla en la que va a marcar la cpu
    public int cpu(){
        Random r = new Random();
        int casillaCpu = 0;
        do{
            casillaCpu = r.nextInt(9);
        }while(!comprueba_casilla(casillaCpu));
        System.out.println("CPU Elije: " + casillaCpu);
        return casillaCpu;
    }
}

