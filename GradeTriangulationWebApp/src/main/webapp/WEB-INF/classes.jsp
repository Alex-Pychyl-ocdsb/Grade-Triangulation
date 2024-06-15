<%-- 
    Document   : classes
    Created on : May 23, 2024, 1:09:55 p.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link rel="stylesheet" href="/styles/dropdown.css" type="text/css">
    <link rel="stylesheet" href="/styles/classes.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Classes</title>
    </head>
    <body>
        <script src="/scripts/topnav.js"></script>
    <center>
        <h1>Your Classes</h1>
        <div style='width: 60%' align='center'>
            <%=request.getAttribute("classes")%>
        </div>
        <%=request.getAttribute("newButton")%>
    </center>
    <script>
        function goToClass(id, location) {
            window.location = location + "?classID=" + id;
        }
        
        function deleteClass(id, name) {
            if (window.confirm("Are you sure you want to delete " + name + "?")) {
                window.location = "/delete-class?id=" + id;
            }
        }
    </script>
    </body>
    
</html>
