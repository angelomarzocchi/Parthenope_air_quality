package com.example.gradleairquality.Model.CompareManagement;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.*;

import java.util.*;

/**
 * AreaEstimator é una classe che si occupa di calcolare le statistiche dell'area facendo la media
 * delle ultime misure dei sensori.
 */
public class AreaEstimator {

    List<Sensor> sensors;
    Map<measureType, Double> averages; //map to store the average

    public AreaEstimator(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Map<measureType, Double> getAverages() {
        averages = new HashMap<>();
        for (measureType m : measureType.values()) {
            getAverage(m, averages);
        }
        return averages;
    }

    private void getAverage(measureType averageType, Map<measureType, Double> average) {
        //Map<measureType, Double> average = new HashMap<>();
        double temp = 0.0;
        int numberOfMeasurement = 0;
        average.put(averageType, 0.0);

        SensorDecorator decorator = null;

        Comparator<Measure> measureComparator = (o1, o2) -> {
            if (o1.getTimestamp().isBefore(o2.getTimestamp())) {
                return -1;
            } else if (o1.getTimestamp().isEqual(o2.getTimestamp())) {
                return 0;
            } else {
                return 1;
            }
        };


        for (Sensor s : sensors) {
            switch (averageType) {
                case TEMPERATURE -> decorator = new TemperatureDecorator(s);
                case HUMIDITY -> decorator = new HumidityDecorator(s);
                case PM2 -> decorator = new PM2Decorator(s);
                case PM10 -> decorator = new PM10Decorator(s);
                case WIND -> decorator = new WindDecorator(s);
                case CARBON -> decorator = new CarbonDecorator(s);
            }
            List<Measure> measureToAdd = decorator.getMeasures().
                    stream()
                    .sorted(measureComparator.reversed()).toList();
            if (measureToAdd.size() > 0) {
                numberOfMeasurement++;
                temp += measureToAdd.get(0).getValue();
            }


        }
        temp = temp / numberOfMeasurement;
        average.replace(averageType, temp);
    }


}

