package main.java.br.hidrometro.hidrometro;

/**
 * Representa os dados de saída de um hidrômetro em um instante específico.
 * <p>
 * A classe encapsula as informações necessárias para gerar imagens ou relatórios
 * do estado atual do hidrômetro, incluindo:
 * <ul>
 *   <li>Volume total no odômetro;</li>
 *   <li>Posição do ponteiro de litros;</li>
 *   <li>Posição do ponteiro de decilitros.</li>
 * </ul>
 *
 * <p><b>Didático:</b> Pense nesta classe como uma “foto instantânea” do hidrômetro.
 * Ela pega os valores do odômetro e dos ponteiros e entrega prontos para exibição.
 */
public class Saida {

    /** Volume total exibido pelo odômetro. */
    private float odometroVolume;

    /** Posição do ponteiro que indica litros. */
    private int posicaoRelogioLitros;

    /** Posição do ponteiro que indica decilitros. */
    private int posicaoRelogioDecilitros;

    /**
     * Construtor da saída do hidrômetro.
     *
     * @param odometroVolume Volume total do odômetro.
     * @param posicaoRelogioLitros Posição do ponteiro de litros.
     * @param posicaoRelogioDecilitros Posição do ponteiro de decilitros.
     */
    public Saida(float odometroVolume, int posicaoRelogioLitros, int posicaoRelogioDecilitros) {
        this.odometroVolume = odometroVolume;
        this.posicaoRelogioLitros = posicaoRelogioLitros;
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    /** @return volume atual do odômetro. */
    public float getOdometro() {
        return odometroVolume;
    }

    /** @return posição do ponteiro de litros. */
    public int getPosicaoRelogioLitros() {
        return posicaoRelogioLitros;
    }

    /** @return posição do ponteiro de decilitros. */
    public int getPosicaoRelogioDecilitros() {
        return posicaoRelogioDecilitros;
    }

    /** Define o volume do odômetro. */
    public void setOdometro(float odometroVolume) {
        this.odometroVolume = odometroVolume;
    }

    /** Define a posição do ponteiro de litros. */
    public void setPosicaoRelogioLitros(int posicaoRelogioLitros) {
        this.posicaoRelogioLitros = posicaoRelogioLitros;
    }

    /** Define a posição do ponteiro de decilitros. */
    public void setPosicaoRelogioDecilitros(int posicaoRelogioDecilitros) {
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    /** Representação textual da saída do hidrômetro. */
    @Override
    public String toString() {
        return "Saida[" +
                "odometro=" + odometroVolume +
                ", posicaoRelogioLitros='" + posicaoRelogioLitros + '\'' +
                ", posicaoRelogioDecilitros='" + posicaoRelogioDecilitros + '\'' +
                ']';
    }
}
