<%--
  Created by IntelliJ IDEA.
  User: YuanhaoLuo
  Date: 2022/7/21
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"
         import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String title =(String) session.getAttribute("title");
    title = title.substring(title.length()-10,title.length()-2);
    String mimeType = (String) session.getAttribute("mimeType");
%>
<html>
<head>
    <title>video</title>
</head>
<body>
<video  height="720" width="1280"  controls preload="auto">
    <source src="/players?title=<%=title%>mimeType" type="<%=mimeType%>">
    <source src="/players?title=<%=title%>mp4" type="video/mp4; codecs=hevc">
<%--    <source src="/players?title=<%=title%>" type="video/ogg">--%>
<%--    <source src="/players?title=<%=title%>x-matroska" type="video/webm">--%>
<%--    <object data="/players?title=<%=title%>" width="320" height="240">--%>
<%--        <embed src="/players?title=<%=title%>" width="320" height="240">--%>
<%--    </object>--%>
</video>
<a href="/backToIndex" style="top: 10%;right: 10%;position: fixed">
    back
</a>

</body>
</html>

