<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 22.11.2019
  Time: 2:44
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
    <title>My messages</title>
</head>
<body>
<div id="header"></div>
<main>

    <% for (User user : (List<User>) request.getAttribute("from")) {%>

    <section class="text-center text-lg-left dark-grey-text" style="border: groove"
             onclick="location.href='<%=request.getContextPath() + "/messages?userId=" + user.getId()%>'"
    >
        <div class="media d-block d-md-flex mt-4">
            <img class="card-img-64 rounded z-depth-1 d-flex mx-auto mb-3"
                 src="<%=request.getContextPath() + "/userAvatar/" +  user.getId() + ".jpg"%>"
                 alt="">
            <div class="media-body text-center text-md-left ml-md-3 ml-0">
                <p class="font-weight-bold my-0">
                    <c:out value="<%=user.getLogin()%>"/>
                </p>
            </div>
        </div>
    </section>
    <% } %>
</main>

<div id="footer"></div>
</body>
</html>
