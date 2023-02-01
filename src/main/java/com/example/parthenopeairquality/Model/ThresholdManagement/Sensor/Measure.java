package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

import java.time.LocalDate;


/**
 * una singola misurazione
 */
public class Measure {


    /**
     * @param type      il tipo di misurazione, @see measureType.java
     * @param timestamp data della misurazione
     * @param value     valore della misurazione
     */
    public Measure(measureType type, LocalDate timestamp, int value) {
        this.type = type;
        this.timestamp = timestamp;
        this.value = value;
    }


    public LocalDate getTimestamp() {
        return timestamp;
    }


    public int getValue() {
        return value;
    }

    public measureType getType() {
        return type;
    }

    private final measureType type;
    private final int value;
    private final LocalDate timestamp;
}
