package main.java.br.hidrometro.enums;

public enum Ordem {
    LITROS(1),
    DECILITROS(10);

    public final int valor;

    private Ordem(int valor) {
        this.valor = valor;
    }
}