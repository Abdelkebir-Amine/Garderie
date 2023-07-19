package com.garderie.dao;

import com.garderie.model.ClasseActivite;

import java.sql.SQLException;

public interface ClasseActiviteDao {
    boolean ajouterClasseActivite(ClasseActivite classeActivite) throws SQLException, ClassNotFoundException;
    void modifierClasseActivite(ClasseActivite classeActivite) throws SQLException, ClassNotFoundException;
    void supprimerClasseActivite(int id) throws SQLException, ClassNotFoundException;
}
