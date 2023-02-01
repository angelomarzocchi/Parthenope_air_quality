package com.example.gradleairquality.Model.UserManagement;

import com.example.gradleairquality.Model.ThresholdManagement.Threshold.Comuni;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe che estente User per modellare un Manager di un'area
 */
public class Manager extends User {
    private Comuni governanceArea;


    private String type;

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setGovernanceArea(Comuni governanceArea) {
        this.governanceArea = governanceArea;
    }

    //Manager zona di competenza
    public Comuni getGovernanceArea() {
        return governanceArea;
    }

    public String getType() {
        return type;
    }


    //Implementazione metodo login
    @Override
    public boolean logIn() throws SQLException, ClassNotFoundException {
        ResultSet set = DBService.logIn(username, password);

        if (set != null) {
            if (set.getString(8).equals("MG")) {

                this.name = set.getString(2);
                this.surname = set.getString(3);
                this.governanceArea = Comuni.valueOf(set.getString(10).replace(" ", "_"));
                this.birthDate = set.getDate(4);
                this.type = set.getString(9);
                return true;
            }
        }
        return false;

    }


    //Implementazione metodo logout
    @Override
    protected void logOut() {


    }
}
