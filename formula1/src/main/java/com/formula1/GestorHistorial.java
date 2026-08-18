package com.formula1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class GestorHistorial {

    private Map<Integer, SesionClasificacion> sesiones;
    private int contadorSesiones;

    public GestorHistorial() {
        this.sesiones = new HashMap<>();
        this.contadorSesiones = 0;
    }

    /**
     * Guarda una nueva sesión de clasificación en el historial.
     */
    public void guardarSesion(
            String circuito,
            CondicionClimatica clima,
            List<ResultadoClasificacion> resultados) {

        contadorSesiones++;
        SesionClasificacion sesion = new SesionClasificacion(
                contadorSesiones,
                circuito,
                clima,
                LocalDateTime.now(),
                new ArrayList<>(resultados) // Copia de la lista
        );

        sesiones.put(contadorSesiones, sesion);

        JOptionPane.showMessageDialog(null,
                "✅ Sesión guardada correctamente\n\n" +
                "ID de sesión: " + contadorSesiones + "\n" +
                "Circuito: " + circuito + "\n" +
                "Pole position: " + sesion.getPolePosition().getNombrePiloto());
    }

    /**
     * Lista todas las sesiones guardadas.
     */
    public void listarSesiones() {
        if (sesiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay sesiones guardadas en el historial.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("📋 HISTORIAL DE SESIONES 📋\n\n");
        mensaje.append("Total de sesiones: ").append(sesiones.size()).append("\n\n");

        for (SesionClasificacion sesion : sesiones.values()) {
            mensaje.append(sesion.getResumen()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Consulta una sesión específica por su ID.
     */
    public void consultarSesion() {
        if (sesiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay sesiones guardadas en el historial.");
            return;
        }

        String entradaId = JOptionPane.showInputDialog("Ingrese el ID de la sesión que desea consultar:");

        if (entradaId == null || entradaId.trim().isEmpty()) {
            return;
        }

        int id;
        try {
            id = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El ID debe ser un número.");
            return;
        }

        SesionClasificacion sesion = sesiones.get(id);

        if (sesion == null) {
            JOptionPane.showMessageDialog(null, "No se encontró una sesión con ese ID.");
            return;
        }

        mostrarDetalleSesion(sesion);
    }

    /**
     * Muestra el detalle completo de una sesión.
     */
    private void mostrarDetalleSesion(SesionClasificacion sesion) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("🏁 SESIÓN #").append(sesion.getId()).append(" 🏁\n\n");
        mensaje.append("Circuito: ").append(sesion.getCircuito()).append("\n");
        mensaje.append("Clima: ").append(sesion.getClima().getNombre()).append("\n");
        mensaje.append("Fecha: ").append(sesion.getFechaFormateada()).append("\n\n");
        mensaje.append("════════════════════════════════════════\n\n");

        for (ResultadoClasificacion resultado : sesion.getResultados()) {
            mensaje.append(String.format("P%-2d | %-20s | %-18s | %s\n",
                    resultado.getPosicion(),
                    resultado.getNombrePiloto(),
                    resultado.getEquipo(),
                    resultado.getTiempoFormateado()));
        }

        mensaje.append("\n════════════════════════════════════════\n");
        mensaje.append("\n🏆 POLE POSITION: ").append(sesion.getPolePosition().getNombrePiloto());

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Consulta sesiones por circuito específico.
     */
    public void consultarPorCircuito() {
        if (sesiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay sesiones guardadas en el historial.");
            return;
        }

        String circuito = JOptionPane.showInputDialog("Ingrese el nombre del circuito:");

        if (circuito == null || circuito.trim().isEmpty()) {
            return;
        }

        List<SesionClasificacion> sesionesFiltradas = new ArrayList<>();

        for (SesionClasificacion sesion : sesiones.values()) {
            if (sesion.getCircuito().toLowerCase().contains(circuito.toLowerCase())) {
                sesionesFiltradas.add(sesion);
            }
        }

        if (sesionesFiltradas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron sesiones en ese circuito.");
            return;
        }

        StringBuilder mensaje = new StringBuilder("📍 SESIONES EN: " + circuito + "\n\n");
        mensaje.append("Total: ").append(sesionesFiltradas.size()).append(" sesión(es)\n\n");

        for (SesionClasificacion sesion : sesionesFiltradas) {
            mensaje.append(sesion.getResumen()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Compara tiempos de dos sesiones diferentes.
     */
    public void compararSesiones() {
        if (sesiones.size() < 2) {
            JOptionPane.showMessageDialog(null, "Se necesitan al menos 2 sesiones para comparar.");
            return;
        }

        String entrada1 = JOptionPane.showInputDialog("Ingrese el ID de la primera sesión:");
        if (entrada1 == null) return;

        String entrada2 = JOptionPane.showInputDialog("Ingrese el ID de la segunda sesión:");
        if (entrada2 == null) return;

        int id1, id2;
        try {
            id1 = Integer.parseInt(entrada1);
            id2 = Integer.parseInt(entrada2);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Los IDs deben ser números.");
            return;
        }

        SesionClasificacion sesion1 = sesiones.get(id1);
        SesionClasificacion sesion2 = sesiones.get(id2);

        if (sesion1 == null || sesion2 == null) {
            JOptionPane.showMessageDialog(null, "Una o ambas sesiones no existen.");
            return;
        }

        compararDosSesiones(sesion1, sesion2);
    }

    /**
     * Muestra comparación detallada entre dos sesiones.
     */
    private void compararDosSesiones(SesionClasificacion s1, SesionClasificacion s2) {
        StringBuilder mensaje = new StringBuilder("🔍 COMPARACIÓN DE SESIONES 🔍\n\n");

        mensaje.append("SESIÓN #").append(s1.getId()).append(":\n");
        mensaje.append("• Circuito: ").append(s1.getCircuito()).append("\n");
        mensaje.append("• Clima: ").append(s1.getClima().getNombre()).append("\n");
        mensaje.append("• Pole: ").append(s1.getPolePosition().getNombrePiloto())
                .append(" (").append(s1.getPolePosition().getTiempoFormateado()).append(")\n\n");

        mensaje.append("SESIÓN #").append(s2.getId()).append(":\n");
        mensaje.append("• Circuito: ").append(s2.getCircuito()).append("\n");
        mensaje.append("• Clima: ").append(s2.getClima().getNombre()).append("\n");
        mensaje.append("• Pole: ").append(s2.getPolePosition().getNombrePiloto())
                .append(" (").append(s2.getPolePosition().getTiempoFormateado()).append(")\n\n");

        // Comparar pole positions
        double tiempo1 = s1.getPolePosition().getTiempoVuelta();
        double tiempo2 = s2.getPolePosition().getTiempoVuelta();
        double diferencia = Math.abs(tiempo1 - tiempo2);

        mensaje.append("════════════════════════════════════════\n\n");

        if (tiempo1 < tiempo2) {
            mensaje.append("✅ La Sesión #").append(s1.getId())
                    .append(" tuvo el mejor tiempo de pole\n");
            mensaje.append("Diferencia: ").append(String.format("%.3f", diferencia)).append(" segundos");
        } else if (tiempo2 < tiempo1) {
            mensaje.append("✅ La Sesión #").append(s2.getId())
                    .append(" tuvo el mejor tiempo de pole\n");
            mensaje.append("Diferencia: ").append(String.format("%.3f", diferencia)).append(" segundos");
        } else {
            mensaje.append("⚖️ Ambas sesiones tuvieron el mismo tiempo de pole");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Muestra estadísticas generales del historial.
     */
    public void mostrarEstadisticas() {
        if (sesiones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay sesiones guardadas en el historial.");
            return;
        }

        // Contar sesiones por circuito
        Map<String, Integer> sesionesPorCircuito = new HashMap<>();
        Map<String, Integer> polesPorPiloto = new HashMap<>();

        for (SesionClasificacion sesion : sesiones.values()) {
            // Contar por circuito
            String circuito = sesion.getCircuito();
            sesionesPorCircuito.put(circuito, sesionesPorCircuito.getOrDefault(circuito, 0) + 1);

            // Contar poles por piloto
            String piloto = sesion.getPolePosition().getNombrePiloto();
            polesPorPiloto.put(piloto, polesPorPiloto.getOrDefault(piloto, 0) + 1);
        }

        StringBuilder mensaje = new StringBuilder("📊 ESTADÍSTICAS GENERALES 📊\n\n");
        mensaje.append("Total de sesiones: ").append(sesiones.size()).append("\n\n");

        mensaje.append("SESIONES POR CIRCUITO:\n");
        for (Map.Entry<String, Integer> entry : sesionesPorCircuito.entrySet()) {
            mensaje.append("• ").append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" sesión(es)\n");
        }

        mensaje.append("\nPOLES POR PILOTO:\n");
        for (Map.Entry<String, Integer> entry : polesPorPiloto.entrySet()) {
            mensaje.append("• ").append(entry.getKey()).append(": ")
                    .append(entry.getValue()).append(" pole(s)\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    /**
     * Obtiene una sesión por ID (para uso interno).
     */
    public SesionClasificacion obtenerSesion(int id) {
        return sesiones.get(id);
    }

    /**
     * Obtiene todas las sesiones (para uso interno).
     */
    public Map<Integer, SesionClasificacion> obtenerTodasSesiones() {
        return sesiones;
    }
}
