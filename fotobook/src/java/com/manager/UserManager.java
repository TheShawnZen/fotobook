package com.manager;

import com.entites.User;
import com.service.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Salahudine
 */
public class UserManager {

    private static final String queryGetyAll = "select * from user";
    private static final String queryGetByUserName = "select * from user where username = ?";
    private static final String queryGetById = "select * from user where id = ?";
    private static final String queryInsert = "insert into user(email, username, pwd) VALUES(?,?,?)";
    public static ArrayList<User> getAll() {

        ArrayList<User> mesUsers = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetyAll);
            ResultSet result = ps.executeQuery();
            mesUsers = new ArrayList();
            while (result.next()) {
                User u = new User();
                u.setId(result.getInt("id"));
                u.setEmail(result.getString("email"));
                u.setUsername(result.getString("username"));
                u.setPassword(result.getString("pwd"));
                mesUsers.add(u);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mesUsers;
    }
    public static User getByUserName(String username) {

        User utilisateur = null;

        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByUserName);
            ps.setString(1, username);

            ResultSet result = ps.executeQuery();

            if (result.isBeforeFirst()) {
                utilisateur = new User();
            }

            while (result.next()) {
                utilisateur.setId(result.getInt("id"));
                utilisateur.setEmail(result.getString("email"));
                utilisateur.setUsername(result.getString("username"));
                utilisateur.setPassword(result.getString("pwd"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return utilisateur;
    }
    public static User getById(int id) {
        User u = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetById);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                u = new User();
            }
            while (result.next()) {
                u.setId(result.getInt("id"));
                u.setEmail(result.getString("email"));
                u.setUsername(result.getString("username"));
                u.setPassword(result.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
     public static boolean insererUser(User u) {
        boolean retour = false;
        int nbLigne = 0;
        PreparedStatement ps;
        try {
            ps = Connexion.getConnexion().prepareStatement(queryInsert);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());

            nbLigne = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (nbLigne > 0) {
            retour = true;
        }
        Connexion.closeConnexion();
        return retour;
    }
}