package com.garderie.dao;

import com.garderie.model.Activite;
import com.garderie.util.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActiviteDaoImpl implements ActiviteDao{

    @Override
    public boolean ajouterActivite(Activite activite) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "INSERT INTO activite (designation,employe_cin) VALUES (?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setString(1, activite.getDesignation());
            statement.setString(2, activite.getEmploye_cin());
            rowInserted = statement.executeUpdate() > 0;

            statement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return rowInserted;
    }

    @Override
    public void modifierActivite(Activite activite) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE activite SET designation=?, employe_cin=? WHERE code=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, activite.getDesignation());
            preparedStatement.setString(2, activite.getEmploye_cin());
            preparedStatement.setInt(3, activite.getCode());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void supprimerActivite(int code) throws SQLException, ClassNotFoundException{
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "DELETE FROM activite WHERE code = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, code);
            statement.executeUpdate();
            statement.close();
            cnx.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Activite> getTousLesActivites() throws SQLException, ClassNotFoundException {
        List<Activite> activites = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM activite");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activite activite = new Activite();
                activite.setCode(rs.getInt("code"));
                activite.setDesignation(rs.getString("designation"));
                activite.setEmploye_cin(rs.getString("employe_cin"));
                activites.add(activite);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des activites : " + e.getMessage());
            throw e;
        }
        return activites;
    }

    @Override
    public Activite getActiviteByCode(int code) throws SQLException, ClassNotFoundException {
        Activite activite = null;
        ResultSet rs = null;
        try {
            Connection cnx = new Connexion().getCnx();
            String query = "SELECT * FROM activite WHERE code = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, code);
            rs = statement.executeQuery();
            while (rs.next()) {
                activite = new Activite();
                activite.setCode(rs.getInt("code"));
                activite.setDesignation(rs.getString("designation"));
                activite.setEmploye_cin(rs.getString("employe_cin"));

                statement.close();
                cnx.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération d'activite : " + e.getMessage());
            throw e;
        }
        return activite;
    }

    @Override
    public List<Activite> rechercherActivites(String motCle) throws SQLException, ClassNotFoundException {
        List<Activite> activites = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM activite WHERE code LIKE ? OR designation LIKE ?");
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activite activite = new Activite();
                activite.setCode(rs.getInt("code"));
                activite.setDesignation(rs.getString("designation"));
                activite.setEmploye_cin(rs.getString("employe_cin"));
                activites.add(activite);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des activites : " + e.getMessage());
            throw e;
        }
        return activites;
    }
}
