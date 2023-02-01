package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

/**
 * Interfaccia per implementare Observer (lato Osservante)
 */
public interface ThresholdUpdateListener {

    void update(int newValue, measureType type, Publisher p);

}
