<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <script type='text/html' src='main.html'></script>
    <meta charset="UTF-8">
    <title>Registration</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
<form action='<%=request.getContextPath() + "/signUp"%>' method="post">
    <table cellpadding="0" cellspacing="0" class="table_registration">
        <tr>
            <td colspan="2" style="text-align: center"><p style="font-family: Tahoma; font-size: 20px">Создание нового
                пользователя</p></td>
        </tr>
        <tr>
            <td style="width: 200px;">Логин:</td>
            <td style="width: 300px;"><input class="text" type="text" name="login" style=""/></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input class="text" type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Повтор пароля:</td>
            <td><input class="text" type="password"></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><input class="text" type="text" name="mail"/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Регистрация"
                       style="background-color:#EAEAEA;border-color:#CCCCCC;border-width:1px;border-style:Dashed;
                   color:#444444;font-family:Tahoma;font-weight:bold;font-size:15px; text-align: center">
            </td>
        </tr>
</form>
</table>


<div id="footer"></div>


</body>
</html>