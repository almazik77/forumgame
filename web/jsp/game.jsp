<%@ page import="models.Phrase" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:34
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
    <title>Game</title>
</head>
<body>
<div id="header"></div>
<main>
    <div class="container my-5 py-5 px-md-5 z-depth-1">
        <!--Section: Content-->
        <section class="text-center text-lg-left dark-grey-text">
            <% for (Phrase phrase : ((List<Phrase>) request.getAttribute("phrases"))) { %>
            <div class="media d-block d-md-flex mt-4">
                <img class="card-img-64 rounded z-depth-1 d-flex mx-auto mb-3"
                     src="<%=request.getContextPath() + "/userAvatar/" + phrase.getUserId() + ".jpg"%>"
                     alt="">
                <div class="media-body text-center text-md-left ml-md-3 ml-0">
                    <c:out value="<%=phrase.getUserLogin()%>"/>
                    <p class="font-weight-bold my-0">
                        <c:out value="<%=phrase.getText()%>"/>
                    </p>
                </div>
            </div>
            <% }
                if (request.getAttribute("canAdd") != null && request.getAttribute("canAdd").equals(Boolean.TRUE)) { %>
            <div class="form-group mt-4">
                <form action='<%=request.getContextPath()+"/game"%>' method="post">

                    <label for="newPhrase">Add</label>
                    <input type="text" class="form-control" id="newPhrase" name="newPhrase"
                           required>

                    <div class="text-center my-4">
                        <input class="_button" type="submit" value="Post">
                    </div>
                </form>
            </div>
            <% } else if ((request.getAttribute("canCheck") != null && request.getAttribute("canCheck").equals(Boolean.TRUE)) ) { %>
            <div class="form-group mt-4">
                <form action='<%=request.getContextPath()+"/game"%>' method="post">
                    <div class="text-center my-4">
                        <input class="_button" type="submit" value="Set last message checked" name="checkLastMessage">
                    </div>
                </form>
            </div>
            <% }%>
        </section>
        <!--Section: Content-->
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination pg-blue justify-content-center">
            <%for (int i = 1; i <= (Integer) request.getAttribute("pagesCount"); i++) { %>
            <li class="page-item">
                <a class="page-link" href="<%=request.getContextPath() + "/game?page=" + i%>">
                    <%=i%>
                </a>
            </li>
            <% } %>
        </ul>
    </nav>
</main>

<div id="footer"></div>

</body>
</html>
