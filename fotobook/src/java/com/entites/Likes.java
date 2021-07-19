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
public class Likes {
    private int id;
    private int userID;
    private int pictureID;
    
    //getter

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getPictureID() {
        return pictureID;
    }
    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }
}
