<%@page import="com.action.UserAction"%>
<%@page import="com.entites.FriendRequest"%>
<%@page import="com.entites.Friend"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entites.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
    </head>
    <body>
        <%
            User utilisateur = ((User)session.getAttribute("user"));
            ArrayList<Friend> friends = ((ArrayList<Friend>)session.getAttribute("friends"));
            ArrayList<FriendRequest> invites = ((ArrayList<FriendRequest>)session.getAttribute("friendInvites"));
        %>
        <nav>
            <div class="logo">
                <h4>Fotobook</h4>
            </div>
            <ul class="nav-links">
                <li><a href="defaut">Accueil</a></li>
                <%
                    if(utilisateur == null){
                 %>
                        <li><a href="inscription.jsp">S'inscrire</a></li>
                        <li><a href="login.jsp">Se connecter</a></li>
                <%
                    }
                    else{
                 %>
                        <li><a href="afficherPictures?action=afficherByProfil&userID=<%=((User)session.getAttribute("user")).getId()%>">Mon profil</a></li>
                        <li><a href="#" class="btn_friend"><i class="fas fa-users"></i></a></li>
                        <li><a href="logout">Se déconnecter</a></li>
                 <% 
                    }
                 %>
            </ul>
            <div class="burger">
                <div class="line1"></div>
                <div class="line2"></div>
                <div class="line3"></div>
            </div>
        </nav>
        <div class="friendList">
            <%if(utilisateur!=null){%>
                <h1>Bonjour <%=utilisateur.getUsername()%> <i class="fas fa-user"></i></h1>
            <%}%>
            <div class="demande">
                <h2>Demandes d'amis</h2>
                <ul class="friend-links">
                       <%
                           if(invites == null){
                        %>
                               <li>Aucune demande</li>
                       <%
                           }
                           else{
                                for(FriendRequest invite : invites){
                        %>
                               <li>
                                   <div class= "username-link">
                                        <a href="afficherProfil?userID=<%=UserAction.afficherUserParId(invite.getUserSenderID()).getId()%>">
                                            <%=UserAction.afficherUserParId(invite.getUserSenderID()).getUsername()%></a>
                                    </div>
                                    <div class="choix">                                            
                                        <a href="afficherFriendRequests?action=add&id=<%=invite.getId()%>&receiverId=<%=invite.getUserReceiverID()%>&senderId=<%=invite.getUserSenderID()%>&ruban=on">
                                           <i class="fas fa-check-circle" style="color:greenyellow"></i>
                                        </a>
                                        <a href="afficherFriendRequests?action=delete&id=<%=invite.getId()%>&ruban=on">
                                            <i class="fas fa-times-circle" style="color:red"></i>
                                        </a>
                                    </div>
                               </li>

                        <%
                                }
                           }
                        %>
                </ul>
            </div>
            <div class="ami">
                <h2>Amis</h2>
                <ul class="friend-links">
                       <%
                           if( friends == null){
                        %>
                               <li>Aucun ami</li>
                       <%
                           }
                           else{
                                for(Friend friend : friends){
                        %>
                               <li>
                                   <%
                                       User friendUser;
                                       if(utilisateur.getId() != friend.getUserOneID() ){
                                           friendUser = UserAction.afficherUserParId(friend.getUserOneID());
                                       }else{
                                           friendUser = UserAction.afficherUserParId(friend.getUserTwoID());
                                       }
                                    %>
                                   <div class= "username-link">
                                        <a href="afficherProfil?userID=<%=friendUser.getId()%>">
                                            <%=friendUser.getUsername()%></a>
                                    </div>
                                    <div class="choix">                                            
                                        <a href="friend?friendID=<%=friend.getId()%>&userID=<%=friendUser.getId()%>">
                                            <i class="fas fa-trash-alt" style="color:red"></i>
                                        </a>
                                    </div>
                               </li>

                        <%
                                }
                           }
                        %>
                </ul>
            </div>
        </div>
        <script><%@include file="/WEB-INF/js/nav.js" %></script>
    </body>
</html>
