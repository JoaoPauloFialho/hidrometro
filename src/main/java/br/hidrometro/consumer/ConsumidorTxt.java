package main.java.br.hidrometro.consumer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsumidorTxt {
    private File arquivo;
    private float tempoAtualizacao;
    private ArrayList<Float> vazaoList = new ArrayList<Float>();
    private ArrayList<Float> entradaArList = new ArrayList<Float>();
    private Scanner scanner;
    
    public ConsumidorTxt(String caminho){
        this.arquivo = new File(caminho);
        try{
            this.scanner = new Scanner(arquivo);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void lerValores(){
        try{
            while(scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if(!(linha.isEmpty() || linha.charAt(0) == '#')){
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
                            throw new IllegalArgumentException("Chave de configuração inválida: '" + chave + "'");
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setArquivo(String caminho){
        this.arquivo = new File(caminho);
    }

    private void setTempoAtualizacao(float tempo) {
        this.tempoAtualizacao = tempo;
    }

    public void adicionarValoresVazao(Float vazao) {
        this.vazaoList.add(vazao);
    }

    public void adicionarValoresEntradaAr(float entradaAr) {
        this.entradaArList.add(entradaAr);
    }

    public float getTempoAtualizacao() {
        return this.tempoAtualizacao;
    }

    public ArrayList<Float> getVazaoList() {
        return this.vazaoList;
    }

    public ArrayList<Float> getEntradaArList() {
        return this.entradaArList;
    }

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
