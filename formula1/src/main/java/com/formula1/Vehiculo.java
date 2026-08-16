package com.formula1;

import java.util.Map;

public class Vehiculo {

    private String equipo;
    private String modelo;
    private String motor;
    private double velocidadMaxima;
    private double aceleracion;
    private Map<Integer, String> pilotos;
    private Map<String, Map<String, Map<String, Double>>> rendimiento;

    public Vehiculo(
            String equipo,
            String modelo,
            String motor,
            double velocidadMaxima,
            double aceleracion,
            Map<Integer, String> pilotos,
            Map<String, Map<String, Map<String, Double>>> rendimiento) {

        this.equipo = equipo;
        this.modelo = modelo;
        this.motor = motor;
        this.velocidadMaxima = velocidadMaxima;
        this.aceleracion = aceleracion;
        this.pilotos = pilotos;
        this.rendimiento = rendimiento;

    }

    public String getEquipo() {
        return equipo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMotor() {
        return motor;
    }

    public double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public double getAceleracion() {
        return aceleracion;
    }

    public Map<Integer, String> getPilotos() {
        return pilotos;
    }

    public Map<String, Map<String, Map<String, Double>>> getRendimiento() {
        return rendimiento;
    }


    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setVelocidadMaxima(Double velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public void setAceleracion(double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public void setPilotos(Map<Integer, String> pilotos) {
        this.pilotos = pilotos;
    }

    public void setRendimiento(
        Map<String, Map<String, Map<String, Double>>> rendimiento) {

        this.rendimiento = rendimiento;

    }
}