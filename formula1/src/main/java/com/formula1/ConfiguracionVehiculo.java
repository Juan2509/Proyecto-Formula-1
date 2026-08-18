package com.formula1;

public class ConfiguracionVehiculo {

    private String modeloVehiculo;
    private String modoConductor;
    private String cargaAerodinamica;
    private String presionNeumaticos;
    private String estrategiaCombustible;

    public ConfiguracionVehiculo(
            String modeloVehiculo,
            String modoConductor,
            String cargaAerodinamica,
            String presionNeumaticos,
            String estrategiaCombustible) {

        this.modeloVehiculo = modeloVehiculo;
        this.modoConductor = modoConductor;
        this.cargaAerodinamica = cargaAerodinamica;
        this.presionNeumaticos = presionNeumaticos;
        this.estrategiaCombustible = estrategiaCombustible;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public String getModoConductor() {
        return modoConductor;
    }

    public String getCargaAerodinamica() {
        return cargaAerodinamica;
    }

    public String getPresionNeumaticos() {
        return presionNeumaticos;
    }

    public String getEstrategiaCombustible() {
        return estrategiaCombustible;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public void setModoConductor(String modoConductor) {
        this.modoConductor = modoConductor;
    }

    public void setCargaAerodinamica(String cargaAerodinamica) {
        this.cargaAerodinamica = cargaAerodinamica;
    }

    public void setPresionNeumaticos(String presionNeumaticos) {
        this.presionNeumaticos = presionNeumaticos;
    }

    public void setEstrategiaCombustible(String estrategiaCombustible) {
        this.estrategiaCombustible = estrategiaCombustible;
    }

    @Override
    public String toString() {
        return "Configuración:\n" +
               "Vehículo: " + modeloVehiculo + "\n" +
               "Modo de conducción: " + modoConductor + "\n" +
               "Carga aerodinámica: " + cargaAerodinamica + "\n" +
               "Presión de neumáticos: " + presionNeumaticos + "\n" +
               "Estrategia de combustible: " + estrategiaCombustible;
    }
}
