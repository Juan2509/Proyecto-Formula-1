package com.formula1;

public enum CondicionClimatica {
    
    SECO("Seco", 1.0, 1.0, 1.0),
    LLUVIOSO("Lluvioso", 0.85, 1.15, 1.25),
    EXTREMO("Extremo", 0.70, 1.30, 1.50);

    private final String nombre;
    private final double modificadorVelocidad;
    private final double modificadorConsumo;
    private final double modificadorDesgaste;

    CondicionClimatica(String nombre, double modificadorVelocidad, double modificadorConsumo, double modificadorDesgaste) {
        this.nombre = nombre;
        this.modificadorVelocidad = modificadorVelocidad;
        this.modificadorConsumo = modificadorConsumo;
        this.modificadorDesgaste = modificadorDesgaste;
    }

    public String getNombre() {
        return nombre;
    }

    public double getModificadorVelocidad() {
        return modificadorVelocidad;
    }

    public double getModificadorConsumo() {
        return modificadorConsumo;
    }

    public double getModificadorDesgaste() {
        return modificadorDesgaste;
    }

    public String getDescripcion() {
        switch (this) {
            case SECO:
                return "Condiciones ideales para las carreras.\n" +
                       "• Velocidad: Normal\n" +
                       "• Consumo: Normal\n" +
                       "• Desgaste: Normal";
            
            case LLUVIOSO:
                return "Lluvia moderada, pista mojada.\n" +
                       "• Velocidad: -15%\n" +
                       "• Consumo: +15%\n" +
                       "• Desgaste: +25%\n" +
                       "• Mayor riesgo de aquaplaning";
            
            case EXTREMO:
                return "Condiciones extremas: lluvia intensa o tormenta.\n" +
                       "• Velocidad: -30%\n" +
                       "• Consumo: +30%\n" +
                       "• Desgaste: +50%\n" +
                       "• Visibilidad muy reducida\n" +
                       "• Alto riesgo de accidente";
            
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
