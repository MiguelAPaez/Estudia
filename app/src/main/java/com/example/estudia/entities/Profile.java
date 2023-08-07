package com.example.estudia.entities;

import java.io.Serializable;

public class Profile implements Serializable {
    String email;
    String password;

    public Profile(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
