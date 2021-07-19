package com.controleur;

import com.action.LikesAction;
import com.action.PictureAction;
import com.entites.Likes;
import com.entites.Picture;
import com.entites.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SupprimerPhoto extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));

        int id = Integer.parseInt(request.getParameter("idPhoto"));
        Picture picSupprimer = new Picture();
        picSupprimer.setId(id);
        picSupprimer.setUserID(utilisateur.getId());
        if (picSupprimer.getUserID() == utilisateur.getId()) {
            ArrayList<Likes> likes = LikesAction.afficherLikesParPictureId(picSupprimer.getId());
            if(likes!=null){
                for (int i = 0; i < likes.size(); i++) {
                    LikesAction.supprimerLike(likes.get(i));
                }
            }
            PictureAction.effacerPicture(picSupprimer);
        }
        response.sendRedirect("afficherGerer");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
