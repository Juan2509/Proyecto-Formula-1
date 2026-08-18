package com.formula1;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class GestorPilotos {

    private final Map<Integer, Piloto> pilotos = new LinkedHashMap<>();
    private final GestorEquipos gestorEquipos;

    public GestorPilotos(GestorEquipos gestorEquipos) {
        this.gestorEquipos = gestorEquipos;
        cargarPilotosPredefinidos();
        sincronizarPilotosConEquipos();
    }

    private void cargarPilotosPredefinidos() {
        registrarPilotoPredefinido(1, "Lando Norris", "Reino Unido", "McLaren", "Titular", 85, 88);
        registrarPilotoPredefinido(2, "George Russell", "Reino Unido", "Mercedes", "Titular", 82, 86);
        registrarPilotoPredefinido(3, "Max Verstappen", "Países Bajos", "Red Bull Racing", "Titular", 95, 98);
        registrarPilotoPredefinido(4, "Charles Leclerc", "Mónaco", "Ferrari", "Titular", 90, 92);
        registrarPilotoPredefinido(5, "Carlos Sainz", "España", "Williams", "Titular", 85, 90);
        registrarPilotoPredefinido(6, "Liam Lawson", "Nueva Zelanda", "Racing Bulls", "Titular", 75, 70);
        registrarPilotoPredefinido(7, "Fernando Alonso", "España", "Aston Martin", "Titular", 88, 95);
        registrarPilotoPredefinido(8, "Esteban Ocon", "Francia", "Haas", "Titular", 78, 82);
        registrarPilotoPredefinido(9, "Nico Hülkenberg", "Alemania", "Audi", "Titular", 80, 88);
        registrarPilotoPredefinido(10, "Pierre Gasly", "Francia", "Alpine", "Titular", 82, 85);
        registrarPilotoPredefinido(11, "Sergio Pérez", "México", "Cadillac", "Titular", 83, 87);
    }

    private void registrarPilotoPredefinido(int id, String nombre, String pais, String equipo, String rol, int experiencia, int habilidad) {
        pilotos.put(id, new Piloto(id, nombre, pais, equipo, rol, experiencia, habilidad));
    }

    public void sincronizarPilotosConEquipos() {
        for (Piloto piloto : pilotos.values()) {
            Equipo equipo = gestorEquipos.obtenerEquipo(piloto.getEquipo());
            if (equipo != null) {
                equipo.agregarPiloto(piloto.getId(), piloto.getNombre());
            }
        }
    }

    public void registrarPiloto() {
        JOptionPane.showMessageDialog(null, "Aviso: los IDs del 1 al 11 ya están ocupados por los pilotos iniciales.");
        Integer id = solicitarEntero("Ingrese el ID del piloto:", 1, Integer.MAX_VALUE);
        if (id == null) return;
        if (pilotos.containsKey(id)) {
            JOptionPane.showMessageDialog(null, "Ya existe un piloto con ese ID.");
            return;
        }
        String nombre = solicitarTexto("Ingrese el nombre del piloto:");
        if (nombre == null) return;
        String pais = solicitarTexto("Ingrese el país del piloto:");
        if (pais == null) return;
        String equipo = solicitarEquipo();
        if (equipo == null) return;
        String rol = solicitarTexto("Ingrese el rol del piloto:");
        if (rol == null) return;
        Integer experiencia = solicitarEntero("Ingrese la experiencia (0 a 100):", 0, 100);
        Integer habilidad = solicitarEntero("Ingrese la habilidad (0 a 100):", 0, 100);
        if (experiencia == null || habilidad == null) return;

        Piloto piloto = new Piloto(id, nombre, pais, equipo, rol, experiencia, habilidad);
        pilotos.put(id, piloto);
        gestorEquipos.obtenerEquipo(equipo).agregarPiloto(id, nombre);
        JOptionPane.showMessageDialog(null, "Piloto registrado correctamente.");
    }

    public void listarPilotos() {
        if (pilotos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pilotos registrados.");
            return;
        }
        StringBuilder mensaje = new StringBuilder("Pilotos registrados:\n\n");
        for (Piloto piloto : pilotos.values()) {
            mensaje.append("ID ").append(piloto.getId()).append(": ").append(piloto.getNombre())
                    .append(" | Equipo: ").append(piloto.getEquipo())
                    .append(" | País: ").append(piloto.getPais())
                    .append(" | Rol: ").append(piloto.getRol())
                    .append(" | Habilidad: ").append(formatearPuntaje(piloto.getHabilidad())).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    public void buscarPiloto() {
        Piloto piloto = solicitarPiloto("Ingrese el ID del piloto:");
        if (piloto != null) JOptionPane.showMessageDialog(null, construirDetalle(piloto));
    }

    public void editarPiloto() {
        Piloto piloto = solicitarPiloto("Ingrese el ID del piloto que desea editar:");
        if (piloto == null) return;
        String opcion = JOptionPane.showInputDialog("Seleccione el dato a editar:\n1. Nombre\n2. Equipo\n3. Rol\n4. Experiencia\n5. Habilidad\n6. País");
        if (opcion == null) return;
        switch (opcion) {
            case "1" -> actualizarNombre(piloto);
            case "2" -> actualizarEquipo(piloto);
            case "3" -> actualizarTexto(piloto, "rol");
            case "4" -> actualizarPuntaje(piloto, true);
            case "5" -> actualizarPuntaje(piloto, false);
            case "6" -> actualizarPais(piloto);
            default -> {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Piloto actualizado correctamente.");
    }

    public void eliminarPiloto() {
        Piloto piloto = solicitarPiloto("Ingrese el ID del piloto que desea eliminar:");
        if (piloto == null) return;
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Eliminar a " + piloto.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            gestorEquipos.obtenerEquipo(piloto.getEquipo()).eliminarPiloto(piloto.getId());
            pilotos.remove(piloto.getId());
            JOptionPane.showMessageDialog(null, "Piloto eliminado correctamente.");
        }
    }

    private void actualizarNombre(Piloto piloto) {
        String nombre = solicitarTexto("Ingrese el nuevo nombre:");
        if (nombre == null) return;
        piloto.setNombre(nombre);
        gestorEquipos.obtenerEquipo(piloto.getEquipo()).agregarPiloto(piloto.getId(), nombre);
    }

    private void actualizarEquipo(Piloto piloto) {
        String nuevoEquipo = solicitarEquipo();
        if (nuevoEquipo == null || nuevoEquipo.equals(piloto.getEquipo())) return;
        gestorEquipos.obtenerEquipo(piloto.getEquipo()).eliminarPiloto(piloto.getId());
        piloto.setEquipo(nuevoEquipo);
        gestorEquipos.obtenerEquipo(nuevoEquipo).agregarPiloto(piloto.getId(), piloto.getNombre());
    }

    private void actualizarTexto(Piloto piloto, String campo) {
        String valor = solicitarTexto("Ingrese el nuevo " + campo + ":");
        if (valor != null) piloto.setRol(valor);
    }

    private void actualizarPuntaje(Piloto piloto, boolean esExperiencia) {
        Integer valor = solicitarEntero("Ingrese el nuevo valor (0 a 100):", 0, 100);
        if (valor == null) return;
        if (esExperiencia) piloto.setExperiencia(valor);
        else piloto.setHabilidad(valor);
    }

    private void actualizarPais(Piloto piloto) {
        String pais = solicitarTexto("Ingrese el nuevo país:");
        if (pais != null) piloto.setPais(pais);
    }

    private Piloto solicitarPiloto(String mensaje) {
        Integer id = solicitarEntero(mensaje, 1, Integer.MAX_VALUE);
        if (id == null) return null;
        Piloto piloto = pilotos.get(id);
        if (piloto == null) JOptionPane.showMessageDialog(null, "No se encontró el piloto.");
        return piloto;
    }

    private String solicitarEquipo() {
        String equipo = solicitarTexto("Ingrese el nombre del equipo del piloto:");
        if (equipo == null) return null;
        if (!gestorEquipos.existeEquipo(equipo)) {
            JOptionPane.showMessageDialog(null, "El equipo no existe. Regístrelo antes de asignar pilotos.");
            return null;
        }
        return equipo;
    }

    private String solicitarTexto(String mensaje) {
        String valor = JOptionPane.showInputDialog(mensaje);
        if (valor == null) return null;
        valor = valor.trim();
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El valor no puede estar vacío.");
            return null;
        }
        return valor;
    }

    private Integer solicitarEntero(String mensaje, int minimo, int maximo) {
        String entrada = JOptionPane.showInputDialog(mensaje);
        if (entrada == null) return null;
        try {
            int valor = Integer.parseInt(entrada);
            if (valor < minimo || valor > maximo) throw new NumberFormatException();
            return valor;
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Ingrese un número entero entre " + minimo + " y " + maximo + ".");
            return null;
        }
    }

    private String construirDetalle(Piloto piloto) {
        return "ID: " + piloto.getId() + "\nNombre: " + piloto.getNombre()
                + "\nPaís: " + piloto.getPais() + "\nEquipo: " + piloto.getEquipo() + "\nRol: " + piloto.getRol()
                + "\nExperiencia: " + formatearPuntaje(piloto.getExperiencia())
                + "\nHabilidad: " + formatearPuntaje(piloto.getHabilidad());
    }

    private String formatearPuntaje(Integer puntaje) {
        if (puntaje == null) return "No especificada";
        return puntaje + "/100";
    }

    public Piloto obtenerPilotoPorId(int id) {
        return pilotos.get(id);
    }

    public Map<Integer, Piloto> obtenerTodosPilotos() {
        return pilotos;
    }
}
