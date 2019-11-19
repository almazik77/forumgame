<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<nav class="mb-1 navbar navbar-expand-lg navbar-dark lighten-1" id="header"
     style="height: 10%; background-color: black">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-555"
            aria-controls="navbarSupportedContent-555" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent-555">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="<%=request.getContextPath() + "/profile"%>">Home</a>
            </li>
            <li>
                <a class="nav-link" href="<%=request.getContextPath() + "/games"%>">Games</a>
            </li>
        </ul>

        <ul class="navbar-nav ml-auto nav-flex-icons">
            <% if (request.getSession().getAttribute("userId") == null) {%>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() + "/login"%>">Вход</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath() + "/signUp"%>">Регистрация</a>
            </li>
            <% } else { %>
            <li class="nav-item avatar">
                <a class="nav-link p-0" href="">
                    <img src="<%=(String)request.getSession().getAttribute("userAvatar")%>"
                         class="rounded-circle z-depth-0"
                         alt="avatar image" height="35">
                </a>
            </li>
            <% } %>
        </ul>
    </div>
</nav>


<footer class="page-footer font-small " id="footer" style="background-color: black">

    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <a href="https://mdbootstrap.com/education/bootstrap/"> kpfu.ru </a>
    </div>

</footer>

</body>
</html>
