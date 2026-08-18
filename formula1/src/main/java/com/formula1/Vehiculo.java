package com.formula1;

import java.util.LinkedHashMap;
import java.util.Map;

public class Vehiculo {

    private String equipo;
    private String modelo;
    private String motor;
    private Double velocidadMaxima;
    private Double aceleracion;
    private Map<Integer, String> pilotos;
    private Map<String, Map<String, Double>> rendimiento;
    private Map<String, String> especificacionesTecnicas;

    public Vehiculo(
            String equipo,
            String modelo,
            String motor,
            Double velocidadMaxima,
            Double aceleracion,
            Map<Integer, String> pilotos,
            Map<String, Map<String, Double>> rendimiento) {

        this.equipo = equipo;
        this.modelo = modelo;
        this.motor = motor;
        this.velocidadMaxima = velocidadMaxima;
        this.aceleracion = aceleracion;
        this.pilotos = pilotos;
        this.rendimiento = rendimiento;
        this.especificacionesTecnicas = new LinkedHashMap<>();
        cargarEspecificacionesTecnicas();

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

    public Double getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public Double getAceleracion() {
        return aceleracion;
    }

    public Map<Integer, String> getPilotos() {
        return pilotos;
    }

    public Map<String, Map<String, Double>> getRendimiento() {
        return rendimiento;
    }

    public Map<String, String> getEspecificacionesTecnicas() {
        return especificacionesTecnicas;
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

    public void setAceleracion(Double aceleracion) {
        this.aceleracion = aceleracion;
    }

    public void setPilotos(Map<Integer, String> pilotos) {
        this.pilotos = pilotos;
    }

    public void setRendimiento(
        Map<String, Map<String, Double>> rendimiento) {

        this.rendimiento = rendimiento;

    }

    private void cargarEspecificacionesTecnicas() {
        especificacionesTecnicas.put("Peso mínimo", "768 kg");
        especificacionesTecnicas.put("Batalla", "3400 mm");
        especificacionesTecnicas.put("Ancho", "1900 mm");
        especificacionesTecnicas.put("Downforce", "Reducido 30%");
        especificacionesTecnicas.put("Drag", "Reducido 55%");
        especificacionesTecnicas.put("Neumáticos", "18 pulgadas; 25 mm más angostos delante y 30 mm más angostos detrás");
        especificacionesTecnicas.put("Combustible", "100% sostenible");
        especificacionesTecnicas.put("Recuperación de energía por frenado", "8.5 MJ por vuelta");
        especificacionesTecnicas.put("Aerodinámica y adelantamiento", "Sin DRS tradicional; Straight-Mode, Corner-Mode y Overtake Mode");
    }
}
