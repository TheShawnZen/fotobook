<%-- 
    Document   : testPost
    Created on : 2020-11-15, 13:57:30
    Author     : raduo
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <title>Posting image to DB</title>
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <form action="testPost" method="post" enctype="multipart/form-data">
            <input type="text" name="description" />
            <input type="file" name="photo" />
            <input type="submit" value="Envoyer"/>
        </form>
    </body>
</html>
