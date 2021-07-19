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
public class Picture {

    private int id;
    private int userID;
    private String img;
    private String privacy;
    private String caption;

    //getter
    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getImg() {
        return img;
    }

    public String getCaption() {
        return caption;
    }

    public String getPrivacy() {
        return privacy;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

}
