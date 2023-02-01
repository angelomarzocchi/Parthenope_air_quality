package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

/**
 * Enumerazione per le unitá di misura delle variabili ambientali
 */
public enum Yardstick {
    TEMPERATURE(measureType.TEMPERATURE, " °C"),
    HUMIDITY(measureType.HUMIDITY, "%"),
    CARBON(measureType.CARBON, "ppm"),
    WIND(measureType.WIND, "Km/h"),
    PM10(measureType.PM10, " µg/m3"),
    PM2(measureType.PM2, " µg/m3");


    Yardstick(measureType type, String symbol) {
        this.symbol = symbol;
        this.type = type;
    }

    public measureType getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }


    private final measureType type;
    private final String symbol;


}
