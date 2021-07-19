/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.action.PictureAction;
import com.entites.Picture;
import com.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModifierPhoto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));
        if (utilisateur != null) {
            int idPic = Integer.parseInt(request.getParameter("idPhoto"));
            Picture pic = PictureAction.afficherPictureParId(idPic);
            request.setAttribute("idPhoto", idPic);
            request.setAttribute("pic", pic);
            request.getRequestDispatcher("WEB-INF/vues/afficherModifierPictureForm.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
