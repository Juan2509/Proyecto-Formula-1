package com.formula1;

import java.util.Map;

public class Circuito {

    private String nombre;
    private String pais;
    private double longitud;
    private int vueltas;
    private String descripcion;

    private String tiempoRecord;
    private String pilotoRecord;
    private int anioRecord;

    private Map<Integer, Integer> ganadores;

    private String climaPromedio;
    private double consumoCombustible;
    private double desgasteNeumaticos;

    public Circuito(
            String nombre,
            String pais,
            double longitud,
            int vueltas,
            String descripcion,
            String tiempoRecord,
            String pilotoRecord,
            int anioRecord,
            Map<Integer, Integer> ganadores,
            String climaPromedio,
            double consumoCombustible,
            double desgasteNeumaticos) {

        this.nombre = nombre;
        this.pais = pais;
        this.longitud = longitud;
        this.vueltas = vueltas;
        this.descripcion = descripcion;
        this.tiempoRecord = tiempoRecord;
        this.pilotoRecord = pilotoRecord;
        this.anioRecord = anioRecord;
        this.ganadores = ganadores;
        this.climaPromedio = climaPromedio;
        this.consumoCombustible = consumoCombustible;
        this.desgasteNeumaticos = desgasteNeumaticos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getVueltas() {
        return vueltas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTiempoRecord() {
        return tiempoRecord;
    }

    public String getPilotoRecord() {
        return pilotoRecord;
    }

    public int getAnioRecord() {
        return anioRecord;
    }

    public Map<Integer, Integer> getGanadores() {
        return ganadores;
    }

    public String getClimaPromedio() {
        return climaPromedio;
    }

    public double getConsumoCombustible() {
        return consumoCombustible;
    }

    public double getDesgasteNeumaticos() {
        return desgasteNeumaticos;
    }
}