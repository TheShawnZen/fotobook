<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Se connecter</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/inscription_style.css" %></style>
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <div class="center" >
            <h1>Fotobook</h1>
            <form class="signup" id="signup" action="inscription" method="POST">
                <div class="txt_field">
                    <input type="text" class="form-control" id="email" name="email">
                    <span></span>
                    <label>Adresse courriel</label>
                </div>
                <label for="email" generated="true" class="error"></label>
                <div class="txt_field">
                    <input type="text" class="form-control" id="username" name="username">
                    <span></span>
                    <label>Nom d'utilisateur</label>
                </div>
                <label for="username" generated="true" class="error"></label>
                <div class="txt_field">
                    <input type="password" class="form-control" id="password" name="password">
                    <span></span>
                    <label>Mot de passe</label>
                </div>
                <label for="password" generated="true" class="error"></label>
                <div class="txt_field">
                    <input type="password" class="form-control" id="confirm" name="confirm">
                    <span></span>
                    <label>Confirmation</label>
                </div>
                <label for="confirm" generated="true" class="error"></label>
                <div class="msg">
                    <label class="lbl_msg" id="msg"></label>
                </div>
                <input type="submit" value="S'inscrire" id="submit">
            </form>
        </div>
        <script><jsp:include page="/WEB-INF/js/validationInscription.js"/></script>
    </body>
</html>