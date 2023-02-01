package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.UserManagement.Manager;


import java.sql.SQLException;

/**
 * Editor della soglia Carbon
 */
public class CarbonThresholdEditor extends ThresholdEditor {

    public CarbonThresholdEditor(Editor nextEditor) {
        super(nextEditor);
    }

    @Override
    public boolean edit(EditRequest request, Manager manager) throws SQLException, ClassNotFoundException {
        if (request.getType() == measureType.CARBON) {
            if (request.getNewValue() >= 0 && request.getNewValue() <= 12800) {
                ThresholdsMap.
                        getThresholdsInstance(manager)
                        .getThresholds().
                        get(measureType.CARBON).
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

