package com.example.gradleairquality.Model.ThresholdManagement.Sensor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Estensione di SensorDecorator per aggiungere la capacitá di
 * effettuare misurazioni di Temperatura
 */
public class TemperatureDecorator extends SensorDecorator {

    public TemperatureDecorator(Measurer measurer) {
        super(measurer);

    }

    @Override
    public ArrayList<Measure> getMeasures() {
        ArrayList<Measure> temp = super.getMeasures();
        temp = (ArrayList<Measure>) temp.stream().filter(m -> m.getType() == measureType.TEMPERATURE).collect(Collectors.toList());
        return temp;
    }

    @Override
    public ArrayList<Measure> getMeasures(LocalDate timestamp) {
        return (ArrayList<Measure>) super.getMeasures(timestamp).stream().
                filter(m -> m.getType() == measureType.TEMPERATURE).
                collect(Collectors.toList());
    }


}
