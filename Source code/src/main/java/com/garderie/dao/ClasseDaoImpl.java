package com.garderie.dao;

import com.garderie.model.Classe;
import com.garderie.util.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClasseDaoImpl implements ClasseDao{

    @Override
    public boolean ajouterClasse(Classe classe) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "INSERT INTO classe (nom) VALUES (?)";
            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setString(1, classe.getNom());
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
    public void modifierClasse(Classe classe) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE classe SET nom=? WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, classe.getNom());
            preparedStatement.setInt(2, classe.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void supprimerClasse(int id) throws SQLException, ClassNotFoundException{
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "DELETE FROM classe WHERE id = ?";
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
    public List<Classe> getTousLesClasses() throws SQLException, ClassNotFoundException {
        List<Classe> classes = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM classe");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classes.add(classe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des classes : " + e.getMessage());
            throw e;
        }
        return classes;
    }

    @Override
    public Classe getClasseById(int id) throws SQLException, ClassNotFoundException {
        Classe classe = null;
        ResultSet rs = null;
        try {
            Connection cnx = new Connexion().getCnx();
            String query = "SELECT * FROM classe WHERE id = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération de classe : " + e.getMessage());
            throw e;
        }
        return classe;
    }

    @Override
    public List<Classe> rechercherClasses(String motCle) throws SQLException, ClassNotFoundException {
        List<Classe> classes = new ArrayList<>();
        try {
            Connection cnx = new Connexion().getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT * FROM classe WHERE nom LIKE ?");
            ps.setString(1, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setId(rs.getInt("id"));
                classe.setNom(rs.getString("nom"));
                classes.add(classe);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Une erreur est survenue lors de la récupération des élèves : " + e.getMessage());
            throw e;
        }
        return classes;
    }
}
