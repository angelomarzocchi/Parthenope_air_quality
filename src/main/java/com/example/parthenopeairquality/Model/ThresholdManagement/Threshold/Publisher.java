package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

/**
 * interfaccia per implementare Observer (lato Osservato)
 */
public interface Publisher {

    void addSubscriber(ThresholdUpdateListener t);
    void removeSubscriber(ThresholdUpdateListener t);

}
