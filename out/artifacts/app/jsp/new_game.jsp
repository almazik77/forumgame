<%--
  Created by IntelliJ IDEA.
  User: almaz
  Date: 18.11.2019
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <title>Create a new game</title>
</head>
<body>
<div id="header"></div>
<main>

    <div class="container">
        <h1 style="color: darkred">Создание новой игры</h1>
        <form action="<%=request.getContextPath()%>/newGame" method="post">
            <table cellpadding="0" cellspacing="0" class="table" width="99%">
                <tr>
                    <td>Название:<br/></td>
                    <td>&nbsp;</td>
                    <td width="100%">
                        <input class="text" type="text" maxlength="50" name="game_name"
                               style=""/>
                    </td>
                </tr>
                <tr>
                    <td>Ассистент:</td>
                    <td>&nbsp;</td>
                    <td>
                        <select style="background-color:#F9F9F9;border-color:#CCCCCC;border-width:1px;border-style:Solid;color:#444444;font-family:Tahoma;font-size:12px;">
                            <option value="NULL">Нет</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td valign="top">Описание:<br/>
                    </td>
                    <td>&nbsp;</td>
                    <td><input type="text" style="border-style:dashed;" name="game_description"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" class="_button" value="Создать игру">
                    </td>
                </tr>

            </table>
        </form>
    </div>

</main>
<div id="footer"></div>
</body>
</html>
