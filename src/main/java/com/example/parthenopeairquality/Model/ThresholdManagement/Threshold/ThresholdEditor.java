package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.UserManagement.Manager;

import java.sql.SQLException;

/**
 * classe astratta per modellare il ThresholdEditor generale
 */
public abstract class ThresholdEditor implements Editor {

    public ThresholdEditor(Editor nextEditor) {
        this.nextEditor = nextEditor;
    }

    public abstract boolean edit(EditRequest request, Manager manager) throws SQLException, ClassNotFoundException;


    protected Editor getNextEditor() {
        return nextEditor;
    }

    public void setNextEditor(Editor nextEditor) {
        this.nextEditor = nextEditor;
    }

    private Editor nextEditor;


}
