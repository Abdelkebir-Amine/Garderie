package com.garderie.model;

import java.io.Serializable;

public class Activite implements Serializable {

    private String designation, employe_cin;
    private int code;

    // Constructeur par défaut
    public Activite() {
    }

    // Constructeur avec paramètres
    public Activite(int code, String designation, String employe_cin) {
        this.code = code;
        this.designation = designation;
        this.employe_cin = employe_cin;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmploye_cin() {
        return employe_cin;
    }

    public void setEmploye_cin(String employe_cin) {
        this.employe_cin = employe_cin;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
