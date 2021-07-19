<%@page import="com.entites.User"%>
<%@page import="java.util.ArrayList"%>
<div id="search">
    <input type="text" id="inputRecherche" onkeyup="recherche()" placeholder="Rechercher">
    <ul id="listeRecherche">
        <%
            ArrayList<User> utilisateurs = (ArrayList<User>) session.getAttribute("allUsers");
            for (User util : utilisateurs) {%>
        <li><a href="afficherPictures?action=afficherByProfil&userID=<%=util.getId()%>" ><%=util.getUsername()%></a></li>
            <% }%>
    </ul>
</div>