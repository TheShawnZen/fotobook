/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.action.PictureAction;
import com.action.UserAction;
import com.entites.Picture;
import com.entites.Friend;
import com.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Théo
 */
public class AfficherProfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User utilisateur = (User)session.getAttribute("user");
        ArrayList<Friend> profil_friend = (ArrayList<Friend>)session.getAttribute("friends");
        response.setContentType("text/html;charset=UTF-8");
        int id;
        if (request.getAttribute("userID")!=null)
              id = (Integer)  request.getAttribute("userID");
        else
               id = Integer.parseInt((String)request.getParameter("userID"));            

         ArrayList<Picture> pictures = null;
         if(utilisateur == null){
             pictures = PictureAction.afficherPictureParPrivacyVisitor(id);
         }else{
             boolean ami = false;
             if(profil_friend != null){
                for(Friend f : profil_friend){
                    if(id == f.getUserOneID() || id == f.getUserTwoID()){
                        ami = true;
                    }
                }
             }
             if(utilisateur.getId()== id){
                pictures = PictureAction.afficherPictureParUserId(id);
            }else if(ami == true){
                 pictures = PictureAction.afficherPictureParPrivacyFriend(id);
            }else if(ami ==false){
                pictures = PictureAction.afficherPictureParPrivacyVisitor(id);
            }
         }

         ArrayList<User> allUsers = UserAction.getAll();
         request.setAttribute("pictures", pictures);
         session.setAttribute("allUsers", allUsers);
         request.setAttribute("userID", id);
         if (session.getAttribute("idPic")==(null))
             session.setAttribute("idPic", 99999);
         if(session.getAttribute("idPic").equals(99999))
             session.setAttribute("display", "none");
         else
             session.setAttribute("display", "flex");
         request.getRequestDispatcher("WEB-INF/vues/profil.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
