package main.java.br.hidrometro.hidrometro;

import java.util.ArrayList;

/**
 * Fábrica responsável por criar objetos {@link Hidrometro}.
 * <p>
 * Esta classe segue o padrão de projeto **Factory**, centralizando a criação
 * de hidrômetros a partir de listas de vazão e entrada de ar.
 *
 * <p><b>Didático:</b> A ideia é evitar que a criação de hidrômetros fique
 * espalhada pelo código. Você passa os parâmetros, e a fábrica retorna
 * uma lista pronta de hidrômetros, cada um configurado com seus valores.
 */
public class HidrometroFactory {

    /**
     * Cria uma lista de hidrômetros com base nas listas de vazão e entrada de ar.
     *
     * @param vazaoList Lista de vazões de entrada para cada hidrômetro.
     * @param entradaArList Lista de entradas de ar correspondentes.
     * @return Lista de objetos {@link Hidrometro} configurados.
     */
    public static ArrayList<Hidrometro> criarHidrometros(ArrayList<Float> vazaoList, ArrayList<Float> entradaArList) {

        ArrayList<Hidrometro> listaDeHidrometros = new ArrayList<Hidrometro>();

        // Para cada par de valores (vazão, entrada de ar), cria um hidrômetro
        for (int i = 0; i < vazaoList.size(); i++) {
            Hidrometro hidrometro = new Hidrometro(vazaoList.get(i), entradaArList.get(i));
            listaDeHidrometros.add(hidrometro);
        }

        return listaDeHidrometros;
    }
}
