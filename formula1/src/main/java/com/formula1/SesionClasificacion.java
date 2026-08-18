package com.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SesionClasificacion {

    private int id;
    private String circuito;
    private CondicionClimatica clima;
    private LocalDateTime fechaHora;
    private List<ResultadoClasificacion> resultados;

    public SesionClasificacion(
            int id,
            String circuito,
            CondicionClimatica clima,
            LocalDateTime fechaHora,
            List<ResultadoClasificacion> resultados) {

        this.id = id;
        this.circuito = circuito;
        this.clima = clima;
        this.fechaHora = fechaHora;
        this.resultados = resultados;
    }

    public int getId() {
        return id;
    }

    public String getCircuito() {
        return circuito;
    }

    public CondicionClimatica getClima() {
        return clima;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public List<ResultadoClasificacion> getResultados() {
        return resultados;
    }

    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }

    public ResultadoClasificacion getPolePosition() {
        if (resultados.isEmpty()) {
            return null;
        }
        return resultados.get(0); // El primero siempre es pole position
    }

    public String getResumen() {
        return String.format("Sesión #%d | %s | %s | %s | Pole: %s",
                id,
                circuito,
                clima.getNombre(),
                getFechaFormateada(),
                getPolePosition() != null ? getPolePosition().getNombrePiloto() : "N/A");
    }

    @Override
    public String toString() {
        return getResumen();
    }
}
