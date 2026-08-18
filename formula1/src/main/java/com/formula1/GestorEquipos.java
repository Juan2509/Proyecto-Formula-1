package com.formula1;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class GestorEquipos {

    private static final String[][] CATALOGO_ESCUDERIAS = {
        {"McLaren", "MCL40", "Mercedes", "Reino Unido"},
        {"Mercedes", "W17", "Mercedes", "Alemania"},
        {"Red Bull Racing", "RB22", "Red Bull Ford", "Austria"},
        {"Ferrari", "SF-26", "Ferrari", "Italia"},
        {"Williams", "FW48", "Mercedes", "Reino Unido"},
        {"Racing Bulls", "VCARB03", "Red Bull Ford", "Italia"},
        {"Aston Martin", "AMR26", "Honda", "Reino Unido"},
        {"Haas", "VF-26", "Ferrari", "Estados Unidos"},
        {"Audi", "R26", "Audi", "Alemania"},
        {"Alpine", "A526", "Mercedes", "Francia"},
        {"Cadillac", "MAC-26", "Ferrari", "Estados Unidos"}
    };
    private final Map<String, Equipo> equipos = new LinkedHashMap<>();

    public GestorEquipos() {
        cargarEquiposPredefinidos();
    }

    private void cargarEquiposPredefinidos() {
        for (String[] escuderia : CATALOGO_ESCUDERIAS) {
            equipos.put(escuderia[0], new Equipo(escuderia[0], escuderia[3], escuderia[1], escuderia[2]));
        }
    }

    public boolean existeEquipo(String nombre) {
        return obtenerEquipo(nombre) != null;
    }

    public Equipo obtenerEquipo(String nombre) {
        if (nombre == null) {
            return null;
        }

        for (Map.Entry<String, Equipo> entrada : equipos.entrySet()) {
            if (entrada.getKey().equalsIgnoreCase(nombre.trim())) {
                return entrada.getValue();
            }
        }

        return null;
    }

    public void registrarEquipo() {
        String[][] catalogoEscuderias = CATALOGO_ESCUDERIAS;
        StringBuilder opciones = new StringBuilder("Seleccione una escudería:\n\n");

        for (int indice = 0; indice < catalogoEscuderias.length; indice++) {
            opciones.append(indice + 1).append(". ").append(catalogoEscuderias[indice][0])
                    .append(" | Chasis: ").append(catalogoEscuderias[indice][1])
                    .append(" | Motor: ").append(catalogoEscuderias[indice][2]).append("\n");
        }
        opciones.append(catalogoEscuderias.length + 1).append(". Agregar equipo personalizado");

        Integer seleccion = solicitarEntero(opciones.toString(), 1, catalogoEscuderias.length + 1);
        if (seleccion == null) return;

        String nombre;
        String chasis;
        String motor;
        String pais;

        if (seleccion <= catalogoEscuderias.length) {
            String[] escuderia = catalogoEscuderias[seleccion - 1];
            nombre = escuderia[0];
            chasis = escuderia[1];
            motor = escuderia[2];
            pais = escuderia[3];
        } else {
            nombre = solicitarTexto("Ingrese el nombre del equipo:");
            if (nombre == null) return;
            chasis = solicitarTexto("Ingrese el chasis del equipo:");
            if (chasis == null) return;
            motor = solicitarTexto("Ingrese el motor del equipo:");
            if (motor == null) return;
            pais = solicitarTexto("Ingrese el país del equipo:");
            if (pais == null) return;
        }

        if (existeEquipo(nombre)) {
            JOptionPane.showMessageDialog(null, "Ya existe un equipo con ese nombre.");
            return;
        }

        equipos.put(nombre, new Equipo(nombre, pais, chasis, motor));
        JOptionPane.showMessageDialog(null, "Equipo registrado correctamente.");
    }

    public void listarEquipos() {
        if (equipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados.");
            return;
        }
        StringBuilder mensaje = new StringBuilder("Equipos registrados:\n\n");
        for (Equipo equipo : equipos.values()) {
            mensaje.append(equipo.getNombre()).append(" | País: ").append(equipo.getPais())
                    .append(" | Chasis: ").append(equipo.getChasis())
                    .append(" | Motor: ").append(equipo.getMotor())
                    .append(" | Pilotos: ").append(equipo.getPilotos().size()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    public void buscarEquipo() {
        String nombre = solicitarTexto("Ingrese el nombre del equipo:");
        if (nombre == null) return;
        Equipo equipo = obtenerEquipo(nombre);
        if (equipo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el equipo.");
            return;
        }
        JOptionPane.showMessageDialog(null, construirDetalle(equipo));
    }

    public void editarEquipo() {
        String nombre = solicitarTexto("Ingrese el nombre del equipo que desea editar:");
        if (nombre == null) return;
        Equipo equipo = obtenerEquipo(nombre);
        if (equipo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el equipo.");
            return;
        }

        String opcion = JOptionPane.showInputDialog("Seleccione el dato a editar:\n1. País\n2. Chasis\n3. Motor");
        if (opcion == null) return;

        if (opcion.equals("1")) {
            String pais = solicitarTexto("Ingrese el nuevo país:");
            if (pais != null) equipo.setPais(pais);
        } else if (opcion.equals("2")) {
            String chasis = solicitarTexto("Ingrese el nuevo chasis:");
            if (chasis != null) equipo.setChasis(chasis);
        } else if (opcion.equals("3")) {
            String motor = solicitarTexto("Ingrese el nuevo motor:");
            if (motor != null) equipo.setMotor(motor);
        } else {
            JOptionPane.showMessageDialog(null, "Opción inválida.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Equipo actualizado correctamente.");
    }

    public void eliminarEquipo() {
        String nombre = solicitarTexto("Ingrese el nombre del equipo que desea eliminar:");
        if (nombre == null) return;
        Equipo equipo = obtenerEquipo(nombre);
        if (equipo == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el equipo.");
            return;
        }
        if (!equipo.getPilotos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un equipo con pilotos asociados.");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Eliminar " + nombre + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            equipos.remove(equipo.getNombre());
            JOptionPane.showMessageDialog(null, "Equipo eliminado correctamente.");
        }
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
            JOptionPane.showMessageDialog(null, "Ingrese un número entre " + minimo + " y " + maximo + ".");
            return null;
        }
    }

    private String construirDetalle(Equipo equipo) {
        StringBuilder mensaje = new StringBuilder("Equipo: ").append(equipo.getNombre())
                .append("\nPaís: ").append(equipo.getPais())
                .append("\nChasis: ").append(equipo.getChasis())
                .append("\nMotor: ").append(equipo.getMotor()).append("\nPilotos:\n");
        if (equipo.getPilotos().isEmpty()) return mensaje.append("Sin pilotos asociados.").toString();
        for (Map.Entry<Integer, String> piloto : equipo.getPilotos().entrySet()) {
            mensaje.append("ID ").append(piloto.getKey()).append(": ").append(piloto.getValue()).append("\n");
        }
        return mensaje.toString();
    }
}
