<%-- 
    Document   : assignments
    Created on : May 29, 2024, 1:43:04 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link rel="stylesheet" href="/styles/assignments.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - Assignments</title>
    </head>
    <body>
        <script src="/scripts/topnav.js"></script>
    <center>
        <h1><%=request.getAttribute("courseCode")%></h1>
        <%=request.getAttribute("assignments")%>
        <%=request.getAttribute("newButton")%>
    </center>
    <script>
        function deleteAssignment(name, id) {
             if (window.confirm("Are you sure you want to delete " + name + "?"))
                 window.location = "/delete-assignment?id=" + id;
        }
    </script>
    </body>
</html>
