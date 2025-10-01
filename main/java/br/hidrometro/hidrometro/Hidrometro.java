package main.java.br.hidrometro.hidrometro;

import main.java.br.hidrometro.enums.Ordem;
import main.java.br.hidrometro.utils.Calculadora;

/**
 * Representa um hidrômetro que mede e acumula o volume de água.
 * <p>
 * Esta classe implementa {@link Runnable}, permitindo que cada hidrômetro seja
 * executado em uma thread independente para simular a medição em tempo real.
 *
 * <p>O hidrômetro possui:
 * <ul>
 *   <li>Uma vazão de entrada (litros por segundo);</li>
 *   <li>Um parâmetro de entrada de ar (para simulação de imprecisões);</li>
 *   <li>Um volume acumulado;</li>
 *   <li>Um odômetro para volume total;</li>
 *   <li>Relógios para litros e decilitros.</li>
 * </ul>
 *
 * <p><b>Didático:</b> Pense no hidrômetro como um objeto que “bebe” a água
 * que passa por ele, soma essa quantidade e vai girando ponteiros e números
 * para mostrar quanto já acumulou. Como ele implementa <code>Runnable</code>,
 * pode rodar em paralelo em uma thread.
 */
public class Hidrometro implements Runnable {

    /** Valor máximo que o hidrômetro pode exibir antes de reiniciar. */
    private static float VALOR_MAXIMO_HIDROMETRO = 9999;

    /** Tempo de atualização (em milissegundos) entre medições do hidrômetro. */
    private static float TEMPO_DE_ATUALIZACAO_HIDROMETRO = 2000;

    /** Vazão de entrada em litros por segundo. */
    private float vazaoEntrada;

    /** Entrada de ar, simulando interferência na medição. */
    private float entradaAr;

    /** Volume total acumulado pelo hidrômetro. */
    private float volumeAcumulado;

    /** Odômetro responsável por registrar o volume total. */
    private Odometro odometro;

    /** Relógio que representa os litros (unidade inteira). */
    private Relogio relogioLitros;

    /** Relógio que representa os decilitros (1/10 de litro). */
    private Relogio relogioDecilitros;

    /**
     * Construtor do hidrômetro.
     *
     * @param vazao      Vazão de entrada (litros por segundo).
     * @param entradaAr  Quantidade de ar considerada na simulação.
     */
    public Hidrometro(float vazao, float entradaAr) {
        this.vazaoEntrada = vazao;
        this.entradaAr = entradaAr;
        this.volumeAcumulado = 0.0f;
        this.odometro = new Odometro(0);
        this.relogioLitros = new Relogio(0, Ordem.LITROS);
        this.relogioDecilitros = new Relogio(0, Ordem.DECILITROS);
    }

    /** @return o odômetro associado a este hidrômetro. */
    public Odometro getOdometro() {
        return odometro;
    }

    /** @return o relógio que exibe litros. */
    public Relogio getRelogioLitros() {
        return relogioLitros;
    }

    /** @return o relógio que exibe decilitros. */
    public Relogio getRelogioDecilitros() {
        return relogioDecilitros;
    }

    /**
     * Atualiza o volume acumulado do hidrômetro com um novo incremento.
     * Se ultrapassar {@link #VALOR_MAXIMO_HIDROMETRO}, o hidrômetro reinicia.
     *
     * @param novoVolume Incremento de volume a ser adicionado.
     */
    public void atualizaVolumeAcumulado(float novoVolume) {
        if (novoVolume > VALOR_MAXIMO_HIDROMETRO) {
            // Se ultrapassar o limite → reinicia do zero
            volumeAcumulado = 0;
            odometro.setVolume(volumeAcumulado);
            relogioLitros.setVolume(0);
            relogioDecilitros.setVolume(0);
        } else {
            // Caso contrário → acumula normalmente
            volumeAcumulado = volumeAcumulado + novoVolume;
        }

        // Atualiza os componentes de exibição
        odometro.atualiza(novoVolume);
        relogioLitros.atualiza(novoVolume);
        relogioDecilitros.atualiza(novoVolume);
    }

    /**
     * Método de execução da thread.
     * <p>
     * Atualiza o volume acumulado a cada {@link #TEMPO_DE_ATUALIZACAO_HIDROMETRO}
     * milissegundos, chamando a {@link Calculadora}.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep((long) TEMPO_DE_ATUALIZACAO_HIDROMETRO);

                // Calcula o volume no intervalo e atualiza
                atualizaVolumeAcumulado(
                    Calculadora.calcularVolumePorTempo(vazaoEntrada, entradaAr, TEMPO_DE_ATUALIZACAO_HIDROMETRO)
                );

                // Exibe no console o estado atual
                System.out.println(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Representação textual do hidrômetro.
     * 
     * @return String com a vazão de entrada e o volume acumulado atual.
     */
    @Override
    public String toString() {
        return "Hidrometro com vazão de entrada: " + vazaoEntrada
             + " agora tem volume acumulado de " + volumeAcumulado;
    }

    /** @return volume acumulado atual. */
    public float getVolumeAcumulado() {
        return volumeAcumulado;
    }

    /**
     * Define o volume acumulado (sobrescrevendo valor anterior).
     * 
     * @param volumeAcumulado Novo valor de volume acumulado.
     */
    public void setVolumeAcumulado(float volumeAcumulado) {
        this.volumeAcumulado = volumeAcumulado;
    }
}
