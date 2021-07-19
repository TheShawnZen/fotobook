package com.controleur;

import com.action.FriendAction;
import com.action.FriendRequestAction;
import com.action.UserAction;
import com.entites.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        boolean connexion = false;
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User utilisateur = UserAction.afficherUserParNom(username);
        if (utilisateur != null) {
            if(password.equals(utilisateur.getPassword())) {
                connexion = true;
                HttpSession session = request.getSession(true);
                session.setAttribute("friendInvites", FriendRequestAction.afficherParReceiver(utilisateur.getId()));
                session.setAttribute("friends", FriendAction.afficherFriendParUserID(utilisateur.getId()));
                session.setAttribute("user",utilisateur);
                response.sendRedirect("defaut");
            }
            

        } else {
            out.println("La liste des users est vide");
        }

        if (!connexion) {
            String erreur = "Le nom d'utilisateur ou le mot de passe est invalide";
            request.setAttribute("erreur",erreur);
            request.getRequestDispatcher("/login.jsp").include(request, response);
        }
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
    }// </editor-fold>
}

