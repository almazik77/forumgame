<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 13.11.2019
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a new game</title>
</head>
<body>
<form action="/newGame" method="post">
    <input type="text" name="game_name" value="">
    <input type="text" name="game_description" value="">
    <input type="submit" value="create new game">
</form>
</body>
</html>
