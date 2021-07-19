package com.action;

import com.entites.User;
import com.manager.UserManager;
import java.util.ArrayList;

public class UserAction {
        public static ArrayList<User> getAll(){
		ArrayList<User> users = UserManager.getAll();
                return users;
	}
    
    	public static User afficherUserParNom(String nom){
		User u = UserManager.getByUserName(nom);
                return u;
	}

        public static User afficherUserParId(int id){
		User u = UserManager.getById(id);
                return u;
	}
        
        public static boolean insererUser(User u){
		boolean validation = UserManager.insererUser(u);
                return validation;
	}
}