package com.example.gradleairquality.Model.UserManagement;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Classe astratta che modella un utente generico
 */
public abstract class User {

    protected String name;
    protected String surname;
    protected String email;
    protected String username;
    protected String password;

    protected Date birthDate;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // Template method


    //Metodo "abstract" primitivo che deve essere implementato
    protected abstract boolean logIn() throws SQLException, ClassNotFoundException;


    //Metodo "abstract" primitivo che deve essere implementato
    protected abstract void logOut();


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

