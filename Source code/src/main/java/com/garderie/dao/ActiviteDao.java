package com.garderie.dao;

import com.garderie.model.Activite;

import java.sql.SQLException;
import java.util.List;

public interface ActiviteDao {
    boolean ajouterActivite(Activite activite) throws SQLException, ClassNotFoundException;
    void modifierActivite(Activite activite) throws SQLException, ClassNotFoundException;
    void supprimerActivite(int code) throws SQLException, ClassNotFoundException;
    List<Activite> getTousLesActivites() throws SQLException, ClassNotFoundException;
    Activite getActiviteByCode(int code) throws SQLException, ClassNotFoundException;
    List<Activite> rechercherActivites(String motCle) throws SQLException, ClassNotFoundException;
}
