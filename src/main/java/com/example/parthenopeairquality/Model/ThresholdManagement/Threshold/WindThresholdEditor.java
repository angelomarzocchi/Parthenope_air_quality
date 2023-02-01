package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.UserManagement.Manager;

import java.sql.SQLException;

/**
 * Editor della soglia Wind Speed
 */
public class WindThresholdEditor extends ThresholdEditor {
    public WindThresholdEditor(Editor nextEditor) {
        super(nextEditor);
    }

    @Override
    public boolean edit(EditRequest request, Manager manager) throws SQLException, ClassNotFoundException {
        if (request.getType() == measureType.WIND) {
            if (request.getNewValue() >= 0 && request.getNewValue() <= 100) {
                ThresholdsMap.
                        getThresholdsInstance(manager)
                        .getThresholds().
                        get(measureType.WIND).
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
