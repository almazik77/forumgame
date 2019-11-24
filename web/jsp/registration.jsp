<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>Registration</title>
    <script type='text/html' src='<%=request.getContextPath()+"/"%>footer_and_header_helper.jsp'></script>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(function () {
            $("#header").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #header");
            $("#footer").load("<%=request.getContextPath()%>/jsp/footer_and_header_helper.jsp #footer");
        });

        function checkParams() {
            var login = $('#login').val();
            var email = $('#email').val();
            var password = $('#password').val();
            var password2 = $('#password2').val();

            if (login.length != 0 && password.length != 0 && password == password2) {
                $('#submit').removeAttr('disabled');
            } else {
                $('#submit').attr('disabled', 'disabled');
            }
        }
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
    <form action='<%=request.getContextPath() + "/signUp"%>' method="post">
        <table cellpadding="0" cellspacing="0" class="table_registration">
            <tr>
                <td colspan="2" style="text-align: center"><p style="font-family: Tahoma; font-size: 20px">Создание
                    нового
                    пользователя</p></td>
            </tr>
            <tr>
                <td style="width: 200px;">Логин:</td>
                <td style="width: 300px;"><input class="text" type="text" id='login' name="login" style=""
                                                 onkeyup="checkParams()"/></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input id='password' class="text" type="password" name="password" onkeyup="checkParams()"/></td>
            </tr>
            <tr>
                <td><label for="password2">Повтор пароля:</label></td>
                <td><input id='password2' class="text" type="password" onkeyup="checkParams()" required></td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td><input id='email' class="text" type="text" name="mail" onkeyup="checkParams()" required/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input id='submit' type="submit" value="Регистрация" disabled
                           style="background-color:#EAEAEA;border-color:#CCCCCC;border-width:1px;border-style:Dashed;
                   color:#444444;font-family:Tahoma;font-weight:bold;font-size:15px; text-align: center">
                </td>
            </tr>
        </table>
    </form>
</main>

<div id="footer"></div>

</body>
</html>
