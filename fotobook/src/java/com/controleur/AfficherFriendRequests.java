/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.action.FriendAction;
import com.action.FriendRequestAction;
import com.entites.Friend;
import com.entites.FriendRequest;
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
 * @author maiso
 */
public class AfficherFriendRequests extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        if (request.getParameter("action") != null) {
            if ("delete".equals(request.getParameter("action"))) {
                try {
                    this.delete(request, response);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace(System.out);
                }
            } else if ("add".equals(request.getParameter("action"))) {
                try {
                    this.add(request, response);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace(System.out);
                }
            }
            else if ("create".equals(request.getParameter("action"))) {
                try {
                    this.create(request, response);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace(System.out);
                }
            }
            else if ("afficher".equals(request.getParameter("action"))) {
                try {
                    this.afficher(request, response);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }
        //processRequest(request, response);

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
        //processRequest(request, response);
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

    public void afficher(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        HttpSession session = request.getSession();
        User utilisateur = ((User)session.getAttribute("user"));
        request.setAttribute("invites", FriendRequestAction.afficherParReceiver(utilisateur.getId()));
        request.getRequestDispatcher("/WEB-INF/inclusions/friendRequest.jsp").forward(request, response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        HttpSession session = request.getSession();
        User utilisateur = ((User)session.getAttribute("user"));
        int id = Integer.parseInt(request.getParameter("id"));
        String rubanAmi = request.getParameter("ruban");
        FriendRequestAction.deleteFriendRequest(id);
        session.setAttribute("friendInvites", FriendRequestAction.afficherParReceiver(utilisateur.getId()));
        if (rubanAmi!= null){
            response.sendRedirect("defaut?friendRequest=true");
        }else
            response.sendRedirect("defaut");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        int senderId = Integer.parseInt(request.getParameter("senderId"));
        String rubanAmi = request.getParameter("ruban");
        Friend f = new Friend();
        f.setUserOneID(receiverId);
        f.setUserTwoID(senderId);
        FriendAction.insererFriend(f);
        FriendRequestAction.deleteFriendRequest(id);
        session.setAttribute("friends", FriendAction.afficherFriendParUserID(receiverId));
        session.setAttribute("friendInvites", FriendRequestAction.afficherParReceiver(receiverId));
        if (rubanAmi!= null){
            response.sendRedirect("afficherProfil?friendRequest=true&userID="+senderId);
        }else
            response.sendRedirect("afficherProfil?userID="+senderId);
    }
    
    private void create(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, ServletException {
        HttpSession session = request.getSession();
        int receiverId = Integer.parseInt(request.getParameter("userID"));
        int senderId =((User) session.getAttribute("user")).getId();
        FriendRequestAction.insererFriendRequest(senderId,receiverId);
        response.sendRedirect("afficherProfil?userID="+receiverId);
    }
}
