<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/13/2023
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Select sandwich condiments</h1>
<form action="/save" method="post">
    <p>Lettuce</p>
    <input type="checkbox" name="option" value="Lettuce">
    <p>Tomota</p>
    <input type="checkbox" name="option" value="Tomota">
    <p>Mustard</p>
    <input type="checkbox" name="option" value="Mustard">
    <p>Sprouts</p>
    <input type="checkbox" name="option" value="Sprouts">
    <hr>
    <button type="submit">Save</button>
</form>
</body>
</html>
