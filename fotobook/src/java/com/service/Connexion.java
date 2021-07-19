package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion {
    //  Définir l’url de connexion avec le nom de la base donnée

    private static final String url = "jdbc:mysql://127.0.0.1:3306/fotobook?serverTimezone=UTC";
    //Les identifiants de connection de la bd
    private static final String user = "root";
    private static final String pwd = "root";
    private static Connection conn = null;

    public static Connection getConnexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnexion() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnexion(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void closeConnexion(PreparedStatement stm) throws SQLException {
        stm.close();
    }

    public static void closeConnexion(Connection cnx) throws SQLException {
        cnx.close();
    }

}
