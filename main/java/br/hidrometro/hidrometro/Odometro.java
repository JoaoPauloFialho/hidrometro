package main.java.br.hidrometro.hidrometro;

/**
 * Representa o odômetro do hidrômetro, que exibe o volume total acumulado.
 * <p>
 * Herda de {@link ComponenteDeExibicao}, implementando a atualização do
 * volume exibido conforme novas medições chegam.
 *
 * <p><b>Didático:</b> O odômetro funciona como o visor principal de um hidrômetro,
 * mostrando o volume total que passou, acumulando cada incremento.
 */
public class Odometro extends ComponenteDeExibicao {

    /**
     * Construtor do odômetro.
     *
     * @param volume Valor inicial do volume.
     */
    public Odometro(float volume) {
        this.volume = volume;
    }

    /**
     * Atualiza o volume do odômetro adicionando o valor informado.
     *
     * @param valor Incremento de volume a ser somado.
     */
    @Override
    public void atualiza(float valor) {
        this.volume += valor;
    }

    /**
     * Obtém o volume total atualmente exibido pelo odômetro.
     *
     * @return Volume acumulado.
     */
    public float getVolume() {
        return this.volume;
    }

    /**
     * Define o volume do odômetro, sobrescrevendo o valor anterior.
     *
     * @param novoVolume Novo valor do volume.
     */
    public void setVolume(float novoVolume) {
        this.volume = novoVolume;
    }

    /**
     * Representação textual do odômetro.
     *
     * @return String formatada mostrando o volume total em litros.
     */
    @Override
    public String toString() {
        return "Odometro[" +
            "Volume Total: " + String.format("%.2f", volume) + " Litros" +
            ']';
    }
}
