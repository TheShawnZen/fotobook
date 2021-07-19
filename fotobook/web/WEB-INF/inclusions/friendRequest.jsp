<%-- 
    Document   : friendRequest
    Created on : Nov 22, 2020, 1:57:33 AM
    Author     : maiso
--%>

<%@page import="com.action.UserAction"%>
<%@page import="com.entites.FriendRequest"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>        
        <style><%@include file="/WEB-INF/css/friendRequest_style.css" %></style>
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            // Close the dropdown menu if the user clicks outside of it
            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {
                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
        </script>        
        <%int listSize = ((ArrayList<FriendRequest>) request.getAttribute("invites")).size();%>
        <div class="dropdown">
            <a href="#" onclick="myFunction()" class="dropbtn"><i class="fas fa-users"></i> <div class="count"><%=listSize%></div></a>
            
            <div id="myDropdown" class="dropdown-content">
                <%                   
                    if (listSize != 0){%>
                        <% for (FriendRequest fr : (ArrayList<FriendRequest>) request.getAttribute("invites")) {
                                FriendRequest frTemp = fr;
                        %>
                                <div class="friendRequest">
                                    <div class="username">
                                        <a href="#"><%=UserAction.afficherUserParId(frTemp.getUserSenderID()).getUsername()%></a>
                                    </div>
                                    <div class="choix">                                            
                                            <a href="${pageContext.request.contextPath}/afficherFriendRequests?action=add&id=<%=frTemp.getId()%>&receiverId=<%=frTemp.getUserReceiverID()%>&senderId=<%=frTemp.getUserSenderID()%>">
                                               <i class="fas fa-check-circle" style="color:greenyellow"></i>
                                            </a>
                                            <a href="${pageContext.request.contextPath}/afficherFriendRequests?action=delete&id=<%=frTemp.getId()%>"><i class="fas fa-times-circle" style="color:red"></i></a>
                                    </div>
                                </div>        
                        <%}%> 
                        
                    <%}
                    else{%>
                        <div style="color:white;padding:5px;">
                            You have no friends.
                        </div>
                    <%}%>
            </div>
           
        </div>
            
    </body>
</html>
