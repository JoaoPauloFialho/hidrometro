package main.java.br.hidrometro.utils;

import java.util.ArrayList;

/**
 * Classe utilitária para cálculos relacionados ao hidrômetro.
 * <p>
 * Todos os métodos são estáticos e fornecem funções para calcular o volume
 * de água medido pelo hidrômetro, levando em conta a vazão de entrada e a
 * presença de ar no cano.
 *
 * <p><b>Didático:</b> Imagine esta classe como a “matemática por trás do hidrômetro”:
 * ela calcula quanto volume passou considerando o tempo e a vazão.
 */
public final class Calculadora {

    /** Construtor privado para impedir instanciamento. */
    private Calculadora() {
    }

    /**
     * Calcula volumes em lote para várias entradas de hidrômetros.
     *
     * @param vazaoList Lista de vazões de entrada de cada hidrômetro.
     * @param entradaArList Lista de proporções de ar presente em cada hidrômetro.
     * @param tempo Tempo em milissegundos durante o qual o volume foi medido.
     * @return Lista de volumes calculados para cada hidrômetro.
     */
    public static ArrayList<Float> calcularVolumeEmLote(ArrayList<Float> vazaoList, ArrayList<Float> entradaArList, Float tempo) {
        ArrayList<Float> volumeLote = new ArrayList<Float>();
        int tamLista = vazaoList.size();

        for (int i = 0; i < tamLista; i++) {
            // Calcula o volume base em litros (tempo convertido de ms para s)
            float volumeCalculado = vazaoList.get(i) * (tempo / 1000);

            // Aplica fator de correção para a presença de ar
            volumeLote.add(volumeCalculado * (1 + entradaArList.get(i)));
        }

        return volumeLote;
    }

    /**
     * Calcula o volume de água para um único hidrômetro em um intervalo de tempo.
     *
     * @param vazaoDeEntrada Vazão de entrada do hidrômetro (L/s).
     * @param entradaDeAr Proporção de ar presente na medição.
     * @param tempo Tempo em milissegundos.
     * @return Volume calculado considerando a vazão, o tempo e o ar no cano.
     */
    public static Float calcularVolumePorTempo(Float vazaoDeEntrada, Float entradaDeAr, Float tempo) {
        // Calcula o volume base em litros
        float volumeCalculado = vazaoDeEntrada * (tempo / 1000);

        // Aplica correção para presença de ar
        volumeCalculado = volumeCalculado * (1 + entradaDeAr);

        return volumeCalculado;
    }
}

