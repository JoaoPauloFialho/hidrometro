package main.java.br.hidrometro.controller;

import java.util.ArrayList;
import main.java.br.hidrometro.consumer.ConsumidorTxt;
import main.java.br.hidrometro.hidrometro.Hidrometro;
import main.java.br.hidrometro.hidrometro.Saida;
import main.java.br.hidrometro.utils.Calculadora;
import main.java.br.hidrometro.utils.Render;

public class Controller {
    private Hidrometro hidrometro;
    private ConsumidorTxt consumidorTxt;
    private ArrayList<Float> volumesLidosLote = new ArrayList<Float>();
    public static final String DIRETORIO_IMAGEM = "Medições_202211250019";

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
        int cont = 1;
        for (Float valor : this.volumesLidosLote) {
            if(hidrometro.atualizaComponentes(valor)){
                this.gerarImagem(this.gerarSaida(hidrometro), String.valueOf(cont)+".jpeg");
                cont++;
            };
        }
    }

    public void gerarImagem(Saida saidaAtual, String nomeImagem){
        Render.gerarImagem(
            saidaAtual.getOdometro(),
            saidaAtual.getPosicaoRelogioLitros(), 
            saidaAtual.getPosicaoRelogioDecilitros(),
            nomeImagem,
            DIRETORIO_IMAGEM
        );
    }

    public Saida gerarSaida(Hidrometro hidrometro) {

        return new Saida(
            hidrometro.getOdometro().getVolume(), 
            hidrometro.getRelogioLitros().getValorPonteiro(), 
            hidrometro.getRelogioDecilitros().getValorPonteiro()
            );
    }
}
