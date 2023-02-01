package com.example.gradleairquality.Model.ThresholdManagement.Threshold;

import com.example.gradleairquality.Model.UserManagement.Manager;

import java.sql.SQLException;

/**
 * Interfaccia per rendere una classe un modificatore di soglie
 */
public interface Editor {
    boolean edit(EditRequest request, Manager manager) throws SQLException, ClassNotFoundException;
}
