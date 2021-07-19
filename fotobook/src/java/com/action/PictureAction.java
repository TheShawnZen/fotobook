package com.action;

import com.entites.Picture;
import com.manager.PictureManager;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.Part;

public class PictureAction {
        public static ArrayList<Picture> getAll() throws IOException {
		ArrayList<Picture> pictures = PictureManager.getAll();
                return pictures;
	}
    
    	public static Picture afficherPictureParId(int id) throws IOException {
		Picture picture = PictureManager.getById(id);
                return picture;
	}

        public static ArrayList<Picture> afficherPictureParUserId(int id) throws IOException {
		ArrayList<Picture> pictures = PictureManager.getByUserId(id);
                return pictures;
	}
        public static ArrayList<Picture> afficherPictureParPrivacyFriend(int id) throws IOException {
		ArrayList<Picture> pictures = PictureManager.getByPrivacyFriend(id);
                return pictures;
	}
        public static ArrayList<Picture> afficherPictureParPrivacyVisitor(int id) throws IOException {
		ArrayList<Picture> pictures = PictureManager.getByPrivacyVisitor(id);
                return pictures;
	}
        public static ArrayList<Picture> afficherTopXPictures(int topX) throws IOException {
		ArrayList<Picture> pictures = PictureManager.getTopX(topX);
                return pictures;
	}
        
        public static boolean insererPicture(Picture pic, Part p) throws IOException {
		boolean pictures = PictureManager.insererPicture(pic, p);
                return pictures;
	}
        
        public static boolean modifierPicture(Picture pic) {
		boolean pictures = PictureManager.modifierPicture(pic);
                return pictures;
	}
        
        public static boolean effacerPicture(Picture pic) {
		boolean pictures = PictureManager.effacerPicture(pic);
                return pictures;
	}
}
