package com.formula1;

import javax.swing.JOptionPane;

public class ModuloPilotosEquipos {

    public static void main(String[] args) {
        GestorEquipos gestorEquipos = new GestorEquipos();
        GestorPilotos gestorPilotos = new GestorPilotos(gestorEquipos);
        GestorVehiculos gestorVehiculos = new GestorVehiculos();
        while (true) {
            String opcion = JOptionPane.showInputDialog("Módulo de equipos y pilotos:\n\n1. Administrador\n2. Usuario\n3. Salir");
            if (opcion == null || opcion.equals("3")) return;
            if (opcion.equals("1")) mostrarMenuAdministrador(gestorEquipos, gestorPilotos);
            else if (opcion.equals("2")) mostrarMenuUsuario(gestorEquipos, gestorPilotos, gestorVehiculos);
            else JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }

    private static void mostrarMenuAdministrador(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos) {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menú de administrador:\n\n1. Gestionar equipos\n2. Gestionar pilotos\n3. Volver");
            if (opcion == null || opcion.equals("3")) return;
            if (opcion.equals("1")) mostrarMenuEquipos(gestorEquipos, gestorPilotos);
            else if (opcion.equals("2")) mostrarMenuPilotos(gestorPilotos);
            else JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }

    private static void mostrarMenuUsuario(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos, GestorVehiculos gestorVehiculos) {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menú de usuario:\n\n1. Listar equipos\n2. Buscar equipo\n3. Listar pilotos\n4. Buscar piloto\n5. Consultar especificaciones de vehículos\n6. Volver");
            if (opcion == null || opcion.equals("6")) return;
            switch (opcion) {
                case "1" -> gestorEquipos.listarEquipos();
                case "2" -> gestorEquipos.buscarEquipo();
                case "3" -> gestorPilotos.listarPilotos();
                case "4" -> gestorPilotos.buscarPiloto();
                case "5" -> gestorVehiculos.mostrarEspecificacionesParaUsuario();
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void mostrarMenuEquipos(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos) {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Gestión de equipos:\n\n1. Registrar\n2. Listar\n3. Buscar\n4. Editar\n5. Eliminar\n6. Volver");
            if (opcion == null || opcion.equals("6")) return;
            switch (opcion) {
                case "1" -> {
                    gestorEquipos.registrarEquipo();
                    gestorPilotos.sincronizarPilotosConEquipos();
                }
                case "2" -> gestorEquipos.listarEquipos();
                case "3" -> gestorEquipos.buscarEquipo();
                case "4" -> gestorEquipos.editarEquipo();
                case "5" -> gestorEquipos.eliminarEquipo();
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static void mostrarMenuPilotos(GestorPilotos gestorPilotos) {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Gestión de pilotos:\n\n1. Registrar\n2. Listar\n3. Buscar\n4. Editar\n5. Eliminar\n6. Volver");
            if (opcion == null || opcion.equals("6")) return;
            switch (opcion) {
                case "1" -> gestorPilotos.registrarPiloto();
                case "2" -> gestorPilotos.listarPilotos();
                case "3" -> gestorPilotos.buscarPiloto();
                case "4" -> gestorPilotos.editarPiloto();
                case "5" -> gestorPilotos.eliminarPiloto();
                default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }
}
