package main.java.br.hidrometro.enums;

public enum Ordem {
    LITROS(10),
    DECILITROS(100);

    public final int valor;

    private Ordem(int valor) {
        this.valor = valor;
    }
}