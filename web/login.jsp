<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 20.10.2019
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <script type='text/html' src='main.html'></script>
    <meta charset="UTF-8">
    <title>Login</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript">

        var Msg = '<%=request.getAttribute("err_msg")%>';
        if (Msg != "null") {
            function alertName() {
                alert(<%=request.getAttribute("err_msg")%>);
                <%request.removeAttribute("err_msg");%>
            }
        }
    </script>
    <script>
        $(function () {
            $("#header").load("main.html #header");
            $("#footer").load("main.html #footer");
        });
    </script>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div id="header"></div>

<form action='<%=request.getContextPath() + "/login"%>' method="post">
    <table cellpadding="0" cellspacing="0" class="table_login">
        <tr>
            <td colspan="2" style="text-align: center"><p style="font-family: Tahoma; font-size: 20px">Вход</p></td>
        </tr>
        <tr>
            <td style="width: 200px;">Логин:</td>
            <td style="width: 300px;"><input class="text" type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input class="text" type="password" name="password"/></td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" value="Войти"
                       style="background-color:#EAEAEA;border-color:#CCCCCC;border-width:1px;border-style:Dashed;
                   color:#444444;font-family:Tahoma;font-weight:bold;font-size:15px; text-align: center">
            </td>
        </tr>

    </table>
</form>
<div id="footer"></div>
</body>
</html>
