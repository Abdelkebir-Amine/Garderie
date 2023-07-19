package com.garderie.model;

public class ClasseActivite {
    private int id, classe_id, activite_code;

    // Constructeur par défaut
    public ClasseActivite() {
    }

    // Constructeur avec paramètres
    public ClasseActivite(int id, int classe_id, int activite_code) {
        this.id = id;
        this.classe_id = classe_id;
        this.activite_code = activite_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClasse_id() {
        return classe_id;
    }

    public void setClasse_id(int classe_id) {
        this.classe_id = classe_id;
    }

    public int getActivite_code() {
        return activite_code;
    }

    public void setActivite_code(int activite_code) {
        this.activite_code = activite_code;
    }
}
