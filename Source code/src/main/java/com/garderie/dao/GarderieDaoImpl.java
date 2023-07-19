package com.garderie.dao;

import com.garderie.model.Garderie;
import com.garderie.util.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GarderieDaoImpl implements GarderieDao{

    @Override
    public void modifierGarderie(Garderie garderie) throws SQLException, ClassNotFoundException {
        try  {
            Connection cnx = new Connexion().getCnx();
            String query = "UPDATE garderie SET nom=?, adresse=?, telephone=?, mail=? WHERE code_fiscal=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);

            preparedStatement.setString(1, garderie.getNom());
            preparedStatement.setString(2, garderie.getAdresse());
            preparedStatement.setString(3, garderie.getTelephone());
            preparedStatement.setString(4, garderie.getMail());
            preparedStatement.setString(5, garderie.getCode_fiscal());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            cnx.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
