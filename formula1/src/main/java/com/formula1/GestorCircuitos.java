package com.formula1;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class GestorCircuitos {

    private Map<String, Circuito> circuitos;

    public GestorCircuitos() {
        circuitos = new HashMap<>();
    }

    public boolean agregarCircuito() {

        String nombre;

        while (true) {

            nombre = JOptionPane.showInputDialog(
                "Ingrese el nombre del circuito:"
            );

            if (nombre == null) {
                return false;
            }

            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El nombre no puede estar vacío."
                );
                continue;
            }

            if (circuitos.containsKey(nombre)) {
                JOptionPane.showMessageDialog(
                    null,
                    "El circuito ya existe."
                );
                continue;
            }

            break;
        }


        String pais;

        while (true) {

            pais = JOptionPane.showInputDialog(
                "Ingrese el país:"
            );

            if (pais == null) {
                return false;
            }

            if (pais.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El país no puede estar vacío."
                );
                continue;
            }

            break;
        }


        double longitud;

        while (true) {

            String entradaLongitud = JOptionPane.showInputDialog(
                "Ingrese la longitud del circuito en kilómetros:"
            );

            if (entradaLongitud == null) {
                return false;
            }

            try {

                longitud = Double.parseDouble(entradaLongitud);

                if (longitud <= 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "La longitud debe ser mayor que 0."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "La longitud debe ser un número."
                );
            }
        }


        int vueltas;

        while (true) {

            String entradaVueltas = JOptionPane.showInputDialog(
                "Ingrese el número de vueltas:"
            );

            if (entradaVueltas == null) {
                return false;
            }

            try {

                vueltas = Integer.parseInt(entradaVueltas);

                if (vueltas <= 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El número de vueltas debe ser mayor que 0."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "Las vueltas deben ser un número entero."
                );
            }
        }


        String descripcion;

        while (true) {

            descripcion = JOptionPane.showInputDialog(
                "Ingrese la descripción del circuito:"
            );

            if (descripcion == null) {
                return false;
            }

            if (descripcion.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "La descripción no puede estar vacía."
                );
                continue;
            }

            break;
        }


        String tiempoRecord;

        while (true) {

            tiempoRecord = JOptionPane.showInputDialog(
                "Ingrese el tiempo récord de vuelta:"
            );

            if (tiempoRecord == null) {
                return false;
            }

            if (tiempoRecord.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El tiempo récord no puede estar vacío."
                );
                continue;
            }

            break;
        }


        String pilotoRecord;

        while (true) {

            pilotoRecord = JOptionPane.showInputDialog(
                "Ingrese el piloto que tiene el récord:"
            );

            if (pilotoRecord == null) {
                return false;
            }

            if (pilotoRecord.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El piloto no puede estar vacío."
                );
                continue;
            }

            break;
        }


        int anioRecord;

        while (true) {

            String entradaAnio = JOptionPane.showInputDialog(
                "Ingrese el año del récord:"
            );

            if (entradaAnio == null) {
                return false;
            }

            try {

                anioRecord = Integer.parseInt(entradaAnio);

                if (anioRecord <= 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El año debe ser mayor que 0."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "El año debe ser un número entero."
                );
            }
        }


        String climaPromedio;

        while (true) {

            climaPromedio = JOptionPane.showInputDialog(
                "Ingrese el clima promedio del circuito:"
            );

            if (climaPromedio == null) {
                return false;
            }

            if (climaPromedio.trim().isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    "El clima promedio no puede estar vacío."
                );
                continue;
            }

            break;
        }


        double consumoCombustible;

        while (true) {

            String entradaConsumo = JOptionPane.showInputDialog(
                "Ingrese el consumo de combustible:"
            );

            if (entradaConsumo == null) {
                return false;
            }

            try {

                consumoCombustible = Double.parseDouble(entradaConsumo);

                if (consumoCombustible < 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El consumo no puede ser negativo."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "El consumo debe ser un número."
                );
            }
        }


        double desgasteNeumaticos;

        while (true) {

            String entradaDesgaste = JOptionPane.showInputDialog(
                "Ingrese el desgaste de neumáticos:"
            );

            if (entradaDesgaste == null) {
                return false;
            }

            try {

                desgasteNeumaticos = Double.parseDouble(entradaDesgaste);

                if (desgasteNeumaticos < 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "El desgaste no puede ser negativo."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(
                    null,
                    "El desgaste debe ser un número."
                );
            }
        }


        Map<Integer, Integer> ganadores = new HashMap<>();


        Circuito nuevoCircuito = new Circuito(
            nombre,
            pais,
            longitud,
            vueltas,
            descripcion,
            tiempoRecord,
            pilotoRecord,
            anioRecord,
            ganadores,
            climaPromedio,
            consumoCombustible,
            desgasteNeumaticos
        );


        circuitos.put(nombre, nuevoCircuito);


        JOptionPane.showMessageDialog(
            null,
            "Circuito agregado correctamente."
        );

        return true;
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

    public void buscarCircuito() {
        
        if (circuitos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay circuitos registrados");
            return;
        }

    String[] opciones = {
        "Buscar por nombre",
        "Buscar por país"
    };

    String opcion = (String) JOptionPane.showInputDialog(
        null,
        "Seleccione el tipo de búsqueda: ",
        "Buscar circuito",
        JOptionPane.QUESTION_MESSAGE,
        null,
        opciones,
        opciones[0]
    );

    if (opcion == null) {
        return;
    }

    if (opcion.equals("Buscar por nombre")) {
        
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del circuito");

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        Circuito circuito = circuitos.get(nombre);

        if (circuito == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un circuito con ese nombre");
            return;
        }

        mostrarCircuito(circuito);

    } else {

        String pais = JOptionPane.showInputDialog("Ingrese el país");

        if (pais == null || pais.trim().isEmpty()) {
            return;
        }

        String mensaje = "Circuitos encontrados:\n\n";
        boolean encontrado = false;

        for (Circuito circuito : circuitos.values()) {

            if (circuito.getPais().equalsIgnoreCase(pais.trim())) {

                mensaje += "Nombre: " + circuito.getNombre() + "\n";
                mensaje += "País: " + circuito.getPais() + "\n";
                mensaje += "Longitud: " + circuito.getLongitud() + "\n";
                mensaje += "Vueltas: " + circuito.getVueltas() + "\n";
                mensaje += "Descripción: " + circuito.getDescripcion() + "\n";
                mensaje += "------------------------------\n";
                encontrado = true;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron circuitos en ese país");
            return;
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    }

    private void mostrarCircuito(Circuito circuito) {

        String mensaje = "Circuito encontrado\n\n";

        mensaje += "Nombre: " + circuito.getNombre() + "\n";
        mensaje += "País: " + circuito.getPais() + "\n";
        mensaje += "Longitud: " + circuito.getLongitud() + " km\n";
        mensaje += "Vueltas: " + circuito.getVueltas() + "\n";
        mensaje += "Descripción: " + circuito.getDescripcion() + "\n";

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void editarCircuito() {

        if (circuitos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay circuitos registrados");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del circuito que desea editar: ");

        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }

        Circuito circuito = circuitos.get(nombre);

        if (circuito == null) {
            JOptionPane.showMessageDialog(null, "No se encontró un circuito con ese nombre");
            return;
        }

        String[] opciones = {
            "Nombre",
            "País",
            "Longitud",
            "Vueltas",
            "Descripción",
            "Tiempo récord",
            "Piloto del récord",
            "Año del récord",
            "Clima promedio",
            "Consumo de combustible",
            "Desgaste de neumáticos"
        };

        String opcion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el dato que desea modificar: ",
            "Editar circuito",
            JOptionPane.QUESTION_MESSAGE,
            null,opciones,
            opciones[0]
        );

        if (opcion == null) {
            return;
        }

        switch (opcion) {
            case "Nombre":
                
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre: ");

                if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                    return;
                }

                if (circuitos.containsKey(nuevoNombre)) {
                    JOptionPane.showMessageDialog(null, "Ya existe un circuito con ese nombre.");
                    return;
                }

                circuitos.remove(circuito.getNombre());

                circuito.setNombre(nuevoNombre);

                circuitos.put(nuevoNombre, circuito);

                break;

            case "País":

                String nuevoPais = JOptionPane.showInputDialog("Ingrese el nuevo país: ");

                if (nuevoPais == null || nuevoPais.trim().isEmpty()) {
                    return;
                }

                circuito.setPais(nuevoPais);

                break;

            case "Longitud":

                String entradaLongitud = JOptionPane.showInputDialog("Ingrese la nueva longitud en kilómetros: ");

                if (entradaLongitud == null) {
                    return;
                }

                try {
                    
                    double nuevaLongitud = Double.parseDouble(entradaLongitud);

                    if (nuevaLongitud <= 0) {
                        JOptionPane.showMessageDialog(null, "La longitud debe ser mayor que 0");
                        return;
                    }

                    circuito.setLongitud(nuevaLongitud);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "La longitud debe ser un número");
                    return;
                }

                break;

                case "Vueltas":

                    String entradaVueltas = JOptionPane.showInputDialog("Ingrese el nuevo número de vueltas: ");

                    if (entradaVueltas == null) {
                        return;
                    }

                    try {

                        int nuevasVueltas = Integer.parseInt(entradaVueltas);

                        if (nuevasVueltas <= 0) {
                            JOptionPane.showMessageDialog(null, "Las vueltas deben ser mayores de 0.");
                            return;
                        }

                        circuito.setVueltas(nuevasVueltas);

                    } catch (NumberFormatException e) {

                        JOptionPane.showMessageDialog(null, "Las vueltas deben ser un número entero.");

                        return;
                    }

                    break;

                case "Descripción":

                    String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción: ");

                    if (nuevaDescripcion == null || nuevaDescripcion.trim().isEmpty()) {
                        return;                        
                    }

                    circuito.setDescripcion(nuevaDescripcion);

                    break;

                case "Tiempo récord":

                String nuevoTiempo = JOptionPane.showInputDialog("Ingrese el nuevo piloto del récord: ");

                if (nuevoTiempo == null || nuevoTiempo.trim().isEmpty()) {
                    return;
                }

                circuito.setTiempoRecord(nuevoTiempo);
                break;

            case "Piloto del récord":

                String nuevoPiloto = JOptionPane.showInputDialog("Ingrese el nuevo piloto del récord: ");

                if (nuevoPiloto == null || nuevoPiloto.trim().isEmpty()) {
                    return;
                }

                circuito.setPilotoRecord(nuevoPiloto);

                break;

            case "Año del récord":

                String entradaAnio = JOptionPane.showInputDialog("Ingrese el nuevo año del récord: ");

                if (entradaAnio == null) {
                    return;
                }

                try {
                    
                    int nuevoAnio = Integer.parseInt(entradaAnio);

                    if (nuevoAnio <= 0) {
                        JOptionPane.showMessageDialog(null, "El año debe ser mayor que 0");
                        return;
                    }

                    circuito.setAnioRecord(nuevoAnio);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(null, "El año debe ser un número entero");
                    return;
                }

                break;

            case "Clima promedio":

            String nuevoClima = JOptionPane.showInputDialog("Ingrese el nuevo clima promedio");

            if (nuevoClima == null || nuevoClima.trim().isEmpty()) {
                return;
            }

            circuito.setClimaPromedio(nuevoClima);
            break;

            case "Desgaste de neumáticos":

                String entradaDesgaste = JOptionPane.showInputDialog("Ingrese el nuevo desgaste de neumáticos: ");

                if (entradaDesgaste == null) {
                    return;
                }

                try {
                    
                    double nuevoDesgaste = Double.parseDouble(entradaDesgaste);

                    if (nuevoDesgaste < 0) {
                        JOptionPane.showMessageDialog(null, "El desgaste no puede ser negativo");
                        return;
                    }

                    circuito.setDesgasteNeumaticos(nuevoDesgaste);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(null, "El desgaste debe ser un número");
                    return;
                }

                break;

            case "Consumo de combustible":

                String entradaConsumo = JOptionPane.showInputDialog("Ingrese el nuevo consumo de combústible:");

                if (entradaConsumo == null) {
                    return;
                }

                try {
                    
                    double nuevoConsumo = Double.parseDouble(entradaConsumo);

                    if (nuevoConsumo < 0) {
                        JOptionPane.showMessageDialog(null, "El consumo no pude ser negativo");
                        return;
                    }

                    circuito.setConsumoCombustible(nuevoConsumo);

                } catch (NumberFormatException e) {

                    JOptionPane.showMessageDialog(null, "El consumo debe ser un número");

                    return;
                }

                break;
        }

        JOptionPane.showMessageDialog(null, "Circuito actualizado correctamente");

    }
}