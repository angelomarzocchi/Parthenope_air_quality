package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Decorator di base di Sensor
 */
public class SensorDecorator implements Measurer {

    /**
     * classe Decorator di base.
     * Delega tutto il lavoro al
     * wrapped
     */
    public SensorDecorator(Measurer measurer) {
        this.wrapped = measurer;

    }


    @Override
    public ArrayList<Measure> getMeasures() {
        return wrapped.getMeasures();
    }

    @Override
    public ArrayList<Measure> getMeasures(LocalDate timestamp) {
        return wrapped.getMeasures(timestamp);
    }

    @Override
    public void addMeasure(Measure measure) {
        wrapped.addMeasure(measure);
    }


    private final Measurer wrapped;


}



