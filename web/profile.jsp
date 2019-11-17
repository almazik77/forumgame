<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 27.10.2019
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script>
        $(function () {
            $("#header").load("main.html #header");
            $("#footer").load("main.html #footer");
        });
    </script>
</head>
<body>
<div id="header"></div>


<div>
    <%=request.getAttribute("userLogin")%>
</div>

<div id="footer"></div>
</body>
</html>
