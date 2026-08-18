package com.formula1;

import java.util.LinkedHashMap;
import java.util.Map;

public class Equipo {

    private String nombre;
    private String pais;
    private String chasis;
    private String motor;
    private final Map<Integer, String> pilotos;

    public Equipo(String nombre, String pais, String chasis, String motor) {
        this.nombre = nombre;
        this.pais = pais;
        this.chasis = chasis;
        this.motor = motor;
        this.pilotos = new LinkedHashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public String getMotor() {
        return motor;
    }

    public String getChasis() {
        return chasis;
    }

    public Map<Integer, String> getPilotos() {
        return pilotos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void agregarPiloto(int idPiloto, String nombrePiloto) {
        pilotos.put(idPiloto, nombrePiloto);
    }

    public void eliminarPiloto(int idPiloto) {
        pilotos.remove(idPiloto);
    }
}
