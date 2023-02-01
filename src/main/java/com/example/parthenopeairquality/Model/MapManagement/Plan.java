package com.example.gradleairquality.Model.MapManagement;
import com.example.gradleairquality.Model.ThresholdManagement.Sensor.Sensor;

/**
 * Abbiamo creato una classe contenente le informazioni del piano in modo da poter definire molto meglio la planimetria
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe che modella il piano di un edificio¶
 */
public class Plan {
   public Plan (String numberPlan, Double metressquare, Double lenghtroom, String typeSimbol ){
        this.numberPlan=numberPlan;
        this.metressquare=metressquare;
        this.lenghtroom=lenghtroom;
        this.typeSimbol=typeSimbol;
        zone= new ArrayList<Room>();
    }


    public String getNumberPlan() {
        return numberPlan;
    }

    public Double getMetressquare() {
        return metressquare;
    }

    public Double getLenghtroom() {
        return lenghtroom;
    }

    public String getTypeSimbol() {
        return typeSimbol;
    }

    private final String numberPlan;
    private final Double metressquare;
    private final Double lenghtroom;
    private final String typeSimbol;
    private final ArrayList<Room> zone;
}
