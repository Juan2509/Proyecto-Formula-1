package com.formula1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.JOptionPane;

public class MotorSimulacion {

    private GestorPilotos gestorPilotos;
    private GestorVehiculos gestorVehiculos;
    private GestorCircuitos gestorCircuitos;
    private GestorConfiguraciones gestorConfiguraciones;
    private GestorHistorial gestorHistorial;
    private GeneradorClima generadorClima;
    private Random random;

    public MotorSimulacion(
            GestorPilotos gestorPilotos,
            GestorVehiculos gestorVehiculos,
            GestorCircuitos gestorCircuitos,
            GestorConfiguraciones gestorConfiguraciones,
            GestorHistorial gestorHistorial) {

        this.gestorPilotos = gestorPilotos;
        this.gestorVehiculos = gestorVehiculos;
        this.gestorCircuitos = gestorCircuitos;
        this.gestorConfiguraciones = gestorConfiguraciones;
        this.gestorHistorial = gestorHistorial;
        this.generadorClima = new GeneradorClima();
        this.random = new Random();
    }

    /**
     * Inicia una simulación de clasificación completa.
     * El usuario debe seleccionar un circuito y la simulación
     * calcula los tiempos de todos los pilotos disponibles.
     */
    public void iniciarSimulacionClasificacion() {

        JOptionPane.showMessageDialog(null,
            "🏁 SIMULACIÓN DE CLASIFICACIÓN 🏁\n\n" +
            "Esta simulación calculará los tiempos de clasificación\n" +
            "de todos los pilotos en el circuito seleccionado.\n\n" +
            "Los tiempos dependerán de:\n" +
            "• Características del vehículo\n" +
            "• Habilidades del piloto\n" +
            "• Características del circuito\n" +
            "• Configuración del vehículo (si existe)\n" +
            "• Condiciones climáticas (generadas aleatoriamente)");

        // Mostrar lista de circuitos disponibles
        Map<String, Circuito> circuitos = gestorCircuitos.obtenerTodosCircuitos();
        
        if (circuitos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay circuitos registrados.");
            return;
        }

        StringBuilder listaCircuitos = new StringBuilder("Circuitos disponibles:\n\n");
        for (Circuito circuito : circuitos.values()) {
            listaCircuitos.append("• ").append(circuito.getNombre()).append("\n");
        }
        listaCircuitos.append("\nIngrese el nombre del circuito:");

        String nombreCircuito = JOptionPane.showInputDialog(listaCircuitos.toString());

        if (nombreCircuito == null || nombreCircuito.trim().isEmpty()) {
            return;
        }

        Circuito circuito = gestorCircuitos.obtenerCircuito(nombreCircuito);

        if (circuito == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el circuito especificado.");
            return;
        }

        // Generar clima aleatorio
        CondicionClimatica clima = generadorClima.generarYMostrarClima();

        // Realizar simulación con todos los pilotos
        realizarSimulacionCompleta(circuito, clima);
    }

