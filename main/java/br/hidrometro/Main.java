package main.java.br.hidrometro;

import main.java.br.hidrometro.controller.Controller;

/**
 * Classe principal do sistema de simulação de hidrômetros.
 * <p>
 * Esta classe é responsável por:
 * <ul>
 *   <li>Inicializar o {@link Controller} com o arquivo de entrada;</li>
 *   <li>Executar o loop de simulação contínua;</li>
 *   <li>Gerar imagens periódicas representando os hidrômetros.</li>
 * </ul>
 *
 * <p><b>Didático:</b> Pense na classe Main como o “botão ligar” do programa.
 * Ela monta o Controller, inicia o sistema e mantém a simulação rodando sem parar.
 */
public class Main {

    /**
     * Método principal da aplicação.
     * 
     * @param args Argumentos de linha de comando (não utilizados neste programa).
     */
    public static void main(String[] args) {
        // Instancia o controlador, passando o caminho do arquivo de entrada (input.txt).
        // Esse arquivo contém os dados iniciais da simulação (vazões, entradas de ar, tempos etc).
        // Exemplo de caminho antigo comentado (outro ambiente de execução):
        // Controller controller = new Controller("//home//joao//Área de Trabalho//Faculdade//Padrões de Projeto//ProjetosKatyusco//hidrometroV1//input//input.txt");

        Controller controller = new Controller(
            "/home/katia/eclipse-workspace/HidrometroJoaoFialho/input/input.txt"
        );

        try {
            /*
             * Inicializa o sistema e os hidrômetros de acordo
             * com os dados contidos no arquivo input.txt.
             * Isso inclui criar os hidrômetros e iniciar as threads de simulação.
             */
            controller.inicializar();

            // Loop infinito: simula continuamente o funcionamento dos hidrômetros.
            while (true) {
                /*
                 * Gera imagens dos hidrômetros e salva na pasta "Medições".
                 * Cada imagem representa o estado atual dos odômetros e ponteiros.
                 */
                controller.simular();

                // Aguarda o tempo configurado no input.txt antes de gerar a próxima imagem.
                // Esse tempo pode ser em milissegundos, dependendo da configuração.
                Thread.sleep((long) controller.getTempoUpdateImagensHidrometros());
            }

        } catch (Exception e) {
            // Captura possíveis exceções durante a execução da simulação.
            e.printStackTrace();
        }
    }
}
