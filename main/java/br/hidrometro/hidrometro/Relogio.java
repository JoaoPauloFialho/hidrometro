package main.java.br.hidrometro.hidrometro;

import main.java.br.hidrometro.enums.Ordem;

/**
 * Representa um relógio do hidrômetro, responsável por exibir os valores de litros
 * ou decilitros de forma semelhante a um ponteiro em um mostrador.
 * <p>
 * Herda de {@link ComponenteDeExibicao} e utiliza a {@link Ordem} para determinar
 * a unidade que representa (litros ou decilitros).
 *
 * <p><b>Didático:</b> Imagine os ponteiros de um hidrômetro mecânico:
 * um ponteiro mostra litros inteiros, outro mostra décimos de litro (decilitros).
 */
public class Relogio extends ComponenteDeExibicao {

    /** Ordem de medição do relógio (LITROS ou DECILITROS). */
    private Ordem ordem; 

    /**
     * Construtor do relógio.
     *
     * @param volume Valor inicial do relógio.
     * @param ordem Ordem de medição (LITROS ou DECILITROS).
     */
    public Relogio(float volume, Ordem ordem) {
        this.volume = volume;
        this.ordem = ordem;
    }

    /**
     * Atualiza o volume do relógio adicionando o valor informado.
     *
     * @param valor Incremento de volume a ser adicionado.
     */
    @Override
    public void atualiza(float valor) {
        this.volume += valor;
    }

    /**
     * Calcula o valor que o ponteiro do relógio deve exibir com base na ordem.
     *
     * @return Valor do ponteiro (0-9 para LITROS ou valor proporcional para DECILITROS).
     */
    public int getValorPonteiro() {
        int parteInteiraVolume = (int) this.volume;
        int valorPonteiro = 0;

        if (ordem == Ordem.LITROS) {
            // Ponteiro de litros mostra apenas o último dígito da parte inteira
            valorPonteiro = parteInteiraVolume % 10;
        } else {
            // Ponteiro de decilitros calcula a posição proporcional
            valorPonteiro = parteInteiraVolume / ordem.valor;
            valorPonteiro %= ordem.valor;
        }   

        return valorPonteiro;
    }

    /**
     * Define o volume do relógio, sobrescrevendo o valor anterior.
     *
     * @param novoVolume Novo valor do volume.
     */
    public void setVolume(float novoVolume) {
        this.volume = novoVolume;
    }

    /**
     * Representação textual do relógio.
     *
     * @return String mostrando a ordem, o volume atual e o valor do ponteiro.
     */
    @Override
    public String toString() {
        return "Relogio[" +
            "Ordem: " + ordem +
            ", Volume: " + String.format("%.2f", volume) +
            ", Ponteiro Aponta para: " + getValorPonteiro() +
            ']';
    }
}
