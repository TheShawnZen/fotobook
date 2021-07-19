package com.action;

import com.entites.Friend;
import com.manager.FriendManager;
import java.util.ArrayList;

public class FriendAction {
        public static ArrayList<Friend> getAll() {
		ArrayList<Friend> friends = FriendManager.getAll();
                return friends;
	}
    
    	public static Friend afficherFriendParId(int id) {
		Friend friends = FriendManager.getById(id);
                return friends;
	}

        public static ArrayList<Friend> afficherFriendsParUserId(int id) {
		ArrayList<Friend> friends = FriendManager.getByUserId(id);
                return friends;
	}
        
        public static boolean insererFriend(Friend friend) {
		boolean friends = FriendManager.insererFriend(friend);
                return friends;
	}
        
        public static boolean supprimerFriend(int id) {
		boolean friends = FriendManager.effacerFriend(id);
                return friends;
	}
        public static ArrayList<Friend> afficherFriendParUserID(int userID) {
		 ArrayList <Friend> friends = FriendManager.getByUserId(userID);
                return friends;
	}
        
}
