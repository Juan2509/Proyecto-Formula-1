package com.formula1;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class GestorCircuitos {

    private Map<String, Circuito> circuitos;

    public GestorCircuitos() {
        circuitos = new HashMap<>();
    }

    public void agregarCircuito() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del circuito: ");

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre inválido");
            return;
        }

        if (circuitos.containsKey(nombre)) {
            JOptionPane.showMessageDialog(null, "El circuito ya existe");
        }

        String pais = JOptionPane.showInputDialog("Ingrese el país: ");

        if (pais == null || pais.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "País inválido");
            return;
        }

        String entradaLongitud = JOptionPane.showInputDialog("Ingrese la longitud del circuito en kilómetros: ");

        double longitud;

        try {
            longitud = Double.parseDouble(entradaLongitud);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La longitud debe ser un número");
            return;
        }

        String entradaVueltas = JOptionPane.showInputDialog("Ingrese el número de vueltas: ");

        int vueltas;

        try {
            vueltas = Integer.parseInt(entradaVueltas);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Las vueltas deben ser un número entero");
            return;
        }

        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción del circuito: ");

        if (descripcion == null) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía");
            return;
        }

        String tiempoRecord = JOptionPane.showInputDialog("Ingrese el tiempo récord de vuelta: ");

        String pilotoRecord = JOptionPane.showInputDialog("Ingrese el piloto que tiene el record: ");

        String entradaAnio = JOptionPane.showInputDialog("ingrese el año del récord: ");

        int anioRecord;

        try {
            anioRecord = Integer.parseInt(entradaAnio);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El año debe ser un número entero");
            return;
        }

        String climaPromedio = JOptionPane.showInputDialog("Ingrese el clima promedio del circuito: ");

        String entradaConsumo = JOptionPane.showInputDialog("Ingrese el consumo de combustible: ");

        double consumoCombustible;

        try {
            consumoCombustible = Double.parseDouble(entradaConsumo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El consumo debe ser un número");
            return;
        }

        String entradaDesgaste = JOptionPane.showInputDialog("Ingrese el desgaste de neumáticos: ");

        double desgasteNeumaticos;

        try {
            desgasteNeumaticos = Double.parseDouble(entradaDesgaste);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El desgaste debe ser un número");
            return;
        }

        Map<Integer, Integer> ganadores = new HashMap<>();

        Circuito nuevoCircuito = new Circuito(
            nombre, pais, longitud, vueltas, descripcion, tiempoRecord, pilotoRecord, anioRecord, ganadores, climaPromedio, consumoCombustible, desgasteNeumaticos
        );

        circuitos.put(nombre, nuevoCircuito);

        JOptionPane.showMessageDialog(null, "Cicruito agregado correctamente");    
    }

    public void listarCircuitos() {
        if (circuitos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay circuitos registrados");
            return;
        }

        String mensaje = "Circuitos Registrados\n\n";

        for (Circuito circuito : circuitos.values()) {

            mensaje += "Nombre: " + circuito.getNombre() + "\n";
            mensaje += "País: " + circuito.getPais() + "\n";
            mensaje += "Longitud: " + circuito.getLongitud() + "km\n";
            mensaje += "Vueltas: " + circuito.getVueltas() + "\n";
            mensaje += "Descripción: " + circuito.getDescripcion() + "\n";
            mensaje += "-----------------------------\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }
}