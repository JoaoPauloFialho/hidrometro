package main.java.br.hidrometro.hidrometro;

public class Odometro extends ComponenteDeExibicao {

    public Odometro(float volume) {
        this.volume = volume;
    }

    @Override
    public void atualiza(float valor){
        this.volume += valor;
    }

    @Override
    public String toString() {
        return "Odometro[" +
            "Volume Total: " + String.format("%.2f", volume) + " Litros" +
            ']';
    }
}
