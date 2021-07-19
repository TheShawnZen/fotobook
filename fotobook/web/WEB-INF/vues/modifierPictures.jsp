<%@page import="java.util.ArrayList"%>
<%@page import="com.entites.Picture"%>
<%@page import="com.entites.User"%>
<!DOCTYPE html>
<html>
    <head>
        <style><%@include file="/WEB-INF/css/nav_style.css" %></style>
        <style><%@include file="/WEB-INF/css/modifierPicturesStyle.css" %></style>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/b9e915e1be.js" crossorigin="anonymous"></script>
        <title>Paramètres</title>
    </head>
    <body>
        <%@include file="/WEB-INF/inclusions/nav.jsp"%>
        <div align="center">
            <div class="contenu" align="center" style="width: 70%;">
                <div id="ajouter">
                    <form action="ajouterPhoto" method="post" enctype="multipart/form-data" style="margin: 5%;">
                        <h2>Ajouter une photo</h2>
                        <label for="photo">Veuillez choisir une photo:</label>
                        <input type="file" name="photo" /> <br />
                        <label for="privacy">Description:</label>
                        <input type="text" name="caption" /> <br />
                        <label for="privacy">Confidentialité: </label>
                        <select name="privacy" id="privacy">
                            <option value="public">Publique</option>
                            <option value="friends">Amis</option>
                            <option value="private">Privée</option>
                        </select>
                        <input type="submit" value="Ajouter"/>
                    </form>
                </div>
            <%  ArrayList<Picture> listePicture =(ArrayList<Picture>) request.getAttribute("pictures");
                if(listePicture!=null){
            %>
                    <h2 style="margin-top: 5%; margin-bottom: 2%;">Modifier une photo</h2>
                    <table>
                        <tr>
                            <th><h2>Photo</h2></th>
                            <th><h2>Description</h2></th>
                            <th><h2>Confidentialité</h2></th>
                            <th></th>
                            <th></th>
                        </tr>
                        <% for (Picture picture : listePicture) {%>
                        <tr>
                            <td><img src="data:image/jpg;base64,<%= picture.getImg()%>" style='height: 150px'/></td>
                            <td><%= picture.getCaption() %></td>
                            <td><% 
                                switch(picture.getPrivacy()){
                                    case "public":
                                        out.println("Publique");
                                        break;
                                    case "private":
                                        out.println("Privée");
                                        break;
                                    case "friends":
                                        out.println("Amis");
                                        break;
                                }
                                %></td>
                            <td><a href="modifierPhoto?idPhoto=<%= picture.getId()%>" class="bouton">Modifier</a></td>
                            <td><a href="supprimerPhoto?idPhoto=<%= picture.getId()%>" class="bouton"><i class="far fa-trash-alt"></i></a></td>
                        </tr>
                        <% }%>
                    </table>
                <% }%>
            </div>
        </div>
    </body>
</html>
