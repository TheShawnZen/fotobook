<%@page import="com.entites.User"%>
<%@page import="com.action.UserAction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entites.Picture"%>
<%@page import="com.entites.Comment"%>
<%@page import="com.entites.Likes"%>
<%@page import="com.action.LikesAction"%>
<%@page import="com.action.CommentAction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>hehe</h1>
        <% for (Picture picture : (ArrayList<Picture>) request.getAttribute("pictures")) {
            Picture tmpPic = picture;
            ArrayList<Comment> tmpComments = CommentAction.afficherCommentParPictureID(tmpPic.getId());
            int tmpLikes = LikesAction.afficherLikesParPictureId(tmpPic.getId()).size();
        %>
        <div align="center">
            <h2><%= picture.getUserID()%></h2>
            <h3><%= picture.getCaption()%></h3>
            <img src="data:image/jpg;base64,<%= picture.getImg()%>" style='width: 400px'/>
            <h4><%= tmpLikes%> ♥</h4>
            <% for (Comment c : tmpComments) {%>
            Nom d'utilisateur: <%= ((User) UserAction.afficherUserParId(c.getUserID())).getUsername()%><br />
            Commentaire: <%= c.getMsg()%><br /><br />
            <% } %>
            <hr>
            <!--/**
            $("a.picture").click(function() {
            var myClass = $(this).attr("class");
            alert(myClass);
            });
            const com = (classe => $(".commentaire ."+classe)); // zone de commentaires dans le modal
            const likes = (classe => $(".likes ."+classe));
            com.append("Nom d'utilisateur:  <br /> <-%= ((User) UserAction.afficherUserParId(c.getUserID())).getUsername()%->");
            */--->
        </div>
        <% }%>
    </body>
</html>
