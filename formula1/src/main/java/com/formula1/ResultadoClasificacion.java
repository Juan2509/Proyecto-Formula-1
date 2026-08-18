package com.formula1;

public class ResultadoClasificacion implements Comparable<ResultadoClasificacion> {

    private int posicion;
    private int idPiloto;
    private String nombrePiloto;
    private String equipo;
    private double tiempoVuelta;
    private String circuito;
    private CondicionClimatica clima;

    public ResultadoClasificacion(
            int idPiloto,
            String nombrePiloto,
            String equipo,
            double tiempoVuelta,
            String circuito,
            CondicionClimatica clima) {

        this.idPiloto = idPiloto;
        this.nombrePiloto = nombrePiloto;
        this.equipo = equipo;
        this.tiempoVuelta = tiempoVuelta;
        this.circuito = circuito;
        this.clima = clima;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getIdPiloto() {
        return idPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public String getEquipo() {
        return equipo;
    }

    public double getTiempoVuelta() {
        return tiempoVuelta;
    }

    public String getCircuito() {
        return circuito;
    }

    public CondicionClimatica getClima() {
        return clima;
    }

    public String getTiempoFormateado() {
        int minutos = (int) (tiempoVuelta / 60);
        double segundos = tiempoVuelta % 60;
        return String.format("%d:%06.3f", minutos, segundos);
    }

    @Override
    public int compareTo(ResultadoClasificacion otro) {
        // Ordenar por tiempo de menor a mayor (el más rápido primero)
        return Double.compare(this.tiempoVuelta, otro.tiempoVuelta);
    }

    @Override
    public String toString() {
        return String.format("P%d | %s (%s) | %s",
                posicion,
                nombrePiloto,
                equipo,
                getTiempoFormateado());
    }
}
