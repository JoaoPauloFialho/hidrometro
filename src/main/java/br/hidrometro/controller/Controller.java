package main.java.br.hidrometro.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
        BufferedImage imagemHidrometro = Render.gerarImagem(
            saidaAtual.getOdometro(),
            saidaAtual.getPosicaoRelogioLitros(), 
            saidaAtual.getPosicaoRelogioDecilitros()
        );

        System.out.println("==============================================");
        System.out.println("DIAGNÓSTICO ANTES DE RENDERIZAR:");
        System.out.println("  - Valor do Odômetro: " + saidaAtual.getOdometro());
        System.out.println("  - Posição Relógio Litros (char): '" + saidaAtual.getPosicaoRelogioLitros() + "'");
        System.out.println("  - Posição Relógio Decilitros (char): '" + saidaAtual.getPosicaoRelogioDecilitros() + "'");
        System.out.println("==============================================");

        try {
            File diretorio = new File(DIRETORIO_IMAGEM);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }
            File outputFile = new File(diretorio, nomeImagem);
            ImageIO.write(imagemHidrometro, "PNG", outputFile);
            System.out.println("Imagem do hidrômetro gerada com sucesso: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar a imagem: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Saida gerarSaida(Hidrometro hidrometro) {
        char charLitros = String.valueOf(hidrometro.getRelogioLitros().getValorPonteiro()).charAt(0);
        char charDecilitros = String.valueOf(hidrometro.getRelogioDecilitros().getValorPonteiro()).charAt(0);

        return new Saida(hidrometro.getOdometro().getVolume(), charLitros, charDecilitros);
    }
}
