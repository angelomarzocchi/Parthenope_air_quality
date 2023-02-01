package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

/**
 * Richiesta di modifica di una soglia
 */
public class EditRequest {

    public EditRequest(int newValue, measureType type) {
        this.newValue = newValue;
        this.type = type;
    }

    public int getNewValue() {
        return newValue;
    }

    public measureType getType() {
        return type;
    }

    private final int newValue;
    private final measureType type;


}
