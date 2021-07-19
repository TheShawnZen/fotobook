<%@page import="com.action.UserAction"%>
<%@page import="com.action.LikesAction"%>
<%@page import="com.action.CommentAction"%>
<%@page import="com.entites.User"%>
<%@page import="com.entites.Comment"%>
<%@page import="com.entites.Picture"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/searchBar_style.css" %></style>
        <style><%@include file="/WEB-INF/css/gallery_style.css" %></style>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
        <title>Accueil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <%@include file="/WEB-INF/inclusions/barreRecherche.jsp"%>
        <% ArrayList<Picture> pictures = (ArrayList<Picture>) request.getAttribute("pictures");%>
        <div class="gallery-section contenu" >
            <div class="inner-width">
               <%if(pictures == null){ %>
                    <h1>Aucune photo avec la mention J'aime</h1>
                <%}else{ %>
                    <h1>Accueil</h1>
                    <div class="border"></div>
                    <div class="gallery">
                        <% for (Picture picture : pictures) {
                                Picture tmpPic = picture;
                        %>
                            <a href="data:image/jpg;base64,<%= tmpPic.getImg()%>" class="image">
                                <img src="data:image/jpg;base64,<%= tmpPic.getImg()%>" alt=""/>
                            </a>
                        <% }%>
                    </div>
                <% }%>
           </div> 
        </div>
        <script><%@include file="/WEB-INF/js/barreRecherche.js" %></script>
        <script><%@include file="/WEB-INF/js/photoPopup.js" %></script>
    </body>
</html>
