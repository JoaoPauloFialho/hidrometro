package main.java.br.hidrometro.hidrometro;

import main.java.br.hidrometro.enums.Ordem;

public class Relogio extends ComponenteDeExibicao{
   private Ordem ordem; 
   
    public Relogio(float volume, Ordem ordem) {
        this.volume = volume;
        this.ordem = ordem;
    }

    @Override
    public void atualiza(float valor){
        this.volume += valor;
    }

    public int getValorPonteiro(){
        int parteInteiraVolume = (int) this.volume;
        int valorPonteiro = 0;
        if (ordem == Ordem.LITROS){
            valorPonteiro = parteInteiraVolume % 10;
        } else {
            valorPonteiro = parteInteiraVolume / ordem.valor;
            valorPonteiro %= ordem.valor;
        }   
        return valorPonteiro;
    }

    @Override
    public String toString() {
        return "Relogio[" +
            "Ordem: " + ordem +
            ", Volume: " + String.format("%.2f", volume) +
            ", Ponteiro Aponta para: " + getValorPonteiro() +
            ']';
    }
}
