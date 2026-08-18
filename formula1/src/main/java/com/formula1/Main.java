package com.formula1;

import javax.swing.JOptionPane;



public class Main {

    public static void main(String[] args) {
        GestorCircuitos gestorCircuitos = new GestorCircuitos();
        GestorEquipos gestorEquipos = new GestorEquipos();
        GestorPilotos gestorPilotos = new GestorPilotos(gestorEquipos);
        GestorVehiculos gestorVehiculos = new GestorVehiculos();
        gestorVehiculos.setGestorPilotos(gestorPilotos);
        GestorConfiguraciones gestorConfiguraciones = new GestorConfiguraciones(gestorVehiculos);
        GestorHistorial gestorHistorial = new GestorHistorial();
        MotorSimulacion motorSimulacion = new MotorSimulacion(gestorPilotos, gestorVehiculos, gestorCircuitos, gestorConfiguraciones, gestorHistorial);

        int opcion;

        do { 
            
            String entrada = JOptionPane.showInputDialog(null,
                " --- [ Simulador de Formula 1 ] --- \n\n 1. Gestión de circuitos\n 2. Gestión de pilotos y equipos\n 3. Gestión de vehículos\n 4. Configuración de simulación\n 5. Iniciar simulación\n 6. Historial de resultados\n 7. Salir\n\n Seleccione una opción:"
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

                    menuPilotosEquipos(gestorEquipos, gestorPilotos, gestorVehiculos);
                    break;

                case 3:

                    menuVehiculos(gestorVehiculos);
                    break;

                case 4:

                    menuConfiguracion(gestorConfiguraciones);
                    break;

                case 5:

                    motorSimulacion.iniciarSimulacionClasificacion();
                    break;

                case 6:

                    menuHistorial(gestorHistorial);
                    break;

                case 7:

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
                "Gestión de Circuitos: \n\n 1. Agregar circuito\n 2. Listar circuitos\n 3. Buscar circuito\n 4. Editar circuito\n 5. Eliminar circuito\n 6. Registrar ganador histórico\n 7. Consultar ganadores históricos\n 8. Volver al menú principal\n\n Seleccione una opción: "
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
                    gestorCircuitos.registrarGanadorHistorico();
                    break;

                case 7:
                    gestorCircuitos.consultarGanadoresHistoricos();
                    break;

                case 8:

                return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");

            }
        } while (true);
    }

    public static void menuVehiculos(GestorVehiculos gestorVehiculos) {

        int opcion;

        do {

            String entrada = JOptionPane.showInputDialog(null,
                "Gestión de Vehículos: \n\n 1. Agregar vehículo\n 2. Listar vehículos\n 3. Buscar vehículo\n 4. Editar vehículo\n 5. Eliminar vehículo\n 6. Configurar rendimiento\n 7. Asignar piloto\n 8. Comparar vehículos\n 9. Volver al menú principal\n\n Seleccione una opción: "
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
                    gestorVehiculos.agregarVehiculo();
                    break;

                case 2:
                    gestorVehiculos.listarVehiculo();
                    break;

                case 3:
                    gestorVehiculos.buscarVehiculo();
                    break;

                case 4:
                    gestorVehiculos.editarVehiculo();
                    break;

                case 5:
                    gestorVehiculos.eliminarVehiculo();
                    break;

                case 6:
                    gestorVehiculos.gestionarRendimiento();
                    break;

                case 7:
                    gestorVehiculos.asignarPiloto();
                    break;

                case 8:
                    gestorVehiculos.compararVehiculos();
                    break;

                case 9:

                return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");

            }
        } while (true);
    }

    public static void menuPilotosEquipos(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos, GestorVehiculos gestorVehiculos) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                "Módulo de equipos y pilotos:\n\n 1. Administrador\n 2. Usuario\n 3. Volver al menú principal\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                return;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {
                case 1:
                    menuAdministrador(gestorEquipos, gestorPilotos);
                    break;

                case 2:
                    menuUsuario(gestorEquipos, gestorPilotos, gestorVehiculos);
                    break;

