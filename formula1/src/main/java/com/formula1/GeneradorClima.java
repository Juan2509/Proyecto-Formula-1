package com.formula1;

import java.util.Random;
import javax.swing.JOptionPane;

public class GeneradorClima {

    private Random random;

    public GeneradorClima() {
        this.random = new Random();
    }

    /**
     * Genera una condición climática aleatoria para la simulación.
     * 
     * Probabilidades:
     * - Seco: 60%
     * - Lluvioso: 30%
     * - Extremo: 10%
     * 
     * @return La condición climática generada
     */
    public CondicionClimatica generarClimaAleatorio() {
        int probabilidad = random.nextInt(100);

        if (probabilidad < 60) {
            return CondicionClimatica.SECO;
        } else if (probabilidad < 90) {
            return CondicionClimatica.LLUVIOSO;
        } else {
            return CondicionClimatica.EXTREMO;
        }
    }

    /**
     * Genera un clima aleatorio y lo muestra al usuario con sus efectos.
     * 
     * @return La condición climática generada
     */
    public CondicionClimatica generarYMostrarClima() {
        CondicionClimatica clima = generarClimaAleatorio();
        
        String mensaje = "🌤️ CONDICIONES CLIMÁTICAS GENERADAS 🌤️\n\n";
        mensaje += "Clima: " + clima.getNombre() + "\n\n";
        mensaje += clima.getDescripcion();

        JOptionPane.showMessageDialog(null, mensaje);

        return clima;
    }

    /**
     * Muestra información detallada sobre una condición climática.
     * 
     * @param clima La condición climática a mostrar
     */
    public void mostrarInformacionClima(CondicionClimatica clima) {
        String mensaje = "INFORMACIÓN DEL CLIMA\n\n";
        mensaje += "Condición: " + clima.getNombre() + "\n\n";
        mensaje += clima.getDescripcion() + "\n\n";
        mensaje += "MODIFICADORES TÉCNICOS:\n";
        mensaje += "• Velocidad: x" + clima.getModificadorVelocidad() + "\n";
        mensaje += "• Consumo: x" + clima.getModificadorConsumo() + "\n";
        mensaje += "• Desgaste: x" + clima.getModificadorDesgaste();

        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Permite al usuario seleccionar manualmente una condición climática
     * (útil para pruebas o simulaciones específicas).
     * 
     * @return La condición climática seleccionada, o null si se cancela
     */
    public CondicionClimatica seleccionarClimaManual() {
        String[] opciones = {
            "1. Seco",
            "2. Lluvioso",
            "3. Extremo"
        };

        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione la condición climática:\n\n" +
            "Seco: Condiciones ideales\n" +
            "Lluvioso: Pista mojada, -15% velocidad\n" +
            "Extremo: Lluvia intensa, -30% velocidad",
            "Selección de clima",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (seleccion == null) {
            return null;
        }

        if (seleccion.startsWith("1")) {
            return CondicionClimatica.SECO;
        } else if (seleccion.startsWith("2")) {
            return CondicionClimatica.LLUVIOSO;
        } else if (seleccion.startsWith("3")) {
            return CondicionClimatica.EXTREMO;
        }

        return null;
    }

    /**
     * Calcula el impacto del clima en el tiempo de vuelta.
     * 
     * @param tiempoBase Tiempo base en segundos
     * @param clima Condición climática
     * @return Tiempo ajustado según el clima
     */
    public double calcularImpactoEnTiempo(double tiempoBase, CondicionClimatica clima) {
        // A menor velocidad, mayor tiempo de vuelta
        // Si modificador es 0.85 (85% de velocidad), el tiempo aumenta
        return tiempoBase / clima.getModificadorVelocidad();
    }

    /**
     * Calcula el consumo ajustado por el clima.
     * 
     * @param consumoBase Consumo base
     * @param clima Condición climática
     * @return Consumo ajustado
     */
    public double calcularConsumoAjustado(double consumoBase, CondicionClimatica clima) {
        return consumoBase * clima.getModificadorConsumo();
    }

    /**
     * Calcula el desgaste ajustado por el clima.
     * 
     * @param desgasteBase Desgaste base
     * @param clima Condición climática
     * @return Desgaste ajustado
     */
    public double calcularDesgasteAjustado(double desgasteBase, CondicionClimatica clima) {
        return desgasteBase * clima.getModificadorDesgaste();
    }

    /**
     * Genera una variación aleatoria adicional para simular condiciones impredecibles.
     * Devuelve un valor entre -5% y +5% (0.95 a 1.05).
     * 
     * @return Modificador aleatorio
     */
    public double generarVariacionAleatoria() {
        // Genera un valor entre -0.05 y +0.05
        double variacion = (random.nextDouble() * 0.10) - 0.05;
        return 1.0 + variacion;
    }
}