    /**
     * Calcula el tiempo de vuelta de un piloto específico en un circuito.
     * 
     * FÓRMULA DE SIMULACIÓN:
     * 
     * tiempoBase = longitudCircuito * factorBase
     * 
     * Modificadores:
     * - Vehículo: velocidad y aceleración
     * - Piloto: habilidad y experiencia
     * - Circuito: longitud, desgaste y consumo
     * - Configuración: modo conducción, aerodinámica, neumáticos, combustible
     * - Clima: modificadores de velocidad, consumo y desgaste
     * - Variación aleatoria: ±2% para simular impredecibilidad
     * 
     * @param piloto El piloto
     * @param vehiculo El vehículo
     * @param circuito El circuito
     * @param configuracion La configuración (puede ser null)
     * @param clima Las condiciones climáticas
     * @return El tiempo calculado en segundos
     */
    public double calcularTiempoVuelta(
            Piloto piloto,
            Vehiculo vehiculo,
            Circuito circuito,
            ConfiguracionVehiculo configuracion,
            CondicionClimatica clima) {

        // 1. TIEMPO BASE del circuito
        // Usando la longitud y un factor de conversión aproximado
        // Factor base: aproximadamente 20 segundos por kilómetro a velocidad promedio F1
        double tiempoBase = circuito.getLongitud() * 20.0;

        // 2. MODIFICADOR DEL VEHÍCULO
        // Velocidad máxima: a mayor velocidad, menor tiempo
        // Normalizado: 350 km/h = 1.0
        double modificadorVelocidad = 350.0 / (vehiculo.getVelocidadMaxima() != null ? vehiculo.getVelocidadMaxima() : 350.0);

        // Aceleración: a mejor aceleración (menor tiempo 0-100), menor tiempo de vuelta
        // Normalizado: 2.5 segundos = 1.0
        double modificadorAceleracion = (vehiculo.getAceleracion() != null ? vehiculo.getAceleracion() : 2.5) / 2.5;

        double modificadorVehiculo = (modificadorVelocidad + modificadorAceleracion) / 2.0;

        // 3. MODIFICADOR DEL PILOTO
        // Habilidad y experiencia afectan el rendimiento
        double habilidad = piloto.getHabilidad() != null ? piloto.getHabilidad() : 50.0;
        double experiencia = piloto.getExperiencia() != null ? piloto.getExperiencia() : 50.0;

        // A mayor habilidad y experiencia, menor tiempo (mejor desempeño)
        // Normalizado: 75 de promedio = 1.0
        double modificadorHabilidad = 75.0 / habilidad;
        double modificadorExperiencia = 75.0 / experiencia;
        double modificadorPiloto = (modificadorHabilidad * 0.6 + modificadorExperiencia * 0.4);

        // 4. MODIFICADOR DEL CIRCUITO
        // Circuitos con más desgaste y consumo son más difíciles
        double factorCircuito = 1.0 + (circuito.getDesgasteNeumaticos() / 100.0) + (circuito.getConsumoCombustible() / 100.0);

        // 5. MODIFICADOR DE CONFIGURACIÓN
        double modificadorConfiguracion = 1.0;
        if (configuracion != null) {
            modificadorConfiguracion = calcularModificadorConfiguracion(configuracion);
        }

        // 6. MODIFICADOR DE CLIMA
        double modificadorClima = 1.0 / clima.getModificadorVelocidad();

        // 7. VARIACIÓN ALEATORIA (±2%)
        double variacionAleatoria = generadorClima.generarVariacionAleatoria();

        // CÁLCULO FINAL
        double tiempoFinal = tiempoBase 
                           * modificadorVehiculo 
                           * modificadorPiloto 
                           * factorCircuito 
                           * modificadorConfiguracion 
                           * modificadorClima 
                           * variacionAleatoria;

        return tiempoFinal;
    }

    /**
     * Calcula el modificador total de una configuración.
     */
    private double calcularModificadorConfiguracion(ConfiguracionVehiculo config) {
        double modificador = 1.0;

        // Modo de conducción
        switch (config.getModoConductor()) {
            case "Normal":
                modificador *= 1.0;
                break;
            case "Agresiva":
                modificador *= 0.92; // -8% tiempo (más rápido)
                break;
            case "Ahorro de combustible":
                modificador *= 1.08; // +8% tiempo (más lento)
                break;
        }

        // Carga aerodinámica
        switch (config.getCargaAerodinamica()) {
            case "Baja":
                modificador *= 0.97; // Mejor en rectas
                break;
            case "Media":
                modificador *= 1.0;
                break;
            case "Alta":
                modificador *= 1.02; // Mejor en curvas pero más lento
                break;
        }

        // Presión de neumáticos
        switch (config.getPresionNeumaticos()) {
            case "Baja":
                modificador *= 0.98; // Mejor agarre
                break;
            case "Estándar":
                modificador *= 1.0;
                break;
            case "Alta":
                modificador *= 1.03; // Menos agarre
                break;
        }

        // Estrategia de combustible
        switch (config.getEstrategiaCombustible()) {
            case "Agresiva":
                modificador *= 0.95; // Más potencia
                break;
            case "Balanceada":
                modificador *= 1.0;
                break;
            case "Ahorro":
                modificador *= 1.05; // Menos potencia
                break;
        }

        return modificador;
    }

