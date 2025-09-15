package main.java.br.hidrometro.hidrometro;

public class Saida {

    private float odometroVolume;
    private char posicaoRelogioLitros;
    private char posicaoRelogioDecilitros;

    public Saida(float odometroVolume, char posicaoRelogioLitros, char posicaoRelogioDecilitros) {
        this.odometroVolume = odometroVolume;
        this.posicaoRelogioLitros = posicaoRelogioLitros;
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    public float getOdometro() {
        return odometroVolume;
    }

    public char getPosicaoRelogioLitros() {
        return posicaoRelogioLitros;
    }

    public char getPosicaoRelogioDecilitros() {
        return posicaoRelogioDecilitros;
    }

    public void setOdometro(float odometroVolume) {
        this.odometroVolume = odometroVolume;
    }

    public void setPosicaoRelogioLitros (char posicaoRelogioLitros) {
        this.posicaoRelogioLitros = posicaoRelogioLitros;
    }

    public void setPosicaoRelogioDecilitros (char posicaoRelogioDecilitros) {
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    @Override
    public String toString() {
        return "Saida[" +
                "odometro=" + odometroVolume +
                ", posicaoRelogioLitros='" + posicaoRelogioLitros + '\'' +
                ", posicaoRelogioDecilitros='" + posicaoRelogioDecilitros + '\'' +
                ']';
    }
}