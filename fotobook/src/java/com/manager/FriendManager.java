package com.manager;

import com.entites.Friend;
import com.service.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class FriendManager {

    private static final String queryGetAll = "select * from friend";
    private static final String queryGetById = "select * from friend where id = ?";
    private static final String queryGetByUserId = "select * from friend where userOneID = ? or userTwoID = ?";
    private static final String queryInsert = "insert into friend(id, userOneID, userTwoID) VALUES(?,?,?)";
    private static final String queryDelete = "delete from friend where id = ?";

    public static ArrayList<Friend> getAll() {
        ArrayList<Friend> friends = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetAll);
            ResultSet result = ps.executeQuery();
            friends = new ArrayList();
            while (result.next()) {
                Friend friend = new Friend();
                friend.setId(result.getInt("id"));
                friend.setUserOneID(result.getInt("userOneID"));
                friend.setUserTwoID(result.getInt("userTwoID"));
                friends.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    public static Friend getById(int id) {
        Friend friend = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetById);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                friend = new Friend();
            }
            while (result.next()) {
                friend.setId(result.getInt("id"));
                friend.setUserOneID(result.getInt("userOneID"));
                friend.setUserTwoID(result.getInt("userTwoID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friend;
    }

    public static ArrayList<Friend> getByUserId(int id) {
        ArrayList<Friend> likes = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByUserId);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                likes = new ArrayList<>();
            }
            while (result.next()) {
                Friend friend = new Friend();
                friend.setId(result.getInt("id"));
                friend.setUserOneID(result.getInt("userOneID"));
                friend.setUserTwoID(result.getInt("userTwoID"));
                likes.add(friend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }
    
    public static boolean insererFriend(Friend friend) {
        boolean retour = false;
        int nbLigne = 0;
        PreparedStatement ps;
        try {
            ps = Connexion.getConnexion().prepareStatement(queryInsert);
            ps.setInt(1, friend.getId());
            ps.setInt(2, friend.getUserOneID());
            ps.setInt(3, friend.getUserTwoID());

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

    public static boolean effacerFriend(int id) {
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryDelete);
            stm.setInt(1, id);
            cmptEnregistrement = stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Connexion.closeConnexion(stm);
                Connexion.closeConnexion(cnx);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return cmptEnregistrement>0;
    }
}
