package com.example.gradleairquality.Model.CompareManagement;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.*;


import java.util.ArrayList;

/**
 * classe che permette di comparare le misurazioni di 2 sensori
 */
public class SensorComparator {


    private SensorComparator() {
    }

    private SensorComparator(SensorDecorator firstSensor, SensorDecorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(CarbonDecorator firstSensor, CarbonDecorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(HumidityDecorator firstSensor, HumidityDecorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(PM2Decorator firstSensor, PM2Decorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(PM10Decorator firstSensor, PM10Decorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(TemperatureDecorator firstSensor, TemperatureDecorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    public SensorComparator(WindDecorator firstSensor, WindDecorator secondSensor) {
        this.firstSensor = firstSensor;
        this.secondSensor = secondSensor;
    }

    /**
     * @return un ArrayList di Comparison tra i 2 sensori,facendo il massimo numero di paragoni possibili.
     * Le comparazioni sono ordinate dalla meno recente alla piú recente.
     */
    public ArrayList<Comparison> compare() {
        ArrayList<Comparison> comparisons = new ArrayList<>();
        ArrayList<Measure> firstSensorMeasures = firstSensor.getMeasures();
        ArrayList<Measure> secondSensorMeasures = secondSensor.getMeasures();

        while (!firstSensorMeasures.isEmpty() && !secondSensorMeasures.isEmpty()) {
            comparisons.add(new Comparison(firstSensorMeasures.remove(0).getValue(),
                    secondSensorMeasures.remove(0).getValue()));
        }


        return comparisons;
    }


    SensorDecorator firstSensor;
    SensorDecorator secondSensor;

}

