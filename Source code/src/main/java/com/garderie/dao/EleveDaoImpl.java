package com.garderie.dao;

import com.garderie.model.Eleve;
import com.garderie.util.Connexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EleveDaoImpl implements  EleveDao{

    @Override
    public boolean ajouterEleve(Eleve eleve) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "INSERT INTO eleve (nom, prenom, pere_prenom, grand_pere_prenom, mere_nom, mere_prenom, pere_cin, " +
                    "pere_telephone, date_naissance, adresse, image, niveau_scolaire) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, eleve.getNom());
            statement.setString(2, eleve.getPrenom());
            statement.setString(3, eleve.getPere_prenom());
            statement.setString(4, eleve.getGrand_pere_prenom());
            statement.setString(5, eleve.getMere_nom());
            statement.setString(6, eleve.getMere_prenom());
            statement.setString(7, eleve.getPere_cin());
            statement.setString(8, eleve.getPere_telephone());
            statement.setDate(9, Date.valueOf(eleve.getDate_naissance()));
            statement.setString(10, eleve.getAdresse().toString());
            statement.setBytes(11, eleve.getImage());
            statement.setInt(12, eleve.getNiveau_scolaire());
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
    public void modifierEleve(Eleve eleve) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE eleve SET nom=?, prenom=?, pere_prenom=?, grand_pere_prenom=?, mere_nom=?, mere_prenom=?," +
                    " pere_cin=?, pere_telephone=?, date_naissance=?, adresse=?, image=?, niveau_scolaire=? WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, eleve.getNom());
            preparedStatement.setString(2, eleve.getPrenom());
            preparedStatement.setString(3, eleve.getPere_prenom());
            preparedStatement.setString(4, eleve.getGrand_pere_prenom());
            preparedStatement.setString(5, eleve.getMere_nom());
            preparedStatement.setString(6, eleve.getMere_prenom());
            preparedStatement.setString(7, eleve.getPere_cin());
            preparedStatement.setString(8, eleve.getPere_telephone());
            preparedStatement.setDate(9, Date.valueOf(eleve.getDate_naissance()));
            preparedStatement.setString(10, eleve.getAdresse());
            preparedStatement.setBytes(11, eleve.getImage());
            preparedStatement.setInt(12, eleve.getNiveau_scolaire());
            preparedStatement.setInt(13, eleve.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void supprimerEleve (int id) throws SQLException, ClassNotFoundException{
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "DELETE FROM eleve WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            cnx.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Eleve> getTousLesEleves() throws SQLException, ClassNotFoundException {
        List<Eleve> eleves = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM eleve");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Eleve eleve = new Eleve();
                eleve.setId(rs.getInt("id"));
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setPere_prenom(rs.getString("pere_prenom"));
                eleve.setGrand_pere_prenom(rs.getString("grand_pere_prenom"));
                eleve.setMere_nom(rs.getString("mere_nom"));
                eleve.setMere_prenom(rs.getString("mere_prenom"));
                eleve.setPere_cin(rs.getString("pere_cin"));
                eleve.setPere_telephone(rs.getString("pere_telephone"));
                eleve.setDate_naissance(rs.getString("date_naissance"));
                eleve.setAdresse(rs.getString("adresse"));
                eleve.setImage(rs.getBytes("image"));
                eleve.setNiveau_scolaire(rs.getInt("niveau_scolaire"));
                eleves.add(eleve);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des élèves : " + e.getMessage());
            throw e;
        }
        return eleves;
    }

    @Override
    public Eleve getEleveById(int id) throws SQLException, ClassNotFoundException {
        Eleve eleve = null;
        ResultSet rs = null;
        try {
            Connection cnx = new Connexion().getCnx();
            String query = "SELECT * FROM eleve WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setId(rs.getInt("id"));
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setPere_prenom(rs.getString("pere_prenom"));
                eleve.setGrand_pere_prenom(rs.getString("grand_pere_prenom"));
                eleve.setMere_nom(rs.getString("mere_nom"));
                eleve.setMere_prenom(rs.getString("mere_prenom"));
                eleve.setPere_cin(rs.getString("pere_cin"));
                eleve.setPere_telephone(rs.getString("pere_telephone"));
                eleve.setDate_naissance(rs.getString("date_naissance"));
                eleve.setAdresse(rs.getString("adresse"));
                eleve.setImage(rs.getBytes("image"));
                eleve.setNiveau_scolaire(rs.getInt("niveau_scolaire"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération d'élève : " + e.getMessage());
            throw e;
        }
        return eleve;
    }

    /*
    Cette Méthode prend en paramètre un mot clé à partir d'un champ texte,
    et rechercher un nom ou un prenom similaire à ce mot clè.
     */
    @Override
    public List<Eleve> rechercherEleves(String motCle) throws SQLException, ClassNotFoundException {
        List<Eleve> eleves = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM eleve WHERE nom LIKE ? OR prenom LIKE ?");
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Eleve eleve = new Eleve();
                eleve.setId(rs.getInt("id"));
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setPere_prenom(rs.getString("pere_prenom"));
                eleve.setGrand_pere_prenom(rs.getString("grand_pere_prenom"));
                eleve.setMere_nom(rs.getString("mere_nom"));
                eleve.setMere_prenom(rs.getString("mere_prenom"));
                eleve.setPere_cin(rs.getString("pere_cin"));
                eleve.setPere_telephone(rs.getString("pere_telephone"));
                eleve.setDate_naissance(rs.getString("date_naissance"));
                eleve.setAdresse(rs.getString("adresse"));
                eleve.setImage(rs.getBytes("image"));
                eleve.setNiveau_scolaire(rs.getInt("niveau_scolaire"));
                eleves.add(eleve);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des élèves : " + e.getMessage());
            throw e;
        }
        return eleves;
    }
}
