package com.manager;

import com.entites.Comment;
import com.service.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentManager {
    private static final String queryGetAll = "select * from comment";
    private static final String queryGetByPictureID = "select * from comment where pictureID = ?";
    private static final String queryGetById = "select * from comment where id = ?";
    private static final String queryInsert = "insert into comment(userID, pictureID, msg) VALUES(?,?,?)";
    public static ArrayList<Comment> getAll() {

        ArrayList<Comment> mesComments = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetAll);
            ResultSet result = ps.executeQuery();
            mesComments = new ArrayList();
            while (result.next()) {
                Comment c = new Comment();
                c.setId(result.getInt("id"));
                c.setUserID(result.getInt("userID"));
                c.setPictureID(result.getInt("pictureID"));
                c.setMsg(result.getString("msg"));
                mesComments.add(c);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mesComments;
    }
    public static Comment getByID(int id) {

        Comment commentaire = null;

        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetById);
            ps.setInt(1,id);

            ResultSet result = ps.executeQuery();

            if (result.isBeforeFirst()) {
                commentaire = new Comment();
            }

            while (result.next()) {
                commentaire.setId(result.getInt("id"));
                commentaire.setUserID(result.getInt("userID"));
                commentaire.setPictureID(result.getInt("pictureID"));
                commentaire.setMsg(result.getString("msg"));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return commentaire;
    }
    public static ArrayList <Comment> getByPictureID(int pictureId) {
        ArrayList <Comment> mesComments = null;
        Comment c = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByPictureID);
            ps.setInt(1, pictureId);
            ResultSet result = ps.executeQuery();
            mesComments = new ArrayList();
            while (result.next()) {
                c = new Comment();
                c.setId(result.getInt("id"));
                c.setUserID(result.getInt("userID"));
                c.setPictureID(result.getInt("pictureID"));
                c.setMsg(result.getString("msg"));
                mesComments.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesComments;
    }
     public static boolean insererComment(Comment c) {
        boolean retour = false;
        int nbLigne = 0;
        PreparedStatement ps;
        try {
            ps = Connexion.getConnexion().prepareStatement(queryInsert);
            ps.setInt(1, c.getUserID());
            ps.setInt(2, c.getPictureID());
            ps.setString(3, c.getMsg());

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
