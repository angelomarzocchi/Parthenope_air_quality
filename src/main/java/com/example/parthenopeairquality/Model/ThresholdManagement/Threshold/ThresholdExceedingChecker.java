package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.Sensor;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classe che si occupa di osservare i superamento delle soglie . Questi
 * eventi vengono salvati all'interno di una lista
 */
public class ThresholdExceedingChecker implements ThresholdUpdateListener {


    private final Map<measureType, Integer> thresholdValueHolder = new HashMap<>();
    private final Map<measureType, Integer> sensorValueHolder = new HashMap<>();


    private final LinkedList<String> events = new LinkedList<>();

    public void update(int newValue, measureType type, Publisher p) {
        if (p.getClass() == Sensor.class) {

            if (sensorValueHolder.containsKey(type)) {
                sensorValueHolder.replace(type, newValue);
            } else {
                sensorValueHolder.put(type, newValue);
            }

        } else { // source is THRESHOLD
            if (thresholdValueHolder.containsKey(type)) {
                thresholdValueHolder.replace(type, newValue);
            } else {
                thresholdValueHolder.put(type, newValue);
            }


        }
        if (p.getClass() == Sensor.class) {
            check(type, (Sensor) p);
        }
    }


    private void check(measureType type, Sensor p) {

        if (thresholdValueHolder.get(type) < sensorValueHolder.get(type))
            events.addFirst("Threshold " + type.toString + " exceeded by sensor " + p.getCode() + " ( "
                    + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))
                    + " )");

    }

    public LinkedList<String> getEvents() {
        return events;
    }


}
