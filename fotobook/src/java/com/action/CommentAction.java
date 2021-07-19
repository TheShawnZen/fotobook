package com.action;

import com.entites.Comment;
import com.manager.CommentManager;
import java.util.ArrayList;

public class CommentAction {
    public static ArrayList <Comment> getAll(){
		ArrayList<Comment> comments = CommentManager.getAll();
                return comments;
	}
    
    	public static Comment afficherCommentParID(int id){
		Comment c = CommentManager.getByID(id);
                return c;
	}

        public static ArrayList <Comment> afficherCommentParPictureID(int id){
		ArrayList<Comment> comments = CommentManager.getByPictureID(id);
                return comments;
	}
        
        public static boolean insererComment(Comment c){
		boolean validation = CommentManager.insererComment(c);
                return validation;
	}
}
