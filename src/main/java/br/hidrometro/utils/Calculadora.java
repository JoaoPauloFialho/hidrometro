package main.java.br.hidrometro.utils;

import java.util.ArrayList;

public final class Calculadora {

    private Calculadora() {
    }

    public static ArrayList<Float> calcularVolumeEmLote(ArrayList<Float> vazaoList, ArrayList<Float> entradaArList, Float tempo){
        ArrayList<Float> volumeLote =  new ArrayList<Float>();
        int tamLista = vazaoList.size();
        for (int i = 0; i < tamLista; i++){
            float volumeCalculado = vazaoList.get(i) * (tempo/1000); //conversão para segundos
            volumeLote.add(volumeCalculado * (1 + entradaArList.get(i))); //volume real levando em conta o ar no cano
        }

        return volumeLote;
    }
}
