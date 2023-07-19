package com.garderie.dao;

import com.garderie.model.Employe;
import com.garderie.util.Connexion;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDaoImpl implements EmployeDao {

    @Override
    public boolean ajouterEmploye(Employe employe) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "INSERT INTO employe (cin, nom, prenom, telephone, pere_prenom, date_naissance, " +
                    "adresse, motDePasse, image, is_directeur) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setString(1, employe.getCin());
            statement.setString(2, employe.getNom());
            statement.setString(3, employe.getPrenom());
            statement.setString(4, employe.getTelephone());
            statement.setString(5, employe.getPere_prenom());
            statement.setDate(6, Date.valueOf(employe.getDate_naissance()));
            statement.setString(7, employe.getAdresse().toString());
            statement.setString(8, employe.getMotDePasse());
            statement.setBytes(9, employe.getImage());
            statement.setBoolean(10, employe.getIs_directeur());
            rowInserted = statement.executeUpdate() > 0;

            statement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return rowInserted;
    }

    /*
       Problème lors du modification de CIN (Pour le résoudre, il faut ajouter une colonne "id" au table "employe"
       et fixer comme un PRIMARY KEY).
     */
    @Override
    public void modifierEmploye(Employe employe) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE employe SET cin=?, nom=?, prenom=?, telephone=?, pere_prenom=?, date_naissance=?, adresse=?, motDePasse=?, image=?, is_directeur=? WHERE cin=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, employe.getCin());
            preparedStatement.setString(2, employe.getNom());
            preparedStatement.setString(3, employe.getPrenom());
            preparedStatement.setString(4, employe.getTelephone());
            preparedStatement.setString(5, employe.getPere_prenom());
            preparedStatement.setDate(6, Date.valueOf(employe.getDate_naissance()));
            preparedStatement.setString(7, employe.getAdresse());
            preparedStatement.setString(8, employe.getMotDePasse());
            preparedStatement.setBytes(9, employe.getImage());
            preparedStatement.setBoolean(10, employe.getIs_directeur());
            preparedStatement.setString(11, employe.getCin());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void supprimerEmploye(String cin) throws SQLException, ClassNotFoundException{
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "DELETE FROM employe WHERE cin = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, cin);
            statement.executeUpdate();
            statement.close();
            cnx.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Employe> getTousLesEmployes() throws SQLException, ClassNotFoundException {
        List<Employe> employes = new ArrayList<>();
        //cin, nom, prenom, telephone, pere_prenom, date_naissance, adresse, motDePasse, image, is_directeur
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM employe");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employe employe = new Employe();
                employe.setCin(rs.getString("cin"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setTelephone(rs.getString("telephone"));
                employe.setPere_prenom(rs.getString("pere_prenom"));
                employe.setDate_naissance(rs.getString("date_naissance"));
                employe.setAdresse(rs.getString("adresse"));
                employe.setMotDePasse(rs.getString("motDePasse"));
                employe.setImage(rs.getBytes("image"));
                employe.setIs_directeur(rs.getBoolean("is_directeur"));
                employes.add(employe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des employes : " + e.getMessage());
            throw e;
        }
        return employes;
    }

    @Override
    public Employe getEmployeByCin(String cin) throws SQLException, ClassNotFoundException {
        Employe employe = null;
        ResultSet rs = null;
        try {
            Connection cnx = new Connexion().getCnx();
            String query = "SELECT * FROM employe WHERE cin = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, cin);
            rs = statement.executeQuery();
            while (rs.next()) {
                employe = new Employe();
                employe.setCin(rs.getString("cin"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setTelephone(rs.getString("telephone"));
                employe.setPere_prenom(rs.getString("pere_prenom"));
                employe.setDate_naissance(rs.getString("date_naissance"));
                employe.setAdresse(rs.getString("adresse"));
                employe.setMotDePasse(rs.getString("motDePasse"));
                employe.setImage(rs.getBytes("image"));
                employe.setIs_directeur(rs.getBoolean("is_directeur"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération d'employe : " + e.getMessage());
            throw e;
        }
        return employe;
    }

    /*
    Cette Méthode prend en paramètre un mot clé à partir d'un champ texte,
    et rechercher un nom ou un prenom similaire à ce mot clè.
     */
    @Override
    public List<Employe> rechercherEmployes(String motCle) throws SQLException, ClassNotFoundException {
        List<Employe> employes = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM employe WHERE nom LIKE ? OR prenom LIKE ?");
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employe employe = new Employe();
                employe.setCin(rs.getString("cin"));
                employe.setNom(rs.getString("nom"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setTelephone(rs.getString("telephone"));
                employe.setPere_prenom(rs.getString("pere_prenom"));
                employe.setDate_naissance(rs.getString("date_naissance"));
                employe.setAdresse(rs.getString("adresse"));
                employe.setMotDePasse(rs.getString("motDePasse"));
                employe.setImage(rs.getBytes("image"));
                employe.setIs_directeur(rs.getBoolean("is_directeur"));
                employes.add(employe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des employes : " + e.getMessage());
            throw e;
        }
        return employes;
    }
}
