package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import java.util.Locale;

/**
 * Enumerazione per modellare i tipi di misurazione
 */
public enum measureType {
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity"),
    CARBON("Carbon"),
    WIND("Wind speed"),
    PM10("PM 10"),
    PM2("PM 2.5");

    public final String toString;

    measureType(String toString) {
        this.toString = toString;
    }


}
