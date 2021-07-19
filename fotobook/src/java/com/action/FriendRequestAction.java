/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.entites.FriendRequest;
import com.manager.FriendRequestManager;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author maiso
 */
public class FriendRequestAction {

    public static ArrayList<FriendRequest> getAll() throws IOException {
        ArrayList<FriendRequest> frs = FriendRequestManager.getAll();
        return frs;
    }

    public static FriendRequest afficherFriendRequestParId(int id) throws IOException {
        FriendRequest fr = FriendRequestManager.getById(id);
        return fr;
    }

    public static ArrayList<FriendRequest> afficherParReceiver(int id) throws IOException {
        ArrayList<FriendRequest> frs = FriendRequestManager.getByReceiverId(id);
        return frs;
    }
    
    public static ArrayList<FriendRequest> afficherParSender(int id) throws IOException {
        ArrayList<FriendRequest> frs = FriendRequestManager.getBySenderId(id);
        return frs;
    }

    public static int insererFriendRequest(int userSenderID, int userReceiverID) throws IOException {
        int frs = FriendRequestManager.insererFriendRequest(userSenderID, userReceiverID);
        return frs;
    }

    public static int deleteFriendRequest(int id) {
        int frs = FriendRequestManager.effacerFriendRequest(id);
        return frs;
    }
}
