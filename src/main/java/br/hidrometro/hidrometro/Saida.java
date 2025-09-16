package main.java.br.hidrometro.hidrometro;

public class Saida {

    private float odometroVolume;
    private int posicaoRelogioLitros;
    private int posicaoRelogioDecilitros;

    public Saida(float odometroVolume, int posicaoRelogioLitros, int posicaoRelogioDecilitros) {
        this.odometroVolume = odometroVolume;
        this.posicaoRelogioLitros = posicaoRelogioLitros;
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    public float getOdometro() {
        return odometroVolume;
    }

    public int getPosicaoRelogioLitros() {
        return posicaoRelogioLitros;
    }

    public int getPosicaoRelogioDecilitros() {
        return posicaoRelogioDecilitros;
    }

    public void setOdometro(float odometroVolume) {
        this.odometroVolume = odometroVolume;
    }

    public void setPosicaoRelogioLitros (int posicaoRelogioLitros) {
        this.posicaoRelogioLitros = posicaoRelogioLitros;
    }

    public void setPosicaoRelogioDecilitros (int posicaoRelogioDecilitros) {
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