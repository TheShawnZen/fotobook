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
public class Friend {
    private int id;
    private int userOneID;
    private int userTwoID;

    public int getId() {
        return id;
    }

    public int getUserOneID() {
        return userOneID;
    }

    public int getUserTwoID() {
        return userTwoID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserOneID(int userOneID) {
        this.userOneID = userOneID;
    }

    public void setUserTwoID(int userTwoID) {
        this.userTwoID = userTwoID;
    }
    
    public boolean areFriends(User u){
        return (this.userOneID==u.getId() || this.userTwoID==u.getId());
    }
}
