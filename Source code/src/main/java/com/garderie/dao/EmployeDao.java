package com.garderie.dao;

import com.garderie.model.Employe;

import java.sql.SQLException;
import java.util.List;

public interface EmployeDao {
    boolean ajouterEmploye(Employe employe) throws SQLException, ClassNotFoundException;
    void modifierEmploye(Employe employe) throws SQLException, ClassNotFoundException;
    void supprimerEmploye(String cin) throws SQLException, ClassNotFoundException;
    List<Employe> getTousLesEmployes() throws SQLException, ClassNotFoundException;
    Employe getEmployeByCin(String cin) throws SQLException, ClassNotFoundException;
    List<Employe> rechercherEmployes(String motCle) throws SQLException, ClassNotFoundException;
}
