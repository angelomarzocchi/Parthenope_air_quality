package com.example.gradleairquality.Model.PasswordRetrievement;

import com.example.gradleairquality.Model.UserManagement.User;

import java.util.Objects;

/**
 * classe per ripristinare la password
 */
public class RetrPassword {

    User u;
    String mail;
    String pass;
    String newPass = "ggh02";


    public void sendMail() {

        if (Objects.equals(mail, u.getEmail())) {
            this.changePassword();
        } else {
            this.sendMail();
        }
    }


    public void changePassword() {

        if (!Objects.equals(pass, u.getPassword())) {
            pass = newPass;
        } else {
            this.changePassword();
        }

    }

}
