package com.formula1;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class GestorVehiculos {
    
    private Map<String, Vehiculo> vehiculos;
    private GestorPilotos gestorPilotos;

    public GestorVehiculos() {
        vehiculos = new HashMap<>();
        cargarVehiculosPredefinidos();
    }

    public void setGestorPilotos(GestorPilotos gestorPilotos) {
        this.gestorPilotos = gestorPilotos;
    }

    private void cargarVehiculosPredefinidos() {
        registrarVehiculoPredefinido("Red Bull Racing", "RB22", "Red Bull Ford Powertrains (propio)", 358.0, 2.3, "1.9 / 2.1 / 2.4", "1.5 / 0.8 / 2.5");
        registrarVehiculoPredefinido("Ferrari", "SF-26", "Ferrari", 360.0, 2.3, "2.0 / 2.2 / 2.5", "1.6 / 0.9 / 2.6");
        registrarVehiculoPredefinido("McLaren", "MCL40", "Mercedes", 355.0, 2.4, "1.8 / 2.0 / 2.3", "1.4 / 0.7 / 2.3");
        registrarVehiculoPredefinido("Mercedes", "W17", "Mercedes", 352.0, 2.4, "1.9 / 2.1 / 2.4", "1.5 / 0.8 / 2.4");
        registrarVehiculoPredefinido("Aston Martin", "AMR26", "Honda", 348.0, 2.5, "2.1 / 2.3 / 2.6", "1.7 / 1.0 / 2.7");
        registrarVehiculoPredefinido("Williams", "FW48", "Mercedes", 350.0, 2.5, "1.7 / 1.9 / 2.2", "1.3 / 0.6 / 2.1");
        registrarVehiculoPredefinido("Alpine", "A526", "Mercedes (cliente)", 345.0, 2.6, "2.0 / 2.2 / 2.5", "1.6 / 0.9 / 2.5");
        registrarVehiculoPredefinido("Haas", "VF-26", "Ferrari", 347.0, 2.6, "1.9 / 2.1 / 2.4", "1.5 / 0.8 / 2.4");
        registrarVehiculoPredefinido("Audi", "R26", "Audi (propio)", 346.0, 2.6, "2.0 / 2.2 / 2.5", "1.6 / 0.9 / 2.5");
        registrarVehiculoPredefinido("Racing Bulls", "VCARB03", "Ford Red Bull Powertrains", 349.0, 2.5, "1.8 / 2.0 / 2.3", "1.4 / 0.7 / 2.3");
        registrarVehiculoPredefinido("Cadillac", "MAC-26", "Ferrari (cliente)", 344.0, 2.7, "2.1 / 2.3 / 2.6", "1.7 / 1.0 / 2.8");
    }

    private void registrarVehiculoPredefinido(String equipo, String modelo, String motor, Double velocidadMaxima, Double aceleracion, String consumoPorModo, String desgastePorModo) {
        Vehiculo vehiculo = new Vehiculo(
            equipo,
            modelo,
            motor,
            velocidadMaxima,
            aceleracion,
            new HashMap<>(),
            new HashMap<>()
        );
        vehiculo.getEspecificacionesTecnicas().put("Consumo por modo (Normal / Agresiva / Ahorro)", consumoPorModo);
        vehiculo.getEspecificacionesTecnicas().put("Desgaste por modo (Normal / Agresiva / Ahorro)", desgastePorModo);
        vehiculos.put(modelo, vehiculo);
    }

    public void mostrarEspecificacionesParaUsuario() {
        String[] ordenModelos = {"RB22", "SF-26", "MCL40", "W17", "AMR26", "FW48", "A526", "VF-26", "R26", "VCARB03", "MAC-26"};
        int vehiculosPorPagina = 3;
        int paginaActual = 0;
        int totalPaginas = (int) Math.ceil((double) ordenModelos.length / vehiculosPorPagina);

        while (true) {
            int inicio = paginaActual * vehiculosPorPagina;
            int fin = Math.min(inicio + vehiculosPorPagina, ordenModelos.length);
            StringBuilder mensaje = new StringBuilder("Especificaciones de vehículos 2026 - Página ")
                    .append(paginaActual + 1).append(" de ").append(totalPaginas).append("\n");
            mensaje.append("Valores por modo: Normal / Agresiva / Ahorro.\n\n");

            for (int indice = inicio; indice < fin; indice++) {
                Vehiculo vehiculo = vehiculos.get(ordenModelos[indice]);
                if (vehiculo == null) {
                    continue;
                }

                mensaje.append(vehiculo.getEquipo()).append(" - ").append(vehiculo.getModelo()).append("\n");
                mensaje.append("Motor: ").append(vehiculo.getMotor()).append("\n");
                mensaje.append("Velocidad máxima: ").append(formatearDato(vehiculo.getVelocidadMaxima(), "km/h")).append("\n");
                mensaje.append("Aceleración 0-100: ").append(formatearDato(vehiculo.getAceleracion(), "segundos")).append("\n");
                mensaje.append("Consumo por modo: ").append(vehiculo.getEspecificacionesTecnicas().get("Consumo por modo (Normal / Agresiva / Ahorro)")).append("\n");
                mensaje.append("Desgaste por modo: ").append(vehiculo.getEspecificacionesTecnicas().get("Desgaste por modo (Normal / Agresiva / Ahorro)")).append("\n");
                mensaje.append("--------------------------------\n");
            }

            mensaje.append("\n1. Siguiente página\n2. Anterior página\n3. Salir");
            String opcion = JOptionPane.showInputDialog(null, mensaje.toString());

            if (opcion == null || opcion.equals("3")) {
                return;
            }

            if (opcion.equals("1")) {
                if (paginaActual < totalPaginas - 1) {
                    paginaActual++;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya está en la última página.");
                }
            } else if (opcion.equals("2")) {
                if (paginaActual > 0) {
                    paginaActual--;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya está en la primera página.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public void agregarVehiculo() {
        
        String[][] opcionesVehiculos = {
            {"McLaren", "MCL40", "Mercedes"},
            {"Mercedes", "W17", "Mercedes"},
            {"Red Bull Racing", "RB22", "Red Bull Ford"},
            {"Ferrari", "SF-26", "Ferrari"},
            {"Williams", "FW48", "Mercedes"},
            {"Racing Bulls", "VCARB 03", "Red Bull Ford"},
            {"Aston Martin", "AMR26", "Honda"},
            {"Haas", "VF-26", "Ferrari"},
            {"Audi", "R26", "Audi"},
            {"Alpine", "A526", "Mercedes"},
            {"Cadillac", "MAC-26", "Ferrari"}
        };
        String[] opciones = new String[opcionesVehiculos.length + 1];

        for (int indice = 0; indice < opcionesVehiculos.length; indice++) {
            opciones[indice] = (indice + 1) + ". " + opcionesVehiculos[indice][0] + " - " + opcionesVehiculos[indice][1]
                + " (Motor: " + opcionesVehiculos[indice][2] + ")";
        }
        opciones[opcionesVehiculos.length] = (opcionesVehiculos.length + 1) + ". Agregar vehículo personalizado";

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione la escuderia y el monoplaza:",
            "Registrar vehiculo",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion == null) {
            return;
        }

        int indiceSeleccionado = -1;

        for (int indice = 0; indice < opciones.length; indice++) {
            if (opciones[indice].equals(seleccion)) {
                indiceSeleccionado = indice;
                break;
            }
        }

        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(null, "No se pudo identificar el vehiculo seleccionado");
            return;
        }

        String equipo;
        String modelo;
        String motor;

        // Si seleccionó la opción de vehículo personalizado
        if (indiceSeleccionado == opcionesVehiculos.length) {
            equipo = JOptionPane.showInputDialog("Ingrese el nombre del equipo:");
            if (equipo == null || equipo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El equipo no puede estar vacío");
                return;
            }

            modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo:");
            if (modelo == null || modelo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío");
                return;
            }

            if (vehiculos.containsKey(modelo)) {
                JOptionPane.showMessageDialog(null, "Ya existe un vehículo con ese modelo");
                return;
            }

            motor = JOptionPane.showInputDialog("Ingrese el motor del vehículo:");
            if (motor == null || motor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El motor no puede estar vacío");
                return;
            }
        } else {
            // Vehículo predefinido
            equipo = opcionesVehiculos[indiceSeleccionado][0];

            if (equipo == null || equipo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El equipo no puede estar vacío");
                return;
            }

            modelo = opcionesVehiculos[indiceSeleccionado][1];

            if (modelo == null || modelo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío");
                return;
            }

            if (vehiculos.containsKey(modelo)) {
                JOptionPane.showMessageDialog(null, "Ya existe un vehículo con ese modelo");
                return;
            }

            motor = opcionesVehiculos[indiceSeleccionado][2];

            if (motor == null || motor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El motor no puede estar vacío");
                return;
            }
        }

        String entradaVelocidad = JOptionPane.showInputDialog("Ingrese la velocidad máxima del vehículo en km/h: ");

        if (entradaVelocidad == null) {
            return;
        }

        double velocidadMaxima;

        try {
            velocidadMaxima = Double.parseDouble(entradaVelocidad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La velocidad máxima debe ser un número");
            return;
        }

        if (velocidadMaxima < 0) {
            JOptionPane.showMessageDialog(null, "La velocidad máxima no puede ser negativa");
            return;
        }

        String entradaAceleracion = JOptionPane.showInputDialog("Ingrese la aceleración de 0 a 100 km/h en segundos");

        if (entradaAceleracion == null) {
            return;
        }

        double aceleracion;

        try {
            
            aceleracion = Double.parseDouble(entradaAceleracion);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "La aceleración debe ser un número");
            return;

        }

        if (aceleracion < 0) {

            JOptionPane.showMessageDialog(null, "La aceleracion no puede ser negativa");
            return;
            
        }

        Map<Integer, String> pilotos = new HashMap<>();
        Map<String, Map<String, Double>> rendimiento = new HashMap<>();

        Vehiculo nuevoVehiculo = new Vehiculo(
            equipo,
            modelo,
            motor,
            velocidadMaxima,
            aceleracion,
            pilotos,
            rendimiento
        );

        vehiculos.put(modelo, nuevoVehiculo);

        JOptionPane.showMessageDialog(null, "Vehículo agregado correctamente");
    }

    public void listarVehiculo() {

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        // Convertir el mapa de vehículos a un array para poder paginar
        Vehiculo[] listaVehiculos = vehiculos.values().toArray(new Vehiculo[0]);
        int vehiculosPorPagina = 3;
        int paginaActual = 0;
        int totalPaginas = (int) Math.ceil((double) listaVehiculos.length / vehiculosPorPagina);

        while (true) {
            int inicio = paginaActual * vehiculosPorPagina;
            int fin = Math.min(inicio + vehiculosPorPagina, listaVehiculos.length);

            StringBuilder mensaje = new StringBuilder("Vehículos registrados - Página ")
                    .append(paginaActual + 1).append(" de ").append(totalPaginas).append("\n\n");

            for (int indice = inicio; indice < fin; indice++) {
                Vehiculo vehiculo = listaVehiculos[indice];
                mensaje.append("Equipo: ").append(vehiculo.getEquipo()).append("\n");
                mensaje.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
                mensaje.append("Motor: ").append(vehiculo.getMotor()).append("\n");
                mensaje.append("Velocidad máxima: ").append(formatearDato(vehiculo.getVelocidadMaxima(), "km/h")).append("\n");
                mensaje.append("Aceleración 0-100: ").append(formatearDato(vehiculo.getAceleracion(), "segundos")).append("\n");
                mensaje.append("Pilotos asignados: ").append(vehiculo.getPilotos().size()).append("\n");
                mensaje.append("-----------------------------\n");
            }

            mensaje.append("\n1. Siguiente página\n2. Anterior página\n3. Salir");

            String opcion = JOptionPane.showInputDialog(null, mensaje.toString());

            if (opcion == null || opcion.equals("3")) {
                return;
            }

            if (opcion.equals("1")) {
                if (paginaActual < totalPaginas - 1) {
                    paginaActual++;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya está en la última página.");
                }
            } else if (opcion.equals("2")) {
                if (paginaActual > 0) {
                    paginaActual--;
                } else {
                    JOptionPane.showMessageDialog(null, "Ya está en la primera página.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    public void buscarVehiculo() {

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo que desea consultar: ");

        if (modelo == null || modelo.trim().isEmpty()){
            return;
        }

        Vehiculo vehiculo = vehiculos.get(modelo);

        if (vehiculo == null) {
            
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        mostrarVehiculo(vehiculo);
    }

    private void mostrarVehiculo(Vehiculo vehiculo) {

        String mensaje = "Especificaciones del vehículo: \n\n";

        mensaje += "Equipo: " + vehiculo.getEquipo() + "\n";
        mensaje += "Modelo: " + vehiculo.getModelo() + "\n";
        mensaje += "Motor: " + vehiculo.getMotor() + "\n";
        mensaje += "Velocidad máxima: " + formatearDato(vehiculo.getVelocidadMaxima(), "km/h") + "\n";
        mensaje += "Aceleración 0-100: " + formatearDato(vehiculo.getAceleracion(), "segundos") + "\n\n";

        mensaje += "Pilotos asignados: \n";
        mensaje += obtenerResumenPilotos(vehiculo);

        mensaje += "\nRendimiento configurado: \n";
        mensaje += obtenerResumenRendimiento(vehiculo);

        mensaje += "\nEspecificaciones técnicas 2026:\n";
        mensaje += obtenerResumenEspecificaciones(vehiculo);

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private String obtenerResumenPilotos(Vehiculo vehiculo) {

        if (vehiculo.getPilotos().isEmpty()) {
            return "  Sin pilotos asignados\n";
        }

        String resumen = "";

        for (Map.Entry<Integer, String> entrada : vehiculo.getPilotos().entrySet()) {

            resumen += "  ID " + entrada.getKey() + ": " + entrada.getValue() + "\n";
        }

        return resumen;
    }

    private String obtenerResumenRendimiento(Vehiculo vehiculo) {

        if (vehiculo.getRendimiento().isEmpty()) {
            return "  Sin datos de rendimiento configurados\n";
        }

        String resumen = "";

        for (Map.Entry<String, Map<String, Double>> entrada : vehiculo.getRendimiento().entrySet()) {

            Map<String, Double> datos = entrada.getValue();

            resumen += "  " + entrada.getKey() + " -> ";
            resumen += "Vel. promedio: " + datos.get("velocidadPromedio") + " km/h, ";
            resumen += "Consumo: " + datos.get("consumoCombustible") + ", ";
            resumen += "Desgaste neumáticos: " + datos.get("desgasteNeumaticos") + "\n";
        }

        return resumen;
    }

    private String obtenerResumenEspecificaciones(Vehiculo vehiculo) {
        String resumen = "";

        for (Map.Entry<String, String> entrada : vehiculo.getEspecificacionesTecnicas().entrySet()) {
            resumen += "  " + entrada.getKey() + ": " + entrada.getValue() + "\n";
        }

        return resumen;
    }

    private String formatearDato(Double valor, String unidad) {
        if (valor == null) {
            return "No especificada";
        }

        return valor + " " + unidad;
    }

    public void editarVehiculo() {

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo que desea editar: ");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        Vehiculo vehiculo = vehiculos.get(modelo);

        if (vehiculo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese nombre");
            return;
        }

        String opcion = JOptionPane.showInputDialog("Que desea editar ? \n\n 1. Equipo\n 2. Modelo\n 3. Motor\n 4.Velocidad máxima\n 5. Aceleración");

        if (opcion == null) {
            return;
        }

        int seleccion;

        try {
            seleccion = Integer.parseInt(opcion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La opción debe ser un número");
            return;
        }

        switch (seleccion) {
            case 1:

                String nuevoEquipo = JOptionPane.showInputDialog("Ingrese el nuevo equipo: ");
                
                if (nuevoEquipo == null || nuevoEquipo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El equipo no puede estar vacío");
                    break;
                }

                vehiculo.setEquipo(nuevoEquipo);

                JOptionPane.showMessageDialog(null, "Equipo actualizado correctamente");
                break;

            case 2:

                String nuevoModelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo: ");

                if (nuevoModelo == null || nuevoModelo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío");
                    break;
                }

                if (vehiculos.containsKey(nuevoModelo)) {
                    JOptionPane.showMessageDialog(null, "Ya existe un vehículo con ese modelo");
                    break;
                }

                vehiculos.remove(modelo);

                vehiculo.setModelo(nuevoModelo);

                vehiculos.put(nuevoModelo, vehiculo);

                JOptionPane.showMessageDialog(null, "Modelo actualizado correctamente");

                break;

            case 3:

                String nuevoMotor = JOptionPane.showInputDialog("Ingrese el nuevo motor: ");

                if (nuevoMotor == null || nuevoMotor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El motor no puede estar vacío");
                    break;
                }

                vehiculo.setMotor(nuevoMotor);

                JOptionPane.showMessageDialog(null, "Motor actualizado correctamente");

                break;

            case 4:

                String entradaNuevaVelocidad = JOptionPane.showInputDialog("Ingrese la nueva velocidad máxima en km/h: ");

                if (entradaNuevaVelocidad == null) {
                    break;
                }

                double nuevaVelocidad;

                try {

                    nuevaVelocidad = Double.parseDouble(entradaNuevaVelocidad);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La velocidad máxima debe ser un número");
                    break;
                }

                if (nuevaVelocidad < 0) {
                    JOptionPane.showMessageDialog(null, "La velocidad máxima no puede ser negativa");
                    break;
                }

                vehiculo.setVelocidadMaxima(nuevaVelocidad);

                JOptionPane.showMessageDialog(null, "Velocidad máxima actualizada correctamente");

                break;

            case 5:

                String entradaNuevaAceleracion = JOptionPane.showInputDialog("Ingrese la nueva aceleración de 0 a 100 km/h en segundos: ");

                if (entradaNuevaAceleracion == null) {
                    break;
                }

                double nuevaAceleracion;

                try {
                    nuevaAceleracion = Double.parseDouble(entradaNuevaAceleracion);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La aceleración debe ser un número");
                    break;
                }

                if (nuevaAceleracion < 0) {
                    JOptionPane.showMessageDialog(null, "La aceleración no puede ser negativa");
                    break;
                }

                vehiculo.setAceleracion(nuevaAceleracion);

                JOptionPane.showMessageDialog(null, "Aceleración actualizada correctamente");

                break;
        
            default:

                JOptionPane.showMessageDialog(null, "Opción inválida");
                break;
        }
    }

    public void eliminarVehiculo() {

        if (vehiculos.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo que desea eliminar: ");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        if (!vehiculos.containsKey(modelo)) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar el vehículo " + modelo + "?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        vehiculos.remove(modelo);

        JOptionPane.showMessageDialog(null, "Vehículo eliminado correctamente");
    }

    public void gestionarRendimiento() {

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo: ");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        Vehiculo vehiculo = vehiculos.get(modelo);

        if (vehiculo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        String tipo = JOptionPane.showInputDialog("Seleccione el tipo de conducción: \n\n 1. Conducción normal\n 2. Conducción agresiva\n 3. Ahorro de combustible");

        if (tipo == null) {
            return;
        }

        int seleccion;

        try {
            seleccion = Integer.parseInt(tipo);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La opción debe ser un número");
            return;
        }

        if (seleccion < 1 || seleccion > 3) {
            JOptionPane.showMessageDialog(null, "Opción inválida");
            return;
        }

        String nombreTipo;

        switch (seleccion) {

            case 1:
                nombreTipo = "conduccionNormal";
                break;

            case 2:
                nombreTipo = "conduccionAgresiva";
                break;

            case 3:
                nombreTipo = "ahorroCombustible";
                break;

            default:
                return;
        }

        String entradaVelocidad = JOptionPane.showInputDialog("Ingrese la velocidad promedio en km/h: ");

        if (entradaVelocidad == null) {
            return;
        }

        double velocidadPromedio;

        try {
            velocidadPromedio = Double.parseDouble(entradaVelocidad);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La velocidad promedio debe ser un número");
            return;
        }

        if (velocidadPromedio < 0) {
            JOptionPane.showMessageDialog(null, "La velocidad promedio no puede ser negativa");
            return;
        }

        String entradaConsumo = JOptionPane.showInputDialog("Ingrese el consumo de combustible: ");

        if (entradaConsumo == null) {
            return;
        }

        double consumoCombustible;

        try {
            consumoCombustible = Double.parseDouble(entradaConsumo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El consumo de combustible debe ser un número");
            return;
        }

        if (consumoCombustible < 0) {
            JOptionPane.showMessageDialog(null, "El consumo de combustible no puede ser negativo");
            return;
        }

        String entradaDesgaste = JOptionPane.showInputDialog("Ingrese el desgaste de neumáticos: ");

        if (entradaDesgaste == null) {
            return;
        }

        double desgasteNeumaticos;

        try {
            desgasteNeumaticos = Double.parseDouble(entradaDesgaste);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El desgaste de neumáticos debe ser un número");
            return;
        }

        if (desgasteNeumaticos < 0) {
            JOptionPane.showMessageDialog(null, "El desgaste de neumáticos no puede ser negativo");
            return;
        }

        Map<String, Double> datosRendimiento = new HashMap<>();

        datosRendimiento.put("velocidadPromedio", velocidadPromedio);
        datosRendimiento.put("consumoCombustible", consumoCombustible);
        datosRendimiento.put("desgasteNeumaticos", desgasteNeumaticos);

        vehiculo.getRendimiento().put(nombreTipo, datosRendimiento);

        JOptionPane.showMessageDialog(null, "Rendimiento guardado correctamente para el vehículo " + vehiculo.getModelo());
    }

    public void asignarPiloto() {

        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehículos registrados");
            return;
        }

        if (gestorPilotos == null) {
            JOptionPane.showMessageDialog(null, "Error: El gestor de pilotos no está disponible.");
            return;
        }

        String entradaId = JOptionPane.showInputDialog("Ingrese el ID del piloto que desea asignar:");

        if (entradaId == null) {
            return;
        }

        int idPiloto;

        try {
            idPiloto = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID del piloto debe ser un número");
            return;
        }

        Piloto piloto = gestorPilotos.obtenerPilotoPorId(idPiloto);

        if (piloto == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un piloto con ese ID.");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo al que desea asignar el piloto:");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        Vehiculo vehiculo = vehiculos.get(modelo);

        if (vehiculo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        // Validar que el equipo del piloto coincida con el equipo del vehículo
        if (!piloto.getEquipo().equalsIgnoreCase(vehiculo.getEquipo())) {
            JOptionPane.showMessageDialog(null, 
                "Error: El piloto " + piloto.getNombre() + " pertenece al equipo " + piloto.getEquipo() + 
                ", pero el vehículo pertenece al equipo " + vehiculo.getEquipo() + ".\n" +
                "Solo se pueden asignar pilotos del mismo equipo al vehículo.");
            return;
        }

        if (vehiculo.getPilotos().containsKey(idPiloto)) {
            JOptionPane.showMessageDialog(null, "El piloto ya está asignado a este vehículo");
            return;
        }

        vehiculo.getPilotos().put(idPiloto, piloto.getNombre());

        JOptionPane.showMessageDialog(null, 
            "Piloto asignado correctamente:\n\n" +
            "Piloto: " + piloto.getNombre() + " (ID: " + idPiloto + ")\n" +
            "Equipo: " + piloto.getEquipo() + "\n" +
            "Vehículo: " + vehiculo.getModelo());
    }

    public void compararVehiculos() {

        if (vehiculos.size() < 2) {
            JOptionPane.showMessageDialog(null, "Se necesitan al menos dos vehículos registrados para comparar");
            return;
        }

        String modelo1 = JOptionPane.showInputDialog("Ingrese el modelo del primer vehículo: ");

        if (modelo1 == null || modelo1.trim().isEmpty()) {
            return;
        }

        Vehiculo vehiculo1 = vehiculos.get(modelo1);

        if (vehiculo1 == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        String modelo2 = JOptionPane.showInputDialog("Ingrese el modelo del segundo vehículo: ");

        if (modelo2 == null || modelo2.trim().isEmpty()) {
            return;
        }

        if (modelo2.equals(modelo1)) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar dos vehículos diferentes");
            return;
        }

        Vehiculo vehiculo2 = vehiculos.get(modelo2);

        if (vehiculo2 == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        String mensaje = "Comparación de vehículos: \n\n";

        mensaje += vehiculo1.getModelo() + "  vs  " + vehiculo2.getModelo() + "\n\n";

        mensaje += "Velocidad máxima: " + formatearDato(vehiculo1.getVelocidadMaxima(), "km/h") + "  vs  " + formatearDato(vehiculo2.getVelocidadMaxima(), "km/h") + "\n";
        mensaje += "Aceleración 0-100: " + formatearDato(vehiculo1.getAceleracion(), "s") + "  vs  " + formatearDato(vehiculo2.getAceleracion(), "s") + "\n\n";

        mensaje += "Rendimiento de " + vehiculo1.getModelo() + ": \n";
        mensaje += obtenerResumenRendimiento(vehiculo1);

        mensaje += "\nRendimiento de " + vehiculo2.getModelo() + ": \n";
        mensaje += obtenerResumenRendimiento(vehiculo2);

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Vehiculo obtenerVehiculo(String modelo) {
        return vehiculos.get(modelo);
    }

    public Map<String, Vehiculo> obtenerTodosVehiculos() {
        return vehiculos;
    }
}
