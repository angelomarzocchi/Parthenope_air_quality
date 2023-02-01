package com.example.gradleairquality.Model.MapManagement;

import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 * La Planimetria è composta da piani che a sua volta sono composte da stanze
 */
public class Planimetry implements Interface_MapManagement {
    public Planimetry(String buildingName, Double altitudePlanimetry, Double longitudePlanimetry, Double squareMetersPlanimetry) {
        this.buildingName = buildingName;
        this.altitudePlanimetry = altitudePlanimetry;
        this.longitudePlanimetry = longitudePlanimetry;
        this.squareMetersPlanimetry = squareMetersPlanimetry;
        totalPlan = new ArrayList<Plan>();
    }

    Scanner scanner = new Scanner(System.in);

    /**
     * Planimetria dei 4 piani principalli della Parthenope più il piano terra
     */
    @Override
    public LinkedList<Image> viewmap(Manager manager) {
        LinkedList<Image> myPicture = new LinkedList<>();
        int i = 1;
        while (i < 5) {
            String imagePath = "com/example/gradleairquality/FileSystem/piano" + "_" + i + "_" + manager.getGovernanceArea().getNome().replace(" ", "_") + ".jpg";
            myPicture.add(new Image(imagePath));
            i++;
        }

        return myPicture;

    }

    @Override
    public void viewSensorPoints() {

    }


    private final String buildingName;
    private final Double altitudePlanimetry;
    private final Double longitudePlanimetry;
    private final Double squareMetersPlanimetry;
    private final ArrayList<Plan> totalPlan;

}
