package com.formula1;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class GestorConfiguraciones {

    private Map<String, ConfiguracionVehiculo> configuraciones;
    private GestorVehiculos gestorVehiculos;

    public GestorConfiguraciones(GestorVehiculos gestorVehiculos) {
        this.configuraciones = new HashMap<>();
        this.gestorVehiculos = gestorVehiculos;
    }

    public void crearConfiguracion() {
        
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo que desea configurar:");
        
        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        // Nota: Aquí deberíamos validar que el vehículo existe en el GestorVehiculos
        // pero no tenemos acceso público a esa información sin modificar GestorVehiculos
        // Por ahora, asumimos que el usuario ingresa un modelo válido

        String modoConductor = seleccionarModoConductor();
        if (modoConductor == null) {
            return;
        }

        String cargaAerodinamica = seleccionarCargaAerodinamica();
        if (cargaAerodinamica == null) {
            return;
        }

        String presionNeumaticos = seleccionarPresionNeumaticos();
        if (presionNeumaticos == null) {
            return;
        }

        String estrategiaCombustible = seleccionarEstrategiaCombustible();
        if (estrategiaCombustible == null) {
            return;
        }

        ConfiguracionVehiculo configuracion = new ConfiguracionVehiculo(
            modelo,
            modoConductor,
            cargaAerodinamica,
            presionNeumaticos,
            estrategiaCombustible
        );

        configuraciones.put(modelo, configuracion);

        String resumen = "Configuración guardada correctamente:\n\n" +
                        configuracion.toString() + "\n\n" +
                        "Efectos esperados:\n" +
                        obtenerEfectosConfiguracion(modoConductor, cargaAerodinamica, presionNeumaticos, estrategiaCombustible);

        JOptionPane.showMessageDialog(null, resumen);
    }

    private String seleccionarModoConductor() {
        String[] opciones = {
            "1. Normal",
            "2. Agresiva",
            "3. Ahorro de combustible"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el modo de conducción:\n\n" +
            "Normal: Balance entre rendimiento y desgaste\n" +
            "Agresiva: Mayor rendimiento, mayor consumo y desgaste\n" +
            "Ahorro: Menor rendimiento, menor consumo y desgaste",
            "Modo de conducción",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion == null) {
            return null;
        }

        if (seleccion.startsWith("1")) return "Normal";
        if (seleccion.startsWith("2")) return "Agresiva";
        if (seleccion.startsWith("3")) return "Ahorro de combustible";

        return null;
    }

    private String seleccionarCargaAerodinamica() {
        String[] opciones = {
            "1. Baja",
            "2. Media",
            "3. Alta"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione la carga aerodinámica:\n\n" +
            "Baja: Mayor velocidad en rectas, menor agarre en curvas\n" +
            "Media: Balance entre velocidad y agarre\n" +
            "Alta: Menor velocidad en rectas, mayor agarre en curvas",
            "Carga aerodinámica",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[1]
        );

        if (seleccion == null) {
            return null;
        }

        if (seleccion.startsWith("1")) return "Baja";
        if (seleccion.startsWith("2")) return "Media";
        if (seleccion.startsWith("3")) return "Alta";

        return null;
    }

    private String seleccionarPresionNeumaticos() {
        String[] opciones = {
            "1. Baja",
            "2. Estándar",
            "3. Alta"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione la presión de los neumáticos:\n\n" +
            "Baja: Mayor agarre, mayor desgaste\n" +
            "Estándar: Balance recomendado\n" +
            "Alta: Menor agarre, menor desgaste",
            "Presión de neumáticos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[1]
        );

        if (seleccion == null) {
            return null;
        }

        if (seleccion.startsWith("1")) return "Baja";
        if (seleccion.startsWith("2")) return "Estándar";
        if (seleccion.startsWith("3")) return "Alta";

        return null;
    }

    private String seleccionarEstrategiaCombustible() {
        String[] opciones = {
            "1. Agresiva",
            "2. Balanceada",
            "3. Ahorro"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione la estrategia de combustible:\n\n" +
            "Agresiva: Máximo rendimiento, alto consumo\n" +
            "Balanceada: Balance entre rendimiento y consumo\n" +
            "Ahorro: Menor rendimiento, bajo consumo",
            "Estrategia de combustible",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[1]
        );

        if (seleccion == null) {
            return null;
        }

        if (seleccion.startsWith("1")) return "Agresiva";
        if (seleccion.startsWith("2")) return "Balanceada";
        if (seleccion.startsWith("3")) return "Ahorro";

        return null;
    }

    private String obtenerEfectosConfiguracion(String modo, String carga, String presion, String estrategia) {
        String efectos = "";

        // Efectos del modo de conducción
        switch (modo) {
            case "Normal":
                efectos += "• Rendimiento: Normal\n• Consumo: Normal\n• Desgaste: Normal\n";
                break;
            case "Agresiva":
                efectos += "• Rendimiento: +15%\n• Consumo: +25%\n• Desgaste: +30%\n";
                break;
            case "Ahorro de combustible":
                efectos += "• Rendimiento: -10%\n• Consumo: -20%\n• Desgaste: -15%\n";
                break;
        }

        efectos += "\n";

        // Efectos de la carga aerodinámica
        switch (carga) {
            case "Baja":
                efectos += "• Velocidad máxima: +5%\n• Agarre en curvas: -10%\n";
                break;
            case "Media":
                efectos += "• Balance óptimo entre velocidad y agarre\n";
                break;
            case "Alta":
                efectos += "• Velocidad máxima: -5%\n• Agarre en curvas: +15%\n";
                break;
        }

        efectos += "\n";

        // Efectos de la presión de neumáticos
        switch (presion) {
            case "Baja":
                efectos += "• Agarre: +10%\n• Desgaste: +15%\n";
                break;
            case "Estándar":
                efectos += "• Valores recomendados por el fabricante\n";
                break;
            case "Alta":
                efectos += "• Agarre: -8%\n• Desgaste: -10%\n";
                break;
        }

        efectos += "\n";

        // Efectos de la estrategia de combustible
        switch (estrategia) {
            case "Agresiva":
                efectos += "• Potencia del motor: +10%\n• Consumo: +20%\n";
                break;
            case "Balanceada":
                efectos += "• Balance entre potencia y eficiencia\n";
                break;
            case "Ahorro":
                efectos += "• Potencia del motor: -8%\n• Consumo: -18%\n";
                break;
        }

        return efectos;
    }

    public void listarConfiguraciones() {
        if (configuraciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay configuraciones guardadas.");
            return;
        }

        String mensaje = "Configuraciones guardadas:\n\n";

        for (ConfiguracionVehiculo config : configuraciones.values()) {
            mensaje += "━━━━━━━━━━━━━━━━━━━━━━━━━\n";
            mensaje += config.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void consultarConfiguracion() {
        if (configuraciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay configuraciones guardadas.");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo:");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        ConfiguracionVehiculo config = configuraciones.get(modelo);

        if (config == null) {
            JOptionPane.showMessageDialog(null, "No se encontró una configuración para ese vehículo.");
            return;
        }

        String mensaje = config.toString() + "\n\n" +
                        "Efectos esperados:\n" +
                        obtenerEfectosConfiguracion(
                            config.getModoConductor(),
                            config.getCargaAerodinamica(),
                            config.getPresionNeumaticos(),
                            config.getEstrategiaCombustible()
                        );

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void modificarConfiguracion() {
        if (configuraciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay configuraciones guardadas.");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo cuya configuración desea modificar:");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        ConfiguracionVehiculo config = configuraciones.get(modelo);

        if (config == null) {
            JOptionPane.showMessageDialog(null, "No se encontró una configuración para ese vehículo.");
            return;
        }

        String[] opciones = {
            "1. Modo de conducción",
            "2. Carga aerodinámica",
            "3. Presión de neumáticos",
            "4. Estrategia de combustible"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "¿Qué desea modificar?",
            "Modificar configuración",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion == null) {
            return;
        }

        if (seleccion.startsWith("1")) {
            String nuevoModo = seleccionarModoConductor();
            if (nuevoModo != null) {
                config.setModoConductor(nuevoModo);
            }
        } else if (seleccion.startsWith("2")) {
            String nuevaCarga = seleccionarCargaAerodinamica();
            if (nuevaCarga != null) {
                config.setCargaAerodinamica(nuevaCarga);
            }
        } else if (seleccion.startsWith("3")) {
            String nuevaPresion = seleccionarPresionNeumaticos();
            if (nuevaPresion != null) {
                config.setPresionNeumaticos(nuevaPresion);
            }
        } else if (seleccion.startsWith("4")) {
            String nuevaEstrategia = seleccionarEstrategiaCombustible();
            if (nuevaEstrategia != null) {
                config.setEstrategiaCombustible(nuevaEstrategia);
            }
        }

        JOptionPane.showMessageDialog(null, "Configuración actualizada correctamente.");
    }

    public void eliminarConfiguracion() {
        if (configuraciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay configuraciones guardadas.");
            return;
        }

        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del vehículo cuya configuración desea eliminar:");

        if (modelo == null || modelo.trim().isEmpty()) {
            return;
        }

        if (!configuraciones.containsKey(modelo)) {
            JOptionPane.showMessageDialog(null, "No se encontró una configuración para ese vehículo.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
            null,
            "¿Está seguro de que desea eliminar la configuración del vehículo " + modelo + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            configuraciones.remove(modelo);
            JOptionPane.showMessageDialog(null, "Configuración eliminada correctamente.");
        }
    }

    public ConfiguracionVehiculo obtenerConfiguracion(String modelo) {
        return configuraciones.get(modelo);
    }
}
