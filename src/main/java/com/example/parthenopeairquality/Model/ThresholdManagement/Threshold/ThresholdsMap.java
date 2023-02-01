package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.ThresholdManagement.Sensor.measureType;
import com.example.gradleairquality.Model.UserManagement.DBService;
import com.example.gradleairquality.Model.UserManagement.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ThresholdsMap {
    /**
     * Classe che contiene le soglie.La prima vclta tutte le soglie avranno i valori di default
     * descritti nella classe defaultValues
     */

    public static class Memento {
        public Memento(Map<measureType, Threshold> thresholds) {
            this.thresholds = new HashMap<>();
            this.thresholds.put(measureType.TEMPERATURE, thresholds.get(measureType.TEMPERATURE).getThreshold());
            this.thresholds.put(measureType.HUMIDITY, thresholds.get(measureType.HUMIDITY).getThreshold());
            this.thresholds.put(measureType.CARBON, thresholds.get(measureType.CARBON).getThreshold());
            this.thresholds.put(measureType.PM2, thresholds.get(measureType.PM2).getThreshold());
            this.thresholds.put(measureType.PM10, thresholds.get(measureType.PM10).getThreshold());
            this.thresholds.put(measureType.WIND, thresholds.get(measureType.WIND).getThreshold());

        }

        public Map<measureType, Integer> getThresholds() {
            return thresholds;
        }


        private final Map<measureType, Integer> thresholds;

    }


    private ThresholdsMap() {
        thresholds = new HashMap<>();
    }


    protected Memento getSnapshot() {
        return new Memento(thresholds);
    }

    protected void restoreFromSnapshot(Memento previousState) {

        for (measureType t : measureType.values()) {
            thresholds.replace(t, new Threshold(t));
            thresholds.get(t).setThreshold(previousState.thresholds.get(t));
        }


    }


    /**
     * @return the only instance of ThresholdsMap
     */
    public static ThresholdsMap getThresholdsInstance(Manager manager) throws SQLException, ClassNotFoundException {

        if (thresholdsInstance == null) {
            //load map
            thresholdsInstance = new ThresholdsMap();
            ResultSet dbRequest = DBService.getThresholds(manager);
            if (dbRequest != null) {

                dbRequest.beforeFirst();
                while (dbRequest.next()) {
                    Threshold t = new Threshold(measureType.valueOf(dbRequest.getString(2)));
                    t.setThreshold(dbRequest.getInt(3));
                    thresholdsInstance.thresholds.put(t.getType(), t);
                }
            }

        }
        return thresholdsInstance;
    }

    public Map<measureType, Threshold> getThresholds() {
        return thresholds;
    }


    public void resetToDefaultValues(measureType m) {
        thresholds.replace(m,new Threshold(m));
    }

    public void saveChanges(Manager manager) throws SQLException, ClassNotFoundException {
        DBService.saveThresholds(manager);
    }


    private final Map<measureType, Threshold> thresholds;
    private static ThresholdsMap thresholdsInstance = null;

}
