
package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

/**
 * Enumerazione che definisce i valori di default delle soglie,utilizzata da ThresholdsMap per ripristinare le soglie
 */
public enum defaultValues {
    TEMPERATURE(25, measureType.TEMPERATURE),
    HUMIDITY(50, measureType.HUMIDITY),
    CARBON(1000, measureType.CARBON),
    WIND(15, measureType.WIND),
    PM10(50, measureType.PM10),
    PM2(50, measureType.PM2);


    public final int number;
    public final measureType type;


    defaultValues(int number, measureType type) {
        this.number = number;
        this.type = type;
    }
}
