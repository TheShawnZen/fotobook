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
public class Comment {
    private int id;
    private int userID;
    private int pictureID;
    private String msg;

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getPictureID() {
        return pictureID;
    }

    public String getMsg() {
        return msg;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
