<%@page import="com.entites.Picture"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/formulaireModifierPhotoStyle.css" %></style>
        <title>Modifier une photo</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <div id="divcentre" align="center">
            <div id="contenucentre">
                <% Picture photo = (Picture) request.getAttribute("pic");%>
                <form action="modifierPicture" method="POST" style="display: inline-flex;">
                    <div>
                        <input style="opacity: 0;" checked id="btnRad" type="radio" name="idPhoto" value="${pic.getId()}" />
                        <img src="data:image/jpg;base64,<%= photo.getImg()%>" style='width: 300px;'/>
                    </div>
                    <div id="informations">
                        <label for="caption" style="color:rgb(226,226,226);">Description:</label>
                        <input id="cap" type="text" name="caption" value="<%= photo.getCaption()%>"/><br />
                        <label for="privacyMod" style="color:rgb(226,226,226);">Confidentialité: </label>
                        <select name="privacyMod">
                            <% if ((photo.getPrivacy()).equals("private")) { %>
                            <option value="private">Privée</option>
                            <option value="friends">Amis</option>
                            <option value="public">Publique</option>
                            <% } else if ((photo.getPrivacy()).equals("public")) {%>
                            <option value="public">Publique</option>
                            <option value="private">Privée</option>
                            <option value="friends">Amis</option>
                            <%} else {%>
                            <option value="friends">Amis</option>
                            <option value="public">Publique</option>
                            <option value="private">Privée</option>
                            <% }%>
                        </select>
                        <br /><input id="btnSubmit" type="submit" value="Modifier"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
