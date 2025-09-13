package main.java.br.hidrometro.controller;

import java.util.ArrayList;

import main.java.br.hidrometro.consumer.ConsumidorTxt;
import main.java.br.hidrometro.hidrometro.Hidrometro;
import main.java.br.hidrometro.hidrometro.Saida;
import main.java.br.hidrometro.utils.Calculadora;

public class Controller {
    private float volume;
    private Hidrometro hidrometro;
    private ConsumidorTxt consumidorTxt;

    public Controller(String caminho){
        consumidorTxt = new ConsumidorTxt(caminho);
    }

    public void inicializar(){
        consumidorTxt.lerValores();
        ArrayList<Float> volumeCalculadoLote = Calculadora.calcularVolumeEmLote(consumidorTxt.getVazaoList(), consumidorTxt.getEntradaArList(), consumidorTxt.getTempoAtualizacao());
        consumidorTxt.imprimirValoresLidos();
    }

    public void simular(){
    }

    public void renderiza(){
    }

    public Saida gerarSaida(){
        return new Saida();
    }
}
