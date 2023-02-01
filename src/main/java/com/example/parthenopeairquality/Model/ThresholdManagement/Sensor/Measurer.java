package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interfaccia comune a Sensor e a SensorDecorator.
 */
public interface Measurer {
    ArrayList<Measure> getMeasures();

    ArrayList<Measure> getMeasures(LocalDate timestamp);

    void addMeasure(Measure measure);

}
