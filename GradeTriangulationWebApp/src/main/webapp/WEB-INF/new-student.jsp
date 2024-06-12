<%-- 
    Document   : new-student
    Created on : Jun 10, 2024, 11:48:26 a.m.
    Author     : alexp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="/styles/generic.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - New Student</title>
    </head>
    <body>
    <center>
        <h1>Add a new student</h1>
        <form action="new-student" method="post">
            Student Name (First then last): <br>
            <input type="text" name="firstName" size="10" onkeyup="resizeInput(this)" required>
            <input type="text" name="lastName" size="10" onkeyup="resizeInput(this)" required><br>
            <input class="important-button" type="submit" value="Add">
        </form>
    </center>
    <script>
        function resizeInput(input) {
            if (input.value.length < 10)
                input.size = 10;
            else
                input.size = input.value.length;
        }
    </script>
    </body>
</html>
