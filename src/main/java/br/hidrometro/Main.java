package main.java.br.hidrometro;

import main.java.br.hidrometro.controller.Controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller("//home//joao//Área de Trabalho//Faculdade//Padrões de Projeto//ProjetosKatyusco//hidrometroV1//input//input.txt");
        try {
            controller.inicializar();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