                case 3:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void menuAdministrador(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                "Menú de administrador:\n\n 1. Gestionar equipos\n 2. Gestionar pilotos\n 3. Volver\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                return;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {
                case 1:
                    menuEquipos(gestorEquipos, gestorPilotos);
                    break;

                case 2:
                    menuPilotos(gestorPilotos);
                    break;

                case 3:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void menuUsuario(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos, GestorVehiculos gestorVehiculos) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                "Menú de usuario:\n\n 1. Listar equipos\n 2. Buscar equipo\n 3. Listar pilotos\n 4. Buscar piloto\n 5. Consultar especificaciones de vehículos\n 6. Volver\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                return;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {
                case 1:
                    gestorEquipos.listarEquipos();
                    break;

                case 2:
                    gestorEquipos.buscarEquipo();
                    break;

                case 3:
                    gestorPilotos.listarPilotos();
                    break;

                case 4:
                    gestorPilotos.buscarPiloto();
                    break;

                case 5:
                    gestorVehiculos.mostrarEspecificacionesParaUsuario();
                    break;

                case 6:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void menuEquipos(GestorEquipos gestorEquipos, GestorPilotos gestorPilotos) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                "Gestión de equipos:\n\n 1. Registrar\n 2. Listar\n 3. Buscar\n 4. Editar\n 5. Eliminar\n 6. Volver\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                return;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {
                case 1:
                    gestorEquipos.registrarEquipo();
                    gestorPilotos.sincronizarPilotosConEquipos();
                    break;

                case 2:
                    gestorEquipos.listarEquipos();
                    break;

                case 3:
                    gestorEquipos.buscarEquipo();
                    break;

                case 4:
                    gestorEquipos.editarEquipo();
                    break;

                case 5:
                    gestorEquipos.eliminarEquipo();
                    break;

                case 6:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void menuPilotos(GestorPilotos gestorPilotos) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                "Gestión de pilotos:\n\n 1. Registrar\n 2. Listar\n 3. Buscar\n 4. Editar\n 5. Eliminar\n 6. Volver\n\n Seleccione una opción:"
            );

            if (entrada == null) {
                return;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La opción debe ser un número");
                continue;
            }

            switch (opcion) {
                case 1:
                    gestorPilotos.registrarPiloto();
                    break;

                case 2:
                    gestorPilotos.listarPilotos();
                    break;

                case 3:
                    gestorPilotos.buscarPiloto();
                    break;

                case 4:
                    gestorPilotos.editarPiloto();
                    break;

                case 5:
                    gestorPilotos.eliminarPiloto();
                    break;

                case 6:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    public static void menuConfiguracion(GestorConfiguraciones gestorConfiguraciones) {

        int opcion;

        do {

            String entrada = JOptionPane.showInputDialog(null,
                "Configuración de Vehículo:\n\n" +
                " 1. Crear configuración\n" +
                " 2. Listar configuraciones\n" +
                " 3. Consultar configuración\n" +
                " 4. Modificar configuración\n" +
                " 5. Eliminar configuración\n" +
                " 6. Volver al menú principal\n\n" +
                " Seleccione una opción:"
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
                    gestorConfiguraciones.crearConfiguracion();
                    break;

                case 2:
                    gestorConfiguraciones.listarConfiguraciones();
                    break;

                case 3:
                    gestorConfiguraciones.consultarConfiguracion();
                    break;

                case 4:
                    gestorConfiguraciones.modificarConfiguracion();
                    break;

                case 5:
                    gestorConfiguraciones.eliminarConfiguracion();
                    break;

                case 6:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (true);
    }

    public static void menuHistorial(GestorHistorial gestorHistorial) {

        int opcion;

        do {

            String entrada = JOptionPane.showInputDialog(null,
                "Historial de Resultados:\n\n" +
                " 1. Listar todas las sesiones\n" +
                " 2. Consultar sesión específica\n" +
                " 3. Consultar por circuito\n" +
                " 4. Comparar dos sesiones\n" +
                " 5. Ver estadísticas\n" +
                " 6. Volver al menú principal\n\n" +
                " Seleccione una opción:"
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
                    gestorHistorial.listarSesiones();
                    break;

                case 2:
                    gestorHistorial.consultarSesion();
                    break;

                case 3:
                    gestorHistorial.consultarPorCircuito();
                    break;

                case 4:
                    gestorHistorial.compararSesiones();
                    break;

                case 5:
                    gestorHistorial.mostrarEstadisticas();
                    break;

                case 6:
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (true);
    }
}
