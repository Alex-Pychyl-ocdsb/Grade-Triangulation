<%-- 
    Document   : assignments
    Created on : May 29, 2024, 1:43:04 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/styles/topnav.css" type="text/css">
    <link rel="stylesheet" href="/styles/generic.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - Assignments</title>
    </head>
    <body>
        <div class="topnav">
            <button onclick="window.location = '/login'">Log out</button>
            <button onclick="window.location = '/classes'">Home</button>
        </div>
    <center>
        <h1><%=request.getAttribute("classInfo")%></h1>
        <div class="assignments">
            <%=request.getAttribute("assignments")%>
        </div>
        <%=request.getAttribute("newButton")%>
    </center>
    </body>
</html>
