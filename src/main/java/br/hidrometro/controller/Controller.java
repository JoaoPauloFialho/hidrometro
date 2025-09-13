package main.java.br.hidrometro.controller;

import java.util.ArrayList;

import main.java.br.hidrometro.consumer.ConsumidorTxt;
import main.java.br.hidrometro.hidrometro.Hidrometro;
import main.java.br.hidrometro.hidrometro.Saida;
import main.java.br.hidrometro.utils.Calculadora;

public class Controller {
    private Hidrometro hidrometro;
    private ConsumidorTxt consumidorTxt;
    private ArrayList<Float> volumesLidosLote = new ArrayList<Float>();

    public Controller(String caminho){
        consumidorTxt = new ConsumidorTxt(caminho);
        hidrometro = new Hidrometro();
    }

    public void inicializar(){
        consumidorTxt.lerValores();
        volumesLidosLote = Calculadora.calcularVolumeEmLote(consumidorTxt.getVazaoList(), consumidorTxt.getEntradaArList(), consumidorTxt.getTempoAtualizacao());
        consumidorTxt.imprimirValoresLidos();
    }
    
    public void simular(){
        for (Float valor : this.volumesLidosLote) {
            hidrometro.atualizaComponentes(valor);
        }
    }

    public void renderiza(){
    }

    public Saida gerarSaida(){
        return new Saida();
    }
}
