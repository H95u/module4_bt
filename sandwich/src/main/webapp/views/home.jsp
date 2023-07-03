<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/13/2023
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Các gia vị đi kèm bạn chọn</h1>
<c:forEach items="${option}" var="o">
    <p>${o}</p>
</c:forEach>
</body>
</html>
