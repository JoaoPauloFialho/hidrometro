package main.java.br.hidrometro.controller;

import java.util.ArrayList;

import main.java.br.hidrometro.hidrometro.Hidrometro;
import main.java.br.hidrometro.hidrometro.HidrometroFactory;
import main.java.br.hidrometro.hidrometro.Saida;
import main.java.br.hidrometro.utils.Input;
import main.java.br.hidrometro.utils.Render;

/**
 * Classe responsável por controlar a simulação de hidrômetros.
 * <p>
 * Esta classe gerencia:
 * <ul>
 *   <li>Leitura dos dados de entrada (vazões, ar, tempo de atualização);</li>
 *   <li>Criação de hidrômetros via {@link HidrometroFactory};</li>
 *   <li>Inicialização das threads que simulam os hidrômetros;</li>
 *   <li>Geração de imagens representando o estado dos hidrômetros;</li>
 *   <li>Conversão dos dados internos de um {@link Hidrometro} em um objeto {@link Saida} para renderização.</li>
 * </ul>
 * 
 * <p><b>Didático:</b> Pense no Controller como o “cérebro” do sistema: ele organiza as entradas,
 * cria os hidrômetros, liga o motor da simulação e cuida da saída (imagens e dados).
 */
public class Controller {

    /** Hidrômetro principal (pode ser sobrescrito por uma lista de hidrômetros). */
    private Hidrometro hidrometro;

    /** Objeto responsável por ler os dados de entrada. */
    private Input input;

    /** Lista de hidrômetros criados pela fábrica. */
    private ArrayList<Hidrometro> hidrometros;

    /** Volumes lidos em lote durante a simulação. */
    private ArrayList<Float> volumesLidosLote = new ArrayList<Float>();

    /** Diretório onde as imagens simuladas serão salvas. */
    public static final String DIRETORIO_IMAGEM = "Medições_202211250019";

    /**
     * Construtor do controlador.
     *
     * @param caminho Caminho do arquivo de entrada contendo os dados da simulação.
     */
    public Controller(String caminho) {
        hidrometros = new ArrayList<Hidrometro>();
        input = new Input(caminho); // inicializa leitor de dados
    }

    /**
     * Inicializa a simulação:
     * <ol>
     *   <li>Lê os valores de entrada (vazão, entrada de ar, tempo);</li>
     *   <li>Cria os hidrômetros via {@link HidrometroFactory};</li>
     *   <li>Inicia cada hidrômetro em uma thread separada.</li>
     * </ol>
     * 
     * <p><b>Didático:</b> Aqui é como ligar a máquina: primeiro lê as instruções,
     * depois cria os hidrômetros e finalmente coloca cada um para rodar em paralelo.
     */
    public void inicializar() {
        input.lerValores();
        hidrometros = HidrometroFactory.criarHidrometros(input.getVazaoList(), input.getEntradaArList());
        inicializarHidrometros(hidrometros);
    }

    /**
     * Inicia os hidrômetros fornecidos, cada um rodando em sua própria thread.
     *
     * @param hidrometros2 Lista de hidrômetros a serem inicializados.
     */
    private void inicializarHidrometros(ArrayList<Hidrometro> hidrometros2) {
        for (int i = 0; i < hidrometros2.size(); i++) {
            Thread t1 = new Thread(hidrometros2.get(i));
            t1.start(); // cada hidrômetro é um Runnable
        }
        // TODO Auto-generated method stub (caso precise expandir)
    }

    /**
     * Simula o funcionamento dos hidrômetros e gera imagens JPEG
     * representando o estado atual de cada um.
     * 
     * <p><b>Didático:</b> É como tirar uma foto do painel de cada hidrômetro
     * para acompanhar a leitura ao longo do tempo.
     */
    public void simular() {
        for (int j = 0; j < hidrometros.size(); j++) {
            this.gerarImagem(this.gerarSaida(hidrometros.get(j)), j + ".jpeg");
        }
    }

    /**
     * Gera uma imagem a partir do estado atual de um hidrômetro.
     *
     * @param saidaAtual  Objeto contendo os dados atuais do hidrômetro.
     * @param nomeImagem  Nome do arquivo de saída da imagem.
     */
    public void gerarImagem(Saida saidaAtual, String nomeImagem) {
        Render.gerarImagem(
            saidaAtual.getOdometro(),
            saidaAtual.getPosicaoRelogioLitros(),
            saidaAtual.getPosicaoRelogioDecilitros(),
            nomeImagem,
            DIRETORIO_IMAGEM
        );
    }

    /**
     * Constrói um objeto {@link Saida} a partir do estado atual de um {@link Hidrometro}.
     *
     * @param hidrometro O hidrômetro a ser convertido.
     * @return Objeto Saida contendo os dados para renderização.
     */
    public Saida gerarSaida(Hidrometro hidrometro) {
        return new Saida(
            hidrometro.getOdometro().getVolume(),
            hidrometro.getRelogioLitros().getValorPonteiro(),
            hidrometro.getRelogioDecilitros().getValorPonteiro()
        );
    }

    /**
     * Obtém o tempo de atualização (em segundos ou milissegundos, dependendo do Input)
     * configurado para geração das imagens dos hidrômetros.
     *
     * @return Tempo de atualização definido no arquivo de entrada.
     */
    public float getTempoUpdateImagensHidrometros() {
        return input.getTempoAtualizacao();
    }
}
