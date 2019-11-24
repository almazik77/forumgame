<%@ page import="models.Game" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta charset="UTF-8">
    <script type='text/html' src='<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#header").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #header");
            $("#footer").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #footer");
        });
    </script>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<div id="header"></div>
<main>

    <%for (Game game : (List<Game>) request.getAttribute("games")) { %>
    <div class="jumbotron text-center hoverable p-4"
         onclick="location.href='<%=request.getContextPath() + "/game?id=" +game.getId()%>'"
         style="border: groove 5px ; background-color: #F2F1F0; margin: 3% 5%; color: darkred">

        <div class="row" style="margin: 2% 1%">

            <div class=" text-md-left ml-3 mt-3">

                <h4 class="h4 mb-4">
                    <c:out value="<%=game.getName()%>"/>
                </h4>

                <p class="font-weight-normal"><c:out value="<%=game.getDescription()%>"/>
                </p>
                <p class="font-weight-normal">by
                    <a href="<%=request.getContextPath() + "profile?userId=" + game.getModeratorId()%>">
                        <strong>
                            <c:out value="<%=game.getModeratorLogin()%>"/>
                        </strong>
                    </a>
                </p>
            </div>
        </div>
    </div>
    <% } %>
</main>
<div id="footer"></div>
</body>
</html>
