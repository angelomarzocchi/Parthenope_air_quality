package com.example.gradleairquality.Model.CompareManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Classe per rappresentare una comparazione tra 2 valori
 */
public class Comparison {
    int firstSensorValue;
    int secondSensorValue;
    ComparisonResult result;

    public Comparison(int firstSensorValue, int secondSensorValue) {
        this.firstSensorValue = firstSensorValue;
        this.secondSensorValue = secondSensorValue;
        if (firstSensorValue > secondSensorValue) {
            result = ComparisonResult.FIRST_IS_GREATER;
        } else if (firstSensorValue < secondSensorValue) {
            result = ComparisonResult.SECOND_IS_GREATER;
        } else {
            result = ComparisonResult.EQUAL;
        }
    }

    public ComparisonResult getResult() {
        return result;
    }
}


