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
import javax.imageio.ImageReader;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 * La mappa geografica è composta da quadranti
 */
public class GeographicMap implements Interface_MapManagement {
    public GeographicMap(Double latitude, Double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
        parts = new ArrayList<Quadrant>();
    }

    Scanner scanner = new Scanner(System.in);
    private boolean geoMap;

    /**
     *Mappa geografica dei comuni di Napoli
     */

    /**
     * @param manager
     * @return una lista di immagini corrispondenti all'area del manager
     */
    @Override
    public LinkedList<Image> viewmap(Manager manager) {
        String imagePath = "com/example/gradleairquality/FileSystem/mappa" + "_" + manager.getGovernanceArea().getNome().replace(" ", "_") + ".jpg";

        LinkedList<Image> myPicture = new LinkedList<>();
        myPicture.add(new Image(imagePath));


        return myPicture;
    }

    @Override
    public void viewSensorPoints() {

    }


    private final Double latitude;
    private final Double longitude;
    private final ArrayList<Quadrant> parts;
}
