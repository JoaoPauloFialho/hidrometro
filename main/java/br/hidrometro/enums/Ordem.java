package main.java.br.hidrometro.enums;

/**
 * Enumeração que define as ordens de medida utilizadas no hidrômetro.
 * <p>
 * Cada constante representa uma escala diferente do mostrador:
 * <ul>
 *   <li>{@link #LITROS} - unidade base (1 litro);</li>
 *   <li>{@link #DECILITROS} - subdivisão de 10 partes por litro (0,1 litro cada).</li>
 * </ul>
 *
 * <p><b>Didático:</b> Imagine o hidrômetro como um relógio com ponteiros.
 * Um ponteiro gira marcando litros inteiros, e outro gira marcando décimos de litro.
 */
public enum Ordem {

    /** Representa a unidade de litros (1 litro). */
    LITROS(1),

    /** Representa a unidade de decilitros (0,1 litro). */
    DECILITROS(10);

    /**
     * Valor numérico associado à ordem de medida.
     * <ul>
     *   <li>LITROS → 1</li>
     *   <li>DECILITROS → 10</li>
     * </ul>
     */
    public final int valor;

    /**
     * Construtor privado da enumeração.
     *
     * @param valor Valor inteiro correspondente à ordem de medida.
     */
    private Ordem(int valor) {
        this.valor = valor;
    }
}
