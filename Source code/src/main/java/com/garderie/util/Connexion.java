package com.garderie.util;

import java.sql.*;

public class Connexion {
    private Connection cnx;

    public Connexion() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        cnx = DriverManager.getConnection("jdbc:mysql://HOSTNAME:PORT/garderie", "DB_USERNAME", "DB_PASSWORD");
    }

    public Connection getCnx() {
        return cnx;
    }

}

