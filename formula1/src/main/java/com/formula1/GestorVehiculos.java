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
        Map<String, Map<String, Map<String, Double>>> rendimiento = new HashMap<>();

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
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

}

