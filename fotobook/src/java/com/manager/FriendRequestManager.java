/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.manager;

import com.entites.FriendRequest;
import com.service.Connexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiso
 */
public class FriendRequestManager {
    private static final String queryGetAll = "select * from friendRequest";    
    private static final String queryGetByID = "select * from friendRequest where id = ?";
    private static final String queryGetBySenderID = "select * from friendRequest where userSenderID = ?";
    private static final String queryGetByReceiverID = "select * from friendRequest where userReceiverID = ?";    
    private static final String queryInsert = "insert into friendRequest(userSenderID, userReceiverID) VALUES(?,?)";
    private static final String queryDeleteFriendRequest = "delete from friendRequest where id= ?";
    
    // queryGetAll = "select * from friendRequest";    
    public static ArrayList<FriendRequest> getAll() throws IOException {
        ArrayList<FriendRequest> friendRequests = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetAll);
            ResultSet result = ps.executeQuery();
            friendRequests = new ArrayList();
            while (result.next()) {
                FriendRequest fr = new FriendRequest();
                fr.setId(result.getInt("id"));
                fr.setUserSenderID(result.getInt("userSenderID"));
                fr.setUserReceiverID(result.getInt("userReceiverID"));
                friendRequests.add(fr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendRequests;
    }
    
   // queryGetByID = "select * from friendRequest where id = ?";  
    public static FriendRequest getById(int id) throws IOException {
        FriendRequest fr = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByID);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                fr = new FriendRequest();
            }
            while (result.next()) {
                fr.setId(result.getInt("id"));
                fr.setUserSenderID(result.getInt("userSenderID"));
                fr.setUserReceiverID(result.getInt("userReceiverID"));                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fr;
    }
    
    // queryGetBySenderID = "select * from friendRequest where userSenderID = ?";
    public static ArrayList<FriendRequest> getBySenderId(int id) throws IOException {
        ArrayList<FriendRequest> friendRequests = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetBySenderID);
            ps.setInt(1,id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                friendRequests = new ArrayList<FriendRequest>();
            }
            while (result.next()){
                FriendRequest fr = new FriendRequest();
                fr.setId(result.getInt("id"));
                fr.setUserSenderID(result.getInt("userSenderID"));
                fr.setUserReceiverID(result.getInt("userReceiverID"));
                friendRequests.add(fr);
            }
        
        } catch (SQLException e) {
          e.printStackTrace();  
        }
        return friendRequests;
    }
   
    // queryGetByReceiverID = "select * from friendRequest where userReceiverID = ?";
    public static ArrayList<FriendRequest> getByReceiverId(int id) throws IOException {
        ArrayList<FriendRequest> friendRequests = null;
        try {
            PreparedStatement ps = Connexion.getConnexion().prepareStatement(queryGetByReceiverID);
            ps.setInt(1,id);
            ResultSet result = ps.executeQuery();
            if (result.isBeforeFirst()) {
                friendRequests = new ArrayList<FriendRequest>();
            }
            while (result.next()){
                FriendRequest fr = new FriendRequest();
                fr.setId(result.getInt("id"));
                fr.setUserSenderID(result.getInt("userSenderID"));
                fr.setUserReceiverID(result.getInt("userReceiverID"));
                friendRequests.add(fr);
            }
        
        } catch (SQLException e) {
          e.printStackTrace();  
        }
        return friendRequests;
    }
    
    //queryInsert = "insert into friendRequest(id, userSenderID, userReceiverID) VALUES(?,?,?)";

    /**
     *
     * @param userSenderID
     * @param userReceiverID
     * @return
     * @throws IOException
     */
    public static int insererFriendRequest (int userSenderID, int userReceiverID) throws IOException{
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement=0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryInsert);
            stm.setInt(1, userSenderID);
            stm.setInt(2, userReceiverID);
            cmptEnregistrement = stm.executeUpdate();
        } catch (SQLException e){
        } finally {
            try{
                Connexion.closeConnexion(stm);
                Connexion.closeConnexion(cnx);
            } catch (SQLException ex){
                Logger.getLogger(FriendRequestManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return cmptEnregistrement;        
    }
    
    
    //queryDeleteFriendRequest = "delete from friendRequest where id= ?";
    public static int effacerFriendRequest(int id){        
        Connection cnx = null;
        PreparedStatement stm = null;
        int cmptEnregistrement = 0;
        try {
            cnx = Connexion.getConnexion();
            stm = cnx.prepareStatement(queryDeleteFriendRequest);
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
        return cmptEnregistrement;
}
}
