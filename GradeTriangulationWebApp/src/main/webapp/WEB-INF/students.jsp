<%-- 
    Document   : students
    Created on : Jun 2, 2024, 12:50:31 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link rel="stylesheet" href="/styles/students.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - students</title>
    </head>
    <body>
        <script src="/scripts/topnav.js"></script>
    <center>
        <h1><%=request.getAttribute("courseCode")%></h1>
        <%=request.getAttribute("students")%>
    </center>
    <script>
        function deleteStudent(name, id) {
             if (window.confirm("Are you sure you want to delete " + name + "?"))
                 window.location = "/delete-student?id=" + id;
        }
    </script>
    </body>
</html>
