package com.garderie.dao;

import com.garderie.model.Garderie;

import java.sql.SQLException;

public interface GarderieDao {
    void modifierGarderie(Garderie garderie) throws SQLException, ClassNotFoundException;
}
