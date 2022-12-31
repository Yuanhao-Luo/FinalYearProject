<%@ page language="java" pageEncoding="UTF-8"
         import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: YuanhaoLuo
  Date: 2022/9/4
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    String musicname =(String) session.getAttribute("musicname");
    List musicList = (List<String>)session.getAttribute("musicList");
%>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="value" items="${musicList}">
    <a href="/m/video?title=${value}">${value}</a>
    <br>
</c:forEach>
</body>
</html>
