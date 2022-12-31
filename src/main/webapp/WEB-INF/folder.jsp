<%@ page language="java" pageEncoding="UTF-8"
         import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%
    String musicname =(String) session.getAttribute("musicname");
    List musicList = (List<String>)session.getAttribute("musicList");
%>
<head>
    <title>music</title>
</head>
<body>
<c:forEach var="value" items="${musicList}">
    <a href="/m/video?title=${value}">${value}</a>
    <br>
</c:forEach>
<a href="/backToIndex" style="top: 10%;right: 10%;position: fixed">
    back
</a>
<%--<video height="720" width="1280" controls="controls">--%>
<%--    <source src="player" type="video/mp4">--%>
<%--</video>--%>
</body>
</html>