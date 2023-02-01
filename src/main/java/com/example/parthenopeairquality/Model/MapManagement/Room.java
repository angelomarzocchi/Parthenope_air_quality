package com.example.gradleairquality.Model.MapManagement;

/**
 * Abbiamo di creare una classe contenente le informazioni relative ad una stanza in ogni piano essendo che appunto
 * potrebbero esserci più stanze
 */

public class Room {
    public Room(String TypeRoom, Double metresSquare, Double lenghtRoom) {
        this.TypeRoom = TypeRoom;
        this.metresSquare = metresSquare;
        this.lenghtRoom = lenghtRoom;
    }

    private final String TypeRoom;
    private final Double metresSquare;
    private final Double lenghtRoom;

    public String getTypeRoom() {
        return TypeRoom;
    }

    public Double getMetresSquare() {
        return metresSquare;
    }

    public Double getLenghtRoom() {
        return lenghtRoom;
    }
}
