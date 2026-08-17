package com.formula1;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class GestorVehiculos {
    
    private Map<String, Vehiculo> vehiculos;

    public GestorVehiculos() {
        vehiculos = new HashMap<>();
    }

    public void agregarVehiculo() {
        
        String equipo = JOptionPane.showInputDialog("Ingrese el equipo al que pertenece el vehículo: ");

        if (equipo == null || equipo.trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "El equipo no puede estar vaciío");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo: ");

        if (modelo == null || modelo.trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "El modelo no puede estar vacío");
            return;
        }

        if (vehiculos.containsKey(modelo)) {

            JOptionPane.showMessageDialog(null, "Ya existe un vehículo con ese modelo");
            return;
        }

        String motor = JOptionPane.showInputDialog("Ingrese el motor del vehículo:");

        if (motor == null || motor.trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "El motor no puede estar vacío");
            return;
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

        String mensaje = "Vehículos registrados : \n\n";

        for (Vehiculo vehiculo : vehiculos.values()) {

            mensaje += "Equipo: " + vehiculo.getEquipo() + "\n";
            mensaje += "Modelo: " + vehiculo.getModelo() + "\n";
            mensaje += "Motor: " + vehiculo.getMotor() + "\n";
            mensaje += "Velocidad máxima: " + vehiculo.getVelocidadMaxima() + " km/h\n";
            mensaje += "Aceleración 0-100: " + vehiculo.getAceleracion() + " segundos\n";
            mensaje += "Pilotos asignados: " + vehiculo.getPilotos().size() + "\n";
            mensaje += "-----------------------------\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
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
        mensaje += "Velocidad máxima: " + vehiculo.getVelocidadMaxima() + " km/h\n";
        mensaje += "Aceleración 0-100: " + vehiculo.getAceleracion() + " segundos\n\n";

        mensaje += "Pilotos asignados: \n";
        mensaje += obtenerResumenPilotos(vehiculo);

        mensaje += "\nRendimiento configurado: \n";
        mensaje += obtenerResumenRendimiento(vehiculo);

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

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo al que desea asignar un piloto: ");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        Vehiculo vehiculo = vehiculos.get(modelo);

        if (vehiculo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un vehículo con ese modelo");
            return;
        }

        String entradaId = JOptionPane.showInputDialog("Ingrese el ID del piloto: ");

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

        if (vehiculo.getPilotos().containsKey(idPiloto)) {
            JOptionPane.showMessageDialog(null, "Ya hay un piloto asignado con ese ID en este vehículo");
            return;
        }

        String nombrePiloto = JOptionPane.showInputDialog("Ingrese el nombre del piloto: ");

        if (nombrePiloto == null || nombrePiloto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del piloto no puede estar vacío");
            return;
        }

        /* Nota: la asignación se hace con ID y nombre escritos a mano mientras el
           módulo de pilotos no está terminado. Cuando ese módulo exista, aquí se
           debe reemplazar por una búsqueda del piloto real dentro de ese gestor. */

        vehiculo.getPilotos().put(idPiloto, nombrePiloto);

        JOptionPane.showMessageDialog(null, "Piloto asignado correctamente al vehículo " + vehiculo.getModelo());
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

        mensaje += "Velocidad máxima: " + vehiculo1.getVelocidadMaxima() + " km/h  vs  " + vehiculo2.getVelocidadMaxima() + " km/h\n";
        mensaje += "Aceleración 0-100: " + vehiculo1.getAceleracion() + " s  vs  " + vehiculo2.getAceleracion() + " s\n\n";

        mensaje += "Rendimiento de " + vehiculo1.getModelo() + ": \n";
        mensaje += obtenerResumenRendimiento(vehiculo1);

        mensaje += "\nRendimiento de " + vehiculo2.getModelo() + ": \n";
        mensaje += obtenerResumenRendimiento(vehiculo2);

        JOptionPane.showMessageDialog(null, mensaje);
    }

}