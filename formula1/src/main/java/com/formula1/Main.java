package com.formula1;

public class Main {

    public static void main(String[] args) {

        GestorCircuitos gestor = new GestorCircuitos();

        if (gestor.agregarCircuito()) {

            gestor.listarCircuitos();

            gestor.editarCircuito();

            gestor.listarCircuitos();
        }
    }
}