package com.garderie.model;

import java.io.Serializable;

public class Classe implements Serializable {
    private int id;
    private String nom;

    // Constructeur par défaut
    public Classe() {
    }

    // Constructeur avec paramètres
    public Classe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
