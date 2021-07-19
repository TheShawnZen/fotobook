package com.manager;

import com.entites.Likes;
import com.service.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LikesManager {

    private static final String queryGetAll = "select * from likes";
    private static final String queryGetById = "select * from likes where id = ?";
    private static final String queryGetByUserId = "select * from likes where userID = ?";
    private static final String queryGetByPictureId = "select * from likes where pictureID = ?";
    private static final String queryGetByPictureIdAndUserId = "select * from likes where pictureID = ? and userID = ?";
    private static final String queryInsert = "insert into likes(id, userID, pictureID) VALUES(?,?,?)";
    private static final String queryDelete = "delete from likes where pictureID = ?";

    public static ArrayList<Likes> getAll() {
        ArrayList<Likes> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetAll);
            ResultSet result = ps.executeQuery();
            pictures = new ArrayList();
            while (result.next()) {
                Likes like = new Likes();
                like.setId(result.getInt("id"));
                like.setUserID(result.getInt("userID"));
                like.setPictureID(result.getInt("pictureID"));
                pictures.add(like);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pictures;
    }

    public static Likes getUserLike(int picID, int userID) {
       Likes like = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByPictureIdAndUserId);
            ps.setInt(1, picID);
            ps.setInt(2, userID);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                like = new Likes();
            }
            while (result.next()) {           
                like.setId(result.getInt("id"));
                like.setUserID(result.getInt("userID"));
                like.setPictureID(result.getInt("pictureID"));                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    
    public static Likes getById(int id) {
        Likes like = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetById);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                like = new Likes();
            }
            while (result.next()) {
                like.setId(result.getInt("id"));
                like.setUserID(result.getInt("userID"));
                like.setPictureID(result.getInt("pictureID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    public static ArrayList<Likes> getByUserId(int id) {
        ArrayList<Likes> likes = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByUserId);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                likes = new ArrayList<>();
            }
            while (result.next()) {
                Likes like = new Likes();
                like.setId(result.getInt("id"));
                like.setUserID(result.getInt("userID"));
                like.setPictureID(result.getInt("pictureID"));
                likes.add(like);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }
    
    public static ArrayList<Likes> getByPictureId(int id) {
        ArrayList<Likes> likes = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByPictureId);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                likes = new ArrayList<Likes>();
            }
            while (result.next()) {
                Likes like = new Likes();
                like.setId(result.getInt("id"));
                like.setUserID(result.getInt("userID"));
                like.setPictureID(result.getInt("pictureID"));
                likes.add(like);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }
    
    public static boolean insererLike(Likes like) {
        boolean retour = false;
        int nbLigne = 0;
        PreparedStatement ps;
        try {
            ps = Connexion.getConnexion().prepareStatement(queryInsert);
            ps.setInt(1, like.getId());
            ps.setInt(2, like.getUserID());
            ps.setInt(3, like.getPictureID());

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

    public static boolean effacerLike(Likes p) {
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryDelete);
            stm.setInt(1, p.getPictureID());

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
