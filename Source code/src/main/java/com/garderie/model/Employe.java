package com.garderie.model;

import java.io.Serializable;

public class Employe implements Serializable {
    private  byte[] image;
    private String cin, nom, prenom, telephone, pere_prenom, date_naissance, adresse, motDePasse;
    private  boolean is_directeur;

    // Constructeur par défaut
    public Employe() {
    }

    // Constructeur avec paramètres
    public Employe(String cin, String nom, String prenom, String telephone, String pere_prenom, String date_naissance,
                   String adresse, String motDePasse, byte[] image, boolean is_directeur) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.pere_prenom = pere_prenom;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.image = image;
        this.is_directeur = is_directeur;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPere_prenom() {
        return pere_prenom;
    }

    public void setPere_prenom(String pere_prenom) {
        this.pere_prenom = pere_prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean getIs_directeur() {
        return is_directeur;
    }

    public void setIs_directeur(boolean is_directeur) {
        this.is_directeur = is_directeur;
    }
}