    /**
     * Realiza la simulación completa con todos los pilotos registrados.
     */
    private void realizarSimulacionCompleta(Circuito circuito, CondicionClimatica clima) {
        Map<Integer, Piloto> pilotos = gestorPilotos.obtenerTodosPilotos();
        Map<String, Vehiculo> vehiculos = gestorVehiculos.obtenerTodosVehiculos();

        if (pilotos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pilotos registrados.");
            return;
        }

        List<ResultadoClasificacion> resultados = new ArrayList<>();

        // Calcular tiempo para cada piloto
        for (Piloto piloto : pilotos.values()) {
            
            // Buscar vehículo del equipo del piloto
            Vehiculo vehiculo = buscarVehiculoPorEquipo(vehiculos, piloto.getEquipo());

            if (vehiculo == null) {
                // Si no hay vehículo del equipo, usar valores por defecto
                vehiculo = crearVehiculoPorDefecto(piloto.getEquipo());
            }

            // Buscar configuración del vehículo (si existe)
            ConfiguracionVehiculo configuracion = gestorConfiguraciones.obtenerConfiguracion(vehiculo.getModelo());

            // Calcular tiempo de vuelta
            double tiempo = calcularTiempoVuelta(piloto, vehiculo, circuito, configuracion, clima);

            // Crear resultado
            ResultadoClasificacion resultado = new ResultadoClasificacion(
                piloto.getId(),
                piloto.getNombre(),
                piloto.getEquipo(),
                tiempo,
                circuito.getNombre(),
                clima
            );

            resultados.add(resultado);
        }

        // Mostrar clasificación
        mostrarClasificacion(resultados, circuito.getNombre(), clima);

        // Preguntar si desea guardar la sesión
        int guardar = JOptionPane.showConfirmDialog(
                null,
                "¿Desea guardar esta sesión en el historial?",
                "Guardar sesión",
                JOptionPane.YES_NO_OPTION);

        if (guardar == JOptionPane.YES_OPTION) {
            gestorHistorial.guardarSesion(circuito.getNombre(), clima, resultados);
        }
    }

    /**
     * Busca un vehículo que pertenezca al equipo especificado.
     */
    private Vehiculo buscarVehiculoPorEquipo(Map<String, Vehiculo> vehiculos, String equipo) {
        for (Vehiculo vehiculo : vehiculos.values()) {
            if (vehiculo.getEquipo().equalsIgnoreCase(equipo)) {
                return vehiculo;
            }
        }
        return null;
    }

    /**
     * Crea un vehículo con valores por defecto para equipos sin vehículo registrado.
     */
    private Vehiculo crearVehiculoPorDefecto(String equipo) {
        return new Vehiculo(
            equipo,
            "Modelo Genérico",
            "Motor Genérico",
            350.0,  // Velocidad promedio
            2.5,    // Aceleración promedio
            new java.util.HashMap<>(),
            new java.util.HashMap<>()
        );
    }

    /**
     * Muestra la tabla de clasificación ordenada.
     */
    public void mostrarClasificacion(
            List<ResultadoClasificacion> resultados,
            String circuito,
            CondicionClimatica clima) {

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay resultados para mostrar.");
            return;
        }

        // Ordenar resultados por tiempo (menor a mayor)
        Collections.sort(resultados);

        // Asignar posiciones
        for (int i = 0; i < resultados.size(); i++) {
            resultados.get(i).setPosicion(i + 1);
        }

        // Construir tabla
        StringBuilder tabla = new StringBuilder();
        tabla.append("🏁 CLASIFICACIÓN FINAL 🏁\n\n");
        tabla.append("Circuito: ").append(circuito).append("\n");
        tabla.append("Clima: ").append(clima.getNombre()).append("\n\n");
        tabla.append("════════════════════════════════════════\n\n");

        for (ResultadoClasificacion resultado : resultados) {
            tabla.append(String.format("P%-2d | %-20s | %-18s | %s\n",
                    resultado.getPosicion(),
                    resultado.getNombrePiloto(),
                    resultado.getEquipo(),
                    resultado.getTiempoFormateado()));
        }

        tabla.append("\n════════════════════════════════════════\n");
        tabla.append("\n🏆 POLE POSITION: ").append(resultados.get(0).getNombrePiloto());

        JOptionPane.showMessageDialog(null, tabla.toString());
    }
}
