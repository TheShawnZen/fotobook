package com.service;

import com.action.CommentAction;
import com.action.UserAction;
import com.entites.Comment;
import static com.manager.UserManager.insererUser;
import com.entites.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestBD {

    public static void main(String[] args) {
//test insert User
//        User u = new User("lagnlois@gmail.com","kameleon","root");
//        boolean t = insererUser(u);

// Test getAll User
//        ArrayList<User> users = UserAction.getAll();
//        for(int i =0; i<users.size();i++){
//            System.out.println(users.get(i).getUsername());
//        }
// test connexion BD
//        String sql = "select * from user";
//        try {
//            PreparedStatement ps = Connexion.getConnexion().prepareStatement(sql);
//            ResultSet result = ps.executeQuery();
//            if (result.isBeforeFirst()) {
//                while (result.next()) {
//                    System.out.println(result.getString("email"));
//                    System.out.println(result.getString("username"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        Connexion.closeConnexion();

//        Comment c = new Comment();
//        c.setUserID(1);
//        c.setPictureID(4);
//        c.setMsg("tu pues");
//        boolean t = CommentAction.insererComment(c);
//        Comment a = new Comment();
//        a.setUserID(2);
//        a.setPictureID(4);
//        a.setMsg("t moche");
//        t = CommentAction.insererComment(a);
//        Comment b = new Comment();
//        b.setUserID(3);
//        b.setPictureID(3);
//        b.setMsg("t beau");
//        t = CommentAction.insererComment(b);
//        Comment comments = CommentAction.afficherCommentParID(6);
//        System.out.println(comments.getMsg());
        
    }
}
