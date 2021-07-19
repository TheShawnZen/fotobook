<%-- 
    Document   : ajouterFriend
    Created on : Dec 2, 2020, 4:26:47 AM
    Author     : maiso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/b9e915e1be.js" crossorigin="anonymous"></script>
        <style><%@include file="/WEB-INF/css/ajouterFriend_style.css" %></style>
    </head>
    <body>
        
        <a class="btn" href="${pageContext.request.contextPath}/afficherFriendRequests?action=create&senderId=<%=userID%>"><i class="fas fa-user-plus"></i></a>
    </body>
</html>
