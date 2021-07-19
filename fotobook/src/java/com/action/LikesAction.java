package com.action;

import com.entites.Likes;
import com.manager.LikesManager;
import java.util.ArrayList;

public class LikesAction {
        public static ArrayList<Likes> getAll() {
		ArrayList<Likes> pictures = LikesManager.getAll();
                return pictures;
	}
    
    	public static Likes afficherLikeParId(int id) {
		Likes like = LikesManager.getById(id);
                return like;
	}

        public static ArrayList<Likes> afficherLikesParUserId(int id) {
		ArrayList<Likes> likes = LikesManager.getByUserId(id);
                return likes;
	}
        
        public static ArrayList<Likes> afficherLikesParPictureId(int id) {
		ArrayList<Likes> likes = LikesManager.getByPictureId(id);
                return likes;
	}
        
        public static boolean insererLike(Likes like) {
		boolean likes = LikesManager.insererLike(like);
                return likes;
	}
        
        public static boolean supprimerLike(Likes like) {
		boolean likes = LikesManager.effacerLike(like);
                return likes;
	}
        
        public static Likes afficherLikeParUser(int picID, int userID) {
		Likes like = LikesManager.getUserLike(picID, userID);
                return like;
	}       
}
