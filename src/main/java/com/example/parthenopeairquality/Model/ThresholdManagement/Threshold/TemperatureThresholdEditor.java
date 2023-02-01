package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.UserManagement.Manager;


import java.sql.SQLException;

/**
 * Editor della soglia Temperature
 */
public class TemperatureThresholdEditor extends ThresholdEditor {

    public TemperatureThresholdEditor(Editor nextEditor) {
        super(nextEditor);
    }

    @Override
    public boolean edit(EditRequest request, Manager manager) throws SQLException, ClassNotFoundException {

        if (request.getType() == measureType.TEMPERATURE) {
            if (request.getNewValue() >= -5 && request.getNewValue() <= 40) {
                ThresholdsMap.
                        getThresholdsInstance(manager)
                        .getThresholds().
                        get(measureType.TEMPERATURE).
                        setThreshold(request.getNewValue());
                return true;

            } else {
                return false;
            }
        } else if (getNextEditor() != null) {
            return getNextEditor().edit(request, manager);
        } else {
            return false;
        }
    }


}






