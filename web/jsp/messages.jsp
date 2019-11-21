<%@ page import="models.Message" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 20.11.2019
  Time: 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
    <meta charset="UTF-8">
    <script type='text/html' src='<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#header").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #header");
            $("#footer").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #footer");
        });
    </script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="../css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<header id="header"></header>
<main>
    <div class="jumbotron-fluid row" style="padding: 5%">

        <div class="col-2 ">
            <a href="<%=request.getContextPath() + "/profile"%>"><span>Профиль</span></a>
            <hr>
            <a href="<%=request.getContextPath() + "/myGames"%>"><span>Мои игры</span></a>
            <hr>
            <a href="<%=request.getContextPath() + "/messages"%>"><span>Сообщения</span></a>
            <hr>
            <a href="<%=request.getContextPath() + "/settings"%>"><span>Настройки</span></a>
            <hr>
            <a href="<%=request.getContextPath() + "/logout"%>"><span>Выйти</span></a>
        </div>
        <div class="col-10">

            <div class="container py-5 px-md-5 z-depth-1">
                <!--Section: Content-->

                <% for (Message message : (List<Message>) request.getAttribute("messages")) {%>
                <section class="text-center text-lg-left dark-grey-text" style="border: groove">
                    <div class="media d-block d-md-flex mt-4">
                        <img class="card-img-64 rounded z-depth-1 d-flex mx-auto mb-3"
                             src="<%=request.getContextPath() + "/userAvatar/" + message.getFromUserId() + ".jpg"%>"
                             alt="">
                        <div class="media-body text-center text-md-left ml-md-3 ml-0">
                            <p class="font-weight-bold my-0">
                                <%=message.getMessage()%>
                            </p>
                        </div>
                    </div>
                </section>
                <% } %>
                <form action="<%=request.getContextPath() + "/messages"%>" method="post">
                    <input type="text" name="userId" style="display: none" value="<%=request.getAttribute("toUser")%>">
                    <label for="newMessage"></label>
                    <input type="text" id="newMessage" class="form-control" name="message">
                    <input type="submit">
                </form>

            </div>
        </div>

    </div>

</main>
<footer id="footer"></footer>
</body>
</html>
