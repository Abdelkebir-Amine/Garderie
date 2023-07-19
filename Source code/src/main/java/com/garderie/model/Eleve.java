package com.garderie.model;

import java.io.Serializable;

public class Eleve implements Serializable {
    private int id, niveau_scolaire;
    private  byte[] image;
    private String nom, prenom, pere_prenom, grand_pere_prenom, mere_nom, mere_prenom, pere_cin;
    private String date_naissance, adresse, pere_telephone;

    // Constructeur par défaut
    public Eleve() {
    }

    // Constructeur avec paramètres
    public Eleve(int id, String nom, String prenom, String prenomPere, String prenomGrandPerePere,
                 String nomMere, String prenomMere, String cinPere, String telPere, String dateNaissance,
                 String adresse, byte[] image, int niveauScolaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pere_prenom = prenomPere;
        this.grand_pere_prenom = prenomGrandPerePere;
        this.mere_nom = nomMere;
        this.mere_prenom = prenomMere;
        this.pere_cin = cinPere;
        this.pere_telephone = telPere;
        this.date_naissance = dateNaissance;
        this.adresse = adresse;
        this.image = image;
        this.niveau_scolaire = niveauScolaire;
    }

    // Getters et Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPere_prenom() {
        return pere_prenom;
    }

    public void setPere_prenom(String pere_prenom) {
        this.pere_prenom = pere_prenom;
    }

    public String getGrand_pere_prenom() {
        return grand_pere_prenom;
    }

    public void setGrand_pere_prenom(String grand_pere_prenom) {
        this.grand_pere_prenom = grand_pere_prenom;
    }

    public String getMere_nom() {
        return mere_nom;
    }

    public void setMere_nom(String mere_nom) {
        this.mere_nom = mere_nom;
    }

    public String getMere_prenom() {
        return mere_prenom;
    }

    public void setMere_prenom(String mere_prenom) {
        this.mere_prenom = mere_prenom;
    }

    public String getPere_cin() {
        return pere_cin;
    }

    public void setPere_cin(String pere_cin) {
        this.pere_cin = pere_cin;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getNiveau_scolaire() {
        return niveau_scolaire;
    }

    public void setNiveau_scolaire(int niveau_scolaire) {
        this.niveau_scolaire = niveau_scolaire;
    }

    public String getPere_telephone() {
        return pere_telephone;
    }

    public void setPere_telephone(String pere_telephone) {
        this.pere_telephone = pere_telephone;
    }
}
