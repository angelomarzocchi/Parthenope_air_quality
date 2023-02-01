package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;

import java.util.ArrayList;


public class Threshold implements Publisher {
    /**
     * Classe per modellare una singola soglia.
     */
    public Threshold(measureType type) {
        subs = new ArrayList<>();
        this.type = type;
        for (defaultValues d : defaultValues.values()) {
            if (d.type == type) {
                threshold = d.number;
                break;
            }
        }
    }

    private Threshold() {
        //never use it
        type = null;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {

        this.threshold = threshold;
        broadcastChanges();

    }

    public measureType getType() {
        return type;
    }

    @Override
    public void addSubscriber(ThresholdUpdateListener t) {
        subs.add(t);
        t.update(this.getThreshold(), this.type, this);
    }

    @Override
    public void removeSubscriber(ThresholdUpdateListener t) {
        subs.remove(t);
    }

    public void broadcastChanges() {
        for (ThresholdUpdateListener t : subs) {
            t.update(this.getThreshold(), this.type, this);
        }
    }


    private int threshold;
    private final measureType type;
    private ArrayList<ThresholdUpdateListener> subs;


}
