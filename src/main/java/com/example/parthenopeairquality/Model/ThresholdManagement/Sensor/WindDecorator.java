package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Estensione di SensorDecorator per aggiungere la capacitá di
 * effettuare misurazioni di velocitá del vento
 */
public class WindDecorator extends SensorDecorator {
    public WindDecorator(Measurer measurer) {
        super(measurer);
    }

    @Override
    public ArrayList<Measure> getMeasures() {
        return (ArrayList<Measure>) super.getMeasures().stream().
                filter(m -> m.getType() == measureType.WIND).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<Measure> getMeasures(LocalDate timestamp) {
        return (ArrayList<Measure>) super.getMeasures(timestamp).stream().
                filter(m -> m.getType() == measureType.WIND).
                collect(Collectors.toList());
    }

}
