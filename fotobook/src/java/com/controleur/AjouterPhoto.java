/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleur;

import com.entites.Picture;
import com.action.PictureAction;
import com.entites.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author raduo
 */
@MultipartConfig(maxFileSize = 16177215)
public class AjouterPhoto extends HttpServlet {

    private static final long serialVersionUID = -1623656324694499109L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));
        ArrayList<Picture> pictures = PictureAction.afficherPictureParUserId(utilisateur.getId());
        request.setAttribute("pictures", pictures);
        if (utilisateur != null) {
            request.getRequestDispatcher("WEB-INF/vues/modifierPictures.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User utilisateur = ((User) session.getAttribute("user"));
        Part filePart = request.getPart("photo");
        Picture picTmp = new Picture();
        picTmp.setUserID(utilisateur.getId());
        picTmp.setPrivacy(request.getParameter("privacy"));
        picTmp.setCaption(request.getParameter("caption"));
        PictureAction.insererPicture(picTmp, filePart);
        response.sendRedirect("ajouterPhoto");
    }
}
