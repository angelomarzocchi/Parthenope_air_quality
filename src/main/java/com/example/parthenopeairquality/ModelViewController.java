package com.example.gradleairquality;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.Sensor;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.ThresholdExceedingChecker;
import com.example.gradleairquality.Model.ThresholdManagement.Threshold.ThresholdsMap;
import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.scene.control.Control;
import javafx.scene.image.Image;

import java.util.LinkedList;

public class ModelViewController extends Control {

    private Manager manager;
    private LinkedList<Sensor> sensors;


    ThresholdExceedingChecker thresholdExceedingChecker;
    private ThresholdsMap thresholds;


    private LinkedList<Image> map;


    private static ModelViewController modelViewController;

    private ModelViewController() {
    }

    public static ModelViewController getInstance() {
        if (modelViewController == null) {
            modelViewController = new ModelViewController();
        }
        return modelViewController;
    }

    public Manager getManager() {
        return manager;
    }

    public LinkedList<Sensor> getSensors() {
        return sensors;
    }

    public ThresholdsMap getThresholds() {
        return thresholds;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setSensors(LinkedList<Sensor> sensors) {
        this.sensors = sensors;

        if (thresholdExceedingChecker == null) {
            thresholdExceedingChecker = new ThresholdExceedingChecker();
        }
        for (Sensor s : sensors) {
            s.addSubscriber(thresholdExceedingChecker);
        }


    }

    public void setThresholds(ThresholdsMap thresholds) {
        this.thresholds = thresholds;
        if (thresholdExceedingChecker == null) {
            thresholdExceedingChecker = new ThresholdExceedingChecker();
        }
        for (measureType m : measureType.values()) {
            thresholds.getThresholds().get(m).addSubscriber(thresholdExceedingChecker);
        }
    }

    public LinkedList<Image> getMap() {
        return map;
    }

    public void setMap(LinkedList<Image> map) {
        this.map = map;
    }

    public ThresholdExceedingChecker getThresholdExceedingChecker() {
        return thresholdExceedingChecker;
    }

    public void setThresholdExceedingChecker(ThresholdExceedingChecker thresholdExceedingChecker) {
        this.thresholdExceedingChecker = thresholdExceedingChecker;
    }

    public void free(){
       modelViewController = null;
    }
}
