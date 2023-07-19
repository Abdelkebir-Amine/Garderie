package com.garderie.dao;

import com.garderie.model.Eleve;

import java.sql.SQLException;
import java.util.List;

public interface EleveDao {
    boolean ajouterEleve(Eleve eleve) throws SQLException, ClassNotFoundException;
    List<Eleve> getTousLesEleves() throws SQLException, ClassNotFoundException;
    void modifierEleve(Eleve eleve) throws SQLException, ClassNotFoundException;
    void supprimerEleve(int id) throws SQLException, ClassNotFoundException;
    Eleve getEleveById(int id) throws SQLException, ClassNotFoundException;
    List<Eleve> rechercherEleves(String motCle) throws SQLException, ClassNotFoundException;
}
