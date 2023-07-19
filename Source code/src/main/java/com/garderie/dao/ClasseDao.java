package com.garderie.dao;

import com.garderie.model.Classe;

import java.sql.SQLException;
import java.util.List;

public interface ClasseDao {
    boolean ajouterClasse(Classe classe) throws SQLException, ClassNotFoundException;
    void modifierClasse(Classe classe) throws SQLException, ClassNotFoundException;
    void supprimerClasse(int id) throws SQLException, ClassNotFoundException;
    List<Classe> getTousLesClasses() throws SQLException, ClassNotFoundException;
    Classe getClasseById(int id) throws SQLException, ClassNotFoundException;

    List<Classe> rechercherClasses(String motCle) throws SQLException, ClassNotFoundException;
}
