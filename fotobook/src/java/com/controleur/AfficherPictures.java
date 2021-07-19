/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.action.CommentAction;
import com.action.LikesAction;
import com.action.PictureAction;
import com.action.UserAction;
import com.entites.Comment;
import com.entites.Likes;
import com.entites.Picture;
import com.entites.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author raduo
 */
public class AfficherPictures extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

    }

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
       String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "afficherTop": {
                    try {
                        this.afficherTop(request, response);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    }
                }
                break;
                case "afficherByProfil": {
                    try {

                        this.afficherByProfil(request, response);

                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    }
                    break;
                }
                case "select": {
                    try {
                        this.select(request, response);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    }
                }
                break;
                case "close": {
                    try {
                        this.close(request, response);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace(System.out);
                    }
                }
                break;
            }
        }

    

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
 private void afficherTop(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));
        ArrayList<User> allUsers = UserAction.getAll();
        ArrayList<Picture> pictures = PictureAction.afficherTopXPictures(3);
        request.setAttribute("pictures", pictures);
        request.setAttribute("allUsers", allUsers);
        response.sendRedirect("accueil.jsp");
    }

    private void afficherByProfil(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("userID"));
        ArrayList<Picture> pictures = PictureAction.afficherPictureParUserId(id);
        request.setAttribute("pictures", pictures);
        request.setAttribute("userID", id);
        session.setAttribute("idPic",99999);
        request.getRequestDispatcher("afficherProfil").forward(request, response);
    }

    private void select(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        int idPic = Integer.parseInt(request.getParameter("idPic"));
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));
        session.setAttribute("idPic", idPic);
        int id = Integer.parseInt(request.getParameter("userID"));
        request.setAttribute("userID", id);
        
        //comments
        ArrayList<Comment> comments = CommentAction.afficherCommentParPictureID(idPic);
        request.setAttribute("comments", comments);
        
        //likes
        
        ArrayList<Likes> likes = (LikesAction.afficherLikesParPictureId(idPic) != null) ? LikesAction.afficherLikesParPictureId(idPic) : new ArrayList<>();
        request.setAttribute("likes", likes);
        if(utilisateur != null){
            boolean liked = (LikesAction.afficherLikeParUser(idPic, utilisateur.getId()) == null);
            request.setAttribute("liked", liked);
        }
        
        request.getRequestDispatcher("afficherProfil").forward(request, response);
    }

    private void close(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("idPic", 99999);
        int id = Integer.parseInt(request.getParameter("userID"));
        request.setAttribute("userID", id);
        request.getRequestDispatcher("afficherProfil").forward(request, response);
    }
}


