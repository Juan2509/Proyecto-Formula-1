package com.formula1;

import javax.swing.JOptionPane;



public class Main {

    public static void main(String[] args) {
        GestorCircuitos gestorCircuitos = new GestorCircuitos();

        int opcion;

        do { 
            
            String entrada = JOptionPane.showInputDialog(null,
                " --- [ Simulador de Formula 1 ] --- \n\n 1. Gestión de circuitos\n 2. Gestión de pilotos y equipos\n 3. gestión de vehículos\n 4. Configuración de simulación\n 5. Iniciar simulación\n 6. Salir\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                break;
            }

            try {

                opcion = Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {

                case 1:

                    menuCircuitos(gestorCircuitos);
                    break;
                
                case 2:

                    JOptionPane.showMessageDialog(null, "Módulo de pilotos y equipos (Coming Soon!)");
                    break;

                case 3:

                    JOptionPane.showMessageDialog(null, "Modulo de vehículos (Coming Soon!)");
                    break;

                case 4:

                    JOptionPane.showMessageDialog(null, "Módulo de configuración (Coming Soon!)");
                    break;

                case 5:

                    JOptionPane.showMessageDialog(null, "Simulación (Coming Soon!)");
                    break;

                case 6:

                    break;

                default: JOptionPane.showMessageDialog(null, "Opción inválida");
                    break;

            }
        } while (true);
    }

    public static void menuCircuitos(GestorCircuitos gestorCircuitos) {

        int opcion;

        do {

            String entrada = JOptionPane.showInputDialog(null,
                "Gestión de Circuitos: \n\n 1. Agregar circuito\n 2. Listar circuitos\n 3. Buscar circuito\n 4. Editar circuito\n 5. Eliminar circuito\n 6. Volver al menú principal\n\n Seleccione una opción: "
            );

            if (entrada == null) {
                return;
            }

            try {
                
                opcion = Integer.parseInt(entrada);

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {

                case 1:
                    gestorCircuitos.agregarCircuito();
                    break;

                case 2:
                    gestorCircuitos.listarCircuitos();
                    break;

                case 3:
                    gestorCircuitos.buscarCircuito();
                    break;

                case 4:
                    gestorCircuitos.editarCircuito();
                    break;

                case 5:
                    gestorCircuitos.eliminarCircuitos();
                    break;

                case 6:

                return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");

            }
        } while (true);
    }
}