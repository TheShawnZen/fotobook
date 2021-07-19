/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entites;

/**
 *
 * @author Théo
 */
public class FriendRequest {
    private int id;
    private int userSenderID;
    private int userReceiverID;
    
    //getter

    public int getId() {
        return id;
    }

    public int getUserSenderID() {
        return userSenderID;
    }

    public int getUserReceiverID() {
        return userReceiverID;
    }
    
    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setUserSenderID(int userSenderID) {
        this.userSenderID = userSenderID;
    }

    public void setUserReceiverID(int userReceiverID) {
        this.userReceiverID = userReceiverID;
    }
}
