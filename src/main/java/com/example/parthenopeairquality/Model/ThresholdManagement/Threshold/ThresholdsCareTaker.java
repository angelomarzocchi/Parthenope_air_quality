package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.UserManagement.Manager;

import java.sql.SQLException;
import java.util.Stack;

/**
 * Classe che contiene gli stati delle soglie (Memento) precedenti
 */
public class ThresholdsCareTaker {
    private final Stack<ThresholdsMap.Memento> mementos;
    private final Manager loggedIn;

    public ThresholdsCareTaker(Manager manager) {
        mementos = new Stack<>();
        loggedIn = manager;

    }

    /**
     * Ripristina le soglie precedenti
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void undo() throws SQLException, ClassNotFoundException {
        if (mementos.size() > 0) {
            ThresholdsMap.getThresholdsInstance(loggedIn).restoreFromSnapshot(mementos.pop());
        }
    }

    /**
     * Salva lo stato attuale delle soglie
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void saveSnapshot() throws SQLException, ClassNotFoundException {
        mementos.add(ThresholdsMap.getThresholdsInstance(loggedIn).getSnapshot());
    }

}
