package main.java.br.hidrometro.hidrometro;

import main.java.br.hidrometro.enums.Ordem;

public class Hidrometro {
    private Odometro odometro;
    private Relogio relogioLitros;
    private Relogio relogioDecilitros;

    public Hidrometro() {
        this.odometro = new Odometro(0);
        this.relogioLitros = new Relogio(0, Ordem.LITROS);
        this.relogioDecilitros = new Relogio(0, Ordem.DECILITROS);
    }

    public boolean atualizaComponentes(float volume){
        this.odometro.atualiza(volume);
        this.relogioLitros.atualiza(volume);
        this.relogioDecilitros.atualiza(volume);
        if(volume == 0){
            return false;
        } else{
            return true;
        }
    }

    public Odometro getOdometro() {
        return odometro;
    }

    public Relogio getRelogioLitros() {
        return relogioLitros;
    }

    public Relogio getRelogioDecilitros() {
        return relogioDecilitros;
    }
}