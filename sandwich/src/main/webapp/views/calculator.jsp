<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/13/2023
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Calculator</h1>
<form action="/" method="get">
<input type="text" name="input1">
<input type="text" name="input2">
<hr>
<input type="submit" name="op" value="+">
<input type="submit" name="op" value="-">
<input type="submit" name="op" value="*">
<input type="submit" name="op" value="/">
</form>

<h1> Result :${result}</h1>
</body>
</html>
