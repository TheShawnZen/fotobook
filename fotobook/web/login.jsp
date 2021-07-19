<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <title>Se connecter</title>
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/login_style.css" %></style>
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <div class="center" id="big_form">
            <h1>Fotobook</h1>
            <form class="form-signin" action="login" method="POST">
                <div class="txt_field">
                    <input type="text" class="form-control" name="username" required>
                    <span></span>
                    <label>Nom d'utilisateur</label>
                </div>
                <div class="txt_field">
                    <input type="password" class="form-control" name="password" required>
                    <span></span>
                    <label>Mot de passe</label>
                </div>
                <div class="erreur">
                    <label class="lbl_erreur">${erreur}</label>
                </div>
                <input type="submit" value="Se connecter">
                <div class="signup_link">
                    Vous n'avez pas de compte  <a href="inscription.jsp">Inscris-toi</a>
                </div>
            </form>
        </div>
    </body>
</html>
