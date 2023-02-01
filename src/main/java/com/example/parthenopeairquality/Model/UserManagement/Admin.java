package com.example.gradleairquality.Model.UserManagement;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;


/**
 * Classe che estente User per modellare un Admin della piattaforma
 */
public class Admin extends User {


    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }


    //Implementazione metodo login
    @Override
    public boolean logIn() throws SQLException, ClassNotFoundException {
        ResultSet set = DBService.logIn(username, password);

        if (set != null) {
            if (set.getString(8).equals("AD")) {

                this.name = set.getString(2);
                this.surname = set.getString(3);
                this.birthDate = set.getDate(4);
                return true;
            }
        }
        return false;

    }


    //Implementazione metodo logout
    @Override
    protected void logOut() throws InputMismatchException {


    }
}
