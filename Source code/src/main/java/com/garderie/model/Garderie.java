package com.garderie.model;

public class Garderie {
    private String code_fiscal, nom, adresse, telephone, mail;

    // Constructeur par défaut
    public Garderie() {
    }

    // Constructeur avec paramètres
    public Garderie(String code_fiscal, String nom, String adresse, String telephone, String mail) {
        this.code_fiscal = code_fiscal;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
    }

    public String getCode_fiscal() {
        return code_fiscal;
    }

    public void setCode_fiscal(String code_fiscal) {
        this.code_fiscal = code_fiscal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
