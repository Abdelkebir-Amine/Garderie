package com.garderie.dao;

import com.garderie.model.ClasseActivite;
import com.garderie.util.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClasseActiviteDaoImpl implements ClasseActiviteDao{

    @Override
    public boolean ajouterClasseActivite(ClasseActivite classeActivite) throws SQLException, ClassNotFoundException {
        boolean rowInserted = false;
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "INSERT INTO classe_activite (classe_id,activite_code) VALUES (?, ?)";
            PreparedStatement statement = cnx.prepareStatement(query);

            statement.setInt(1, classeActivite.getClasse_id());
            statement.setInt(2, classeActivite.getActivite_code());
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
    public void modifierClasseActivite(ClasseActivite classeActivite) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE classe_activite SET classe_id=?, activite_code=? WHERE id=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setInt(1, classeActivite.getClasse_id());
            preparedStatement.setInt(2, classeActivite.getActivite_code());
            preparedStatement.setInt(3, classeActivite.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void supprimerClasseActivite(int id) throws SQLException, ClassNotFoundException{
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "DELETE FROM classe_activite WHERE id = ?";
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
}
