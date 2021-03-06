<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
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
</head>
<body>
<div id="header"></div>

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

            <div class="row featurette" style="height: 70%; width: 100%; margin: auto;display: flex">
                <div class=" order-md-1" style="width: 20%; height: 100%; ">

                    <img src="<%=request.getAttribute("userAvatar")%>" alt=""
                         style="max-width: 300px; max-height: 200px;">
                    <%if (request.getAttribute("userId").equals(request.getSession().getAttribute("userId"))) { %>
                    <form action="<%=request.getContextPath() + "/profile"%>" method="post"
                          enctype="multipart/form-data">
                        <p><label for="avatar" class="_button">Выбрать другой аватар</label>
                            <input type="file" id="avatar" name="file" style="display: none">
                            <input type="submit" value="Сменить аватар">
                        </p>
                    </form>
                    <% } %>
                </div>
                <div class="col-md-5 order-md-2" style="width: 80%;">
                    <p style="color: saddlebrown">Логин: <%=request.getAttribute("userLogin")%>
                    </p>
                    <%if (request.getAttribute("userId").equals(request.getSession().getAttribute("userId"))) { %>
                    <button class="_button" onclick="location.href='<%=request.getContextPath() + "/newGame"%>'">Создать
                        новую игру
                    </button>
                    <% } %>
                </div>
            </div>
        </div>

    </div>
</main>

<div id="footer"></div>

</body>
</html>
