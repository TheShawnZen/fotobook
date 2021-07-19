package com.manager;

import com.entites.Picture;
import com.service.Connexion;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

public class PictureManager {

    private static final String queryGetAll = "select * from picture";
    private static final String queryGetById = "select * from picture where id = ?";
    private static final String queryGetByUserId = "select * from picture where userID = ?";
    private static final String queryGetByPrivacyFriend = "select * from picture where (privacy = \"public\" or  privacy = \"friends\") and userID = ?";
    private static final String queryGetByPrivacyVisitor = "select * from picture where privacy = \"public\" and  userID = ?";
    private static final String queryGetTopX = "SELECT picture.id, picture.userID, picture.img, picture.privacy, picture.caption, COUNT(pictureID) AS \"nbLikes\" FROM fotobook.likes JOIN picture ON picture.id=likes.pictureID WHERE picture.privacy=\"public\" GROUP BY likes.pictureID ORDER BY COUNT(likes.pictureID) DESC LIMIT ?";
    private static final String queryInsert = "insert into picture(userID, privacy, caption, img) VALUES(?,?,?,?)";
    private static final String queryUpdate = "update picture set privacy = ?, caption = ? where id = ? and userID = ?";
    private static final String queryDelete = "delete from picture where id = ?";

    public static ArrayList<Picture> getAll() throws IOException {
        ArrayList<Picture> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetAll);
            ResultSet result = ps.executeQuery();
            pictures = new ArrayList();
            while (result.next()) {
                Picture p = new Picture();
                p.setId(result.getInt("id"));
                p.setUserID(result.getInt("userID"));
                p.setImg(bytesToBase64(result));
                p.setPrivacy(result.getString("privacy"));
                p.setCaption(result.getString("caption"));
                pictures.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pictures;
    }

    public static Picture getById(int id) throws IOException {
        Picture picture = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetById);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                picture = new Picture();
            }
            while (result.next()) {
                picture.setId(result.getInt("id"));
                picture.setUserID(result.getInt("userID"));
                picture.setImg(bytesToBase64(result));
                picture.setPrivacy(result.getString("privacy"));
                picture.setCaption(result.getString("caption"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return picture;
    }

    public static ArrayList<Picture> getByUserId(int id) throws IOException {
        ArrayList<Picture> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByUserId);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                pictures = new ArrayList<Picture>();
            }
            while (result.next()) {
                Picture picture = new Picture();
                picture.setId(result.getInt("id"));
                picture.setUserID(result.getInt("userID"));
                picture.setImg(bytesToBase64(result));
                picture.setPrivacy(result.getString("privacy"));
                picture.setCaption(result.getString("caption"));
                pictures.add(picture);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pictures;
    }
    public static ArrayList<Picture> getByPrivacyFriend(int UserId) throws IOException {
        ArrayList<Picture> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByPrivacyFriend);
            ps.setInt(1, UserId);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                pictures = new ArrayList<Picture>();
            }
            while (result.next()) {
                Picture picture = new Picture();
                picture.setId(result.getInt("id"));
                picture.setUserID(result.getInt("userID"));
                picture.setImg(bytesToBase64(result));
                picture.setPrivacy(result.getString("privacy"));
                picture.setCaption(result.getString("caption"));
                pictures.add(picture);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pictures;
    }
     public static ArrayList<Picture> getByPrivacyVisitor(int UserId) throws IOException {
        ArrayList<Picture> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByPrivacyVisitor);
            ps.setInt(1, UserId);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                pictures = new ArrayList<Picture>();
            }
            while (result.next()) {
                Picture picture = new Picture();
                picture.setId(result.getInt("id"));
                picture.setUserID(result.getInt("userID"));
                picture.setImg(bytesToBase64(result));
                picture.setPrivacy(result.getString("privacy"));
                picture.setCaption(result.getString("caption"));
                pictures.add(picture);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return pictures;
    }
    
    public static ArrayList<Picture> getTopX(int topX) throws IOException {
        ArrayList<Picture> pictures = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetTopX);
            ps.setInt(1, topX);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                pictures = new ArrayList<Picture>();
            }
            while (result.next()) {
                Picture picture = new Picture();
                picture.setId(result.getInt("id"));
                picture.setUserID(result.getInt("userID"));
                picture.setImg(bytesToBase64(result));
                picture.setPrivacy(result.getString("privacy"));
                picture.setCaption(result.getString("caption"));
                pictures.add(picture);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pictures;
    }
    
    // "insert into picture(userID, privacy, caption, img) VALUES(?,?,?,?)"
    public static boolean insererPicture(Picture pic, Part p) throws IOException {
        InputStream inputStream = null;
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        if (p != null) {
            inputStream = p.getInputStream();
        }
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryInsert);
            stm.setInt(1, pic.getUserID());
            stm.setString(2, pic.getPrivacy());
            stm.setString(3, pic.getCaption());
            if (inputStream != null) {
                stm.setBlob(4, inputStream);
            }
            cmptEnregistrement = stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Connexion.closeConnexion(stm);
                Connexion.closeConnexion(cnx);
            } catch (SQLException ex) {
                Logger.getLogger(PictureManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cmptEnregistrement > 0;
    }

    // private static final String queryUpdate = "update picture set privacy = ?, caption = ? where id = ? and userID = ?";
    public static boolean modifierPicture(Picture pic) {
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryUpdate);
            stm.setString(1, pic.getPrivacy());
            stm.setString(2, pic.getCaption());
            stm.setInt(3, pic.getId());
            stm.setInt(4, pic.getUserID());
            cmptEnregistrement = stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Connexion.closeConnexion(stm);
                Connexion.closeConnexion(cnx);
            } catch (SQLException ex) {
                Logger.getLogger(PictureManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cmptEnregistrement > 0;
    }

    // private static final String queryDelete = "delete from picture where id = ? and userID = ?";
    public static boolean effacerPicture(Picture pic) {
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryDelete);
            stm.setInt(1, pic.getId());
            cmptEnregistrement = stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Connexion.closeConnexion(stm);
                Connexion.closeConnexion(cnx);
            } catch (SQLException ex) {
                Logger.getLogger(PictureManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cmptEnregistrement > 0;
    }

    // ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯helpers¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
    public static String bytesToBase64(ResultSet res) throws IOException {
        String base64Image = null;
        try {
            Blob blob = res.getBlob("img");
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            inputStream.close();
            outputStream.close();
        } catch (SQLException ex) {
            Logger.getLogger(PictureManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base64Image;
    }
}
