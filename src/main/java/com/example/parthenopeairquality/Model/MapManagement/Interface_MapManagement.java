package com.example.gradleairquality.Model.MapManagement;
/* RENAME STRATEGY */
//Creiamo un'interfaccia comune alle classi per utilizzare il design
//pattern Strategy
/**
 * Interfaccia per implementare il design pattern 'Strategy' relativo a GeographicMap e Planimetry
 */

import com.example.gradleairquality.Model.UserManagement.Manager;
import com.example.gradleairquality.Model.UserManagement.Manager;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public interface Interface_MapManagement {

    LinkedList<Image> viewmap(Manager manager);

    void viewSensorPoints();

}
