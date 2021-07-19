<%@page import="com.action.PictureAction"%>
<%@page import="com.action.FriendRequestAction"%>
<%@page import="com.action.FriendAction"%>
<%@page import="com.entites.Likes"%>
<%@page import="com.action.UserAction"%>
<%@page import="com.entites.User"%>
<%@page import="com.action.LikesAction"%>
<%@page import="com.action.CommentAction"%>
<%@page import="com.entites.Comment"%>
<%@page import="com.entites.Picture"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/searchBar_style.css" %></style>
        <style><%@include file="/WEB-INF/css/gallery_style.css" %></style>
        <style><%@include file="/WEB-INF/css/profil_style.css" %></style>
        <style><%@include file="/WEB-INF/css/popup.css" %></style>
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <%@include file="/WEB-INF/inclusions/barreRecherche.jsp"%>
        <%
            //get le user id du profil visité
            int user_id = ((int) request.getAttribute("userID"));
            User profilUser = UserAction.afficherUserParId(user_id);

            if (utilisateur != null) {
        %>
        <div class="btn_option">
            <% if (utilisateur.getId() == profilUser.getId()) { %>
            <a href="afficherGerer" class="a_gerer"><i class="mahmoud fas fa-cogs"></i></a>
            <span class="tooltiptext">Gérer mes photos</span>
            <% }
                ArrayList<Friend> amis = (ArrayList<Friend>) session.getAttribute("friends");
                ArrayList<FriendRequest> requetesProfil = FriendRequestAction.afficherParSender(user_id);
                ArrayList<FriendRequest> requetesMoi = FriendRequestAction.afficherParSender(utilisateur.getId());
                Friend ami = null;
                boolean amiOuPas = false;
                boolean requeteAmiOuPas = false;
                FriendRequest fRequest = null;
                // TODO: for loop voir si y'a une requête en cours coline de bines
                if (requetesProfil != null) {
                    for (FriendRequest tabarnak : requetesProfil) {
                        if (tabarnak.getUserReceiverID() == utilisateur.getId()) {
                            requeteAmiOuPas = true;
                            fRequest = tabarnak;
                        }
                    }
                }
                if (requetesMoi != null) {
                    for (FriendRequest tabarnak : requetesMoi) {
                        if (tabarnak.getUserReceiverID() == user_id) {
                            requeteAmiOuPas = true;
                            fRequest = tabarnak;
                        }
                    }
                }
                if (amis != null) {
                    for (Friend fami : amis) {
                        if (((fami.getUserOneID() == user_id || fami.getUserTwoID() == user_id) && fami.areFriends(utilisateur))) {
                            amiOuPas = true;
                            ami = fami;
                        }
                    }
                }

                if (requeteAmiOuPas == true && utilisateur.getId() != profilUser.getId()) {%>
            <%  if (utilisateur.getId() == fRequest.getUserReceiverID()) {%>
            <a href="afficherFriendRequests?action=add&id=<%=fRequest.getId()%>&receiverId=<%=fRequest.getUserReceiverID()%>&senderId=<%=fRequest.getUserSenderID()%>" class="a_gerer">
                <i class="mahmoud fas fa-user-clock"></i>
            </a>
            <span class="tooltiptext">Accepter la demande</span>
            <%  } else {%>
            <a href="afficherFriendRequests?action=delete&id=<%= fRequest.getId()%>" class="a_gerer">
                <i class="mahmoud fas fa-user-clock"></i>
            </a>
            <span class="tooltiptext">Annuler la demande</span>
            <%  }
                } else if (amiOuPas == true && utilisateur.getId() != profilUser.getId()) {%>
            <a href="friend?friendID=<%= ami != null ? ami.getId() : utilisateur.getId()%>&userID=<%= user_id%>" class="a_gerer">
                <i class="mahmoud fas fa-user-minus"></i>
            </a>
            <span class="tooltiptext">Supprimer comme ami</span>
            <% } else if (amiOuPas == false && utilisateur.getId() != profilUser.getId()) {%>
            <a href="afficherFriendRequests?action=create&userID=<%= user_id%>" class="a_gerer">
                <i class="mahmoud fas fa-user-plus"></i>
            </a>
            <span class="tooltiptext">Ajouter comme ami</span>
            <% } %>
        </div>
        <%}%>
        <div  class="gallery-section contenu">
            <%  ArrayList<Picture> pictures = ((ArrayList<Picture>) request.getAttribute("pictures"));%>
            <div class="inner-width">
                <h1><%= profilUser.getUsername()%></h1>
                <div class="border"></div>
                <%if (pictures == null) {%>
                <h1 class="pasDePhoto">Aucune photo</h1>
                <%} else { %>
                <div class="gallery">
                    <%  for (Picture picture : pictures) {%>                                
                    <a href="afficherPictures?action=select&idPic=<%=picture.getId()%>&userID=<%=request.getAttribute("userID")%>" class="image">
                        <img src="data:image/jpg;base64,<%= picture.getImg()%>" alt="">
                    </a>
                    <%}%>
                    <%}%>
                </div>
            </div>
        </div>
        <%if((Integer)session.getAttribute("idPic") != 99999){%>
            <div class="popup" style="display: <%=session.getAttribute("display")%>">          

                <img class="pic" src="data:image/jpg;base64,<%=PictureAction.afficherPictureParId((Integer) session.getAttribute("idPic")).getImg()%>" alt="" >            
                <div class="review">
                    <div class="caption">
                        <a class="owner" href="afficherPictures?action=afficherByProfil&userID=<%=request.getAttribute("userID")%>"><%=(UserAction.afficherUserParId((Integer) request.getAttribute("userID"))).getUsername()%></a>
                        <p><%=PictureAction.afficherPictureParId((Integer) session.getAttribute("idPic")).getCaption()%></p> 
                        <span></span>
                    </div>
                    <div class="comments">
                    <%  ArrayList<Comment> comments = ((ArrayList<Comment>) request.getAttribute("comments"));
                        if (comments == null) {
                    %>
                            <h1>Aucun commentaire</h1>
                    <%  } else {%>
                            <ul>
                            <%for (Comment comment : comments) {
                            %>     
                                <li><a class="username" href="afficherPictures?action=afficherByProfil&userID=<%=comment.getUserID()%>"><%=(UserAction.afficherUserParId(comment.getUserID())).getUsername()%> </a> - <%=comment.getMsg()%></li>
                            <% }%>
                            </ul>
                        <% }%>
                        <% if(utilisateur != null){%>
                            <form action="sendComment?userID=<%=request.getAttribute("userID")%>" method="POST">
                                <input type="text" name="comment"  value=""/>
                                <button type="submit"><i class="fas fa-reply" style="color: #808080;"></i></button>
                            </form>
                        <% } %>
                    </div>
                    <% if (utilisateur != null){%>
                        <%if ((boolean) request.getAttribute("liked")) {%>
                        <div class="likes">
                            <a href="likePicture?userID=<%=request.getAttribute("userID")%>"><i class="far fa-heart"></i></a> <%=((ArrayList<Likes>) request.getAttribute("likes")).size()%>
                        </div>
                        <%} else {%>
                            <div class="likes">
                                <i class="fas fa-heart" style="color:red"></i> <%=((ArrayList<Likes>) request.getAttribute("likes")).size()%>
                            </div>
                        <%}%>
                    <%}else{%>
                        <div class="likes-visitor">
                            <i class="fas fa-heart"></i> <%=((ArrayList<Likes>) request.getAttribute("likes")).size()%>
                        </div>
                    <%}%>                              
                </div>
                <span> <a href="afficherPictures?action=close&userID=<%=request.getAttribute("userID")%>"><i class="fas fa-times" style="color: #808080;"></i></a></span>
            </div>
        <%}%>


        <script><%@include file="/WEB-INF/js/barreRecherche.js" %></script>
    </body>
</html>
