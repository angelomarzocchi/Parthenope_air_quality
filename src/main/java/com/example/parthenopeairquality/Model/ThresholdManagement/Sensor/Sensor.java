package com.example.gradleairquality.Model.ThresholdManagement.Sensor;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.Publisher;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.ThresholdUpdateListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * classe per modellare il sensore base
 */
public class Sensor implements Measurer, Publisher {


    public Sensor(String location, String code, Double latitude, Double longitude) {
        online = true;
        this.location = location;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        measures = new ArrayList<>();
        subs = new ArrayList<>();
    }



    public Boolean isOnline(){return online;}

    public void setOnlineStatus(Boolean onlineStatus){
        this.online = onlineStatus;
    }

    public String getLocation() {
        return location;
    }

    public String getCode() {
        return code;
    }

    @Override
    public ArrayList<Measure> getMeasures() {
        return measures;
    }

    @Override
    public ArrayList<Measure> getMeasures(LocalDate timestamp) {
        return (ArrayList<Measure>) measures.stream().
                filter(m -> m.getTimestamp().isEqual(timestamp)).collect(Collectors.toList());
    }


    @Override
    public void addMeasure(Measure measure) {
    if(this.isOnline()) {
        measures.add(measure);
        broadcastChanges(measure);
    } else{
        System.out.println("The sensor is not online");
    }

    }

    @Override
    public void addSubscriber(ThresholdUpdateListener t) {
        subs.add(t);
    }

    @Override
    public void removeSubscriber(ThresholdUpdateListener t) {
        subs.remove(t);
    }

    public void broadcastChanges(Measure measure) {
        for (ThresholdUpdateListener t : subs) {
            t.update(measure.getValue(), measure.getType(), this);
        }
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    private final String location;
    private Boolean online;
    private final String code;
    private final Double latitude;
    private final Double longitude;
    private final ArrayList<Measure> measures;
    private final ArrayList<ThresholdUpdateListener> subs;


}