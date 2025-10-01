package main.java.br.hidrometro.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * Classe responsável por ler e armazenar dados de configuração de hidrômetros
 * a partir de um arquivo de entrada.
 * <p>
 * Os dados incluem tempo de atualização do display, vazão de entrada e
 * proporção de ar nos hidrômetros.
 *
 * <p><b>Didático:</b> Esta classe funciona como “olhos do programa” para ler
 * o arquivo de configuração. Ela interpreta cada linha e organiza os dados
 * em listas prontas para a criação dos hidrômetros.
 */
public class Input {

    /** Arquivo de entrada contendo os dados de configuração. */
    private File arquivo;

    /** Tempo de atualização do display em milissegundos. */
    private float tempoAtualizacao;

    /** Lista de vazões de entrada dos hidrômetros. */
    private ArrayList<Float> vazaoList = new ArrayList<Float>();

    /** Lista de proporção de ar em cada hidrômetro. */
    private ArrayList<Float> entradaArList = new ArrayList<Float>();

    /** Scanner usado para ler o arquivo de entrada. */
    private Scanner scanner;

    /**
     * Construtor que recebe o caminho do arquivo de entrada.
     *
     * @param caminho Caminho do arquivo de configuração.
     */
    public Input(String caminho) {
        this.arquivo = new File(caminho);
        try {
            this.scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lê os valores do arquivo de configuração e armazena nas listas internas.
     * <p>
     * Reconhece três tipos de chaves: <code>tempoAtualizacaoDisplay</code>,
     * <code>vazao</code> e <code>entradaAr</code>.
     */
    public void lerValores() {
        try {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (!(linha.isEmpty() || linha.charAt(0) == '#')) {
                    String[] input = linha.split("=");
                    String chave = input[0].trim();
                    String valor = input[1].trim();

                    switch (chave) {
                        case "tempoAtualizacaoDisplay":
                            this.setTempoAtualizacao(Float.parseFloat(valor));
                            break;
                        case "vazao":
                            this.adicionarValoresVazao(Float.parseFloat(valor));
                            break;
                        case "entradaAr":
                            this.adicionarValoresEntradaAr(Float.parseFloat(valor));
                            break;
                        default:
                            throw new IllegalArgumentException(
                                "Chave de configuração inválida: '" + chave + "'"
                            );
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Define o arquivo de entrada. */
    public void setArquivo(String caminho) {
        this.arquivo = new File(caminho);
    }

    /** Define o tempo de atualização do display. */
    private void setTempoAtualizacao(float tempo) {
        this.tempoAtualizacao = tempo;
    }

    /** Adiciona um valor de vazão à lista interna. */
    public void adicionarValoresVazao(Float vazao) {
        this.vazaoList.add(vazao);
    }

    /** Adiciona um valor de entrada de ar à lista interna. */
    public void adicionarValoresEntradaAr(float entradaAr) {
        this.entradaArList.add(entradaAr);
    }

    /** @return o tempo de atualização do display em milissegundos. */
    public float getTempoAtualizacao() {
        return this.tempoAtualizacao;
    }

    /** @return lista de vazões de entrada dos hidrômetros. */
    public ArrayList<Float> getVazaoList() {
        return this.vazaoList;
    }

    /** @return lista de entradas de ar dos hidrômetros. */
    public ArrayList<Float> getEntradaArList() {
        return this.entradaArList;
    }

    /**
     * Imprime no console os valores lidos do arquivo de entrada.
     * <p>
     * Mostra tempo de atualização, vazão e entrada de ar de cada hidrômetro.
     */
    public void imprimirValoresLidos() {
        System.out.println("=============================================");
        System.out.println("  DADOS DE ENTRADA DO CONSUMIDOR LIDOS   ");
        System.out.println("=============================================");     
        System.out.println("Arquivo de Origem: " + (arquivo != null ? arquivo.getName() : "Não definido"));
        System.out.println("Tempo de Atualização: " + tempoAtualizacao + " ms");
        System.out.println("---------------------------------------------");

        if (vazaoList == null || vazaoList.isEmpty() || entradaArList == null || entradaArList.isEmpty()) {
            System.out.println("Nenhum dado de vazão ou entrada de ar foi carregado.");
        } else {
            System.out.println("Registros de Medição:");
            System.out.printf("%-5s | %-15s | %-15s%n", "ID", "Vazão", "Entrada de Ar");
            System.out.println("------+-----------------+-----------------");

            for (int i = 0; i < vazaoList.size(); i++) {
                float vazao = vazaoList.get(i);
                Float entradaAr = (i < entradaArList.size()) ? entradaArList.get(i) : 0.0f;
                System.out.printf("%-5d | %-15.2f | %-15.2f%n", (i + 1), vazao, entradaAr);
            }
        }

        System.out.println("=============================================");
    }
}
