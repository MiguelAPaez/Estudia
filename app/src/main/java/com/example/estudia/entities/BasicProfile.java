package com.example.estudia.entities;

public class BasicProfile extends Profile {
    String name;
    String lastName;
    String phone;
    String age;
    String stratum;
    String gender;

    public BasicProfile(String email, String name, String lastName, String phone, String age, String stratum, String gender) {
        super(email);
        this.name = name;
        this.lastName = lastName;
        this.phone = "+57" + phone;
        this.age = age;
        this.stratum = stratum;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStratum() {
        return stratum;
    }

    public void setStratum(String stratum) {
        this.stratum = stratum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
