package main.java.br.hidrometro.hidrometro;

public class Saida {

    private Odometro odometro;
    private int posicaoRelogioLitros;
    private int posicaoRelogioDecilitros;

    public Saida(Odometro odometro, int posicaoRelogioLitros, int posicaoRelogioDecilitros) {
        this.odometro = odometro;
        this.posicaoRelogioLitros = posicaoRelogioLitros;
        this.posicaoRelogioDecilitros = posicaoRelogioDecilitros;
    }

    public Odometro getOdometro() {
        return odometro;
    }

    public int getPosicaoRelogioLitros() {
        return posicaoRelogioLitros;
    }

    public int getPosicaoRelogioDecilitros() {
        return posicaoRelogioDecilitros;
    }

    public void setOdometro(Odometro odometro) {
        this.odometro = odometro;
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
                "odometro=" + odometro.getVolume() +
                ", posicaoRelogioLitros='" + posicaoRelogioLitros + '\'' +
                ", posicaoRelogioDecilitros='" + posicaoRelogioDecilitros + '\'' +
                ']';
    }
}