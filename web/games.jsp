<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 13.11.2019
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Games</title>
</head>
<body>
<div></div>

<div>
    <ul>
        <jsp:useBean id="games" scope="request" type="java.util.List"/>
        <c:forEach items="${games}" var="game">
            <li><a href="<%=request.getRequestURL() + "/game?gameId="%>${game.id}">${game.name}</a></li>
        </c:forEach>
    </ul>

</div>

<div></div>
</body>
</html>
