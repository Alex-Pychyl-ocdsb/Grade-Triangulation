<%-- 
    Document   : assignment
    Created on : Jun 2, 2024, 12:52:30 p.m.
    Author     : alexp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link rel="stylesheet" href="/styles/assignment.css" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("name")%></title>
    </head>
    <body>
    <script src="/scripts/topnav.js"></script>
    <center>
        <h1><%=request.getAttribute("name")%></h1>
        <form action="/save-assignment" method="post">
            <table>
                <tr>
                    <th>Student</th>
                    <%=request.getAttribute("criteria")%>
                </tr>
                <%=request.getAttribute("rows")%>
            </table>
        <button type="submit" onclick="isSaved = true" class="important-button">Save</button>
        <button type="submit" onclick="isSaved = true" name="download" class="important-button">Download this table</button>
        </form>
    </center>
    <script>
        var isSaved = true;
        
        function setValue(val) {
            var cell = val.parentElement.parentElement;
            cell.getElementsByClassName("dropbtn")[0].textContent = val.textContent;
            cell.getElementsByClassName("value")[0].value = val.textContent;
            isSaved = false;
        }
        
        function leaveTo(location) {
            if (isSaved || window.confirm('Are you sure you want to exit without saving?'))
                window.location = location;
        }
        
        function resizeInput(input) {
            if (input.value.length < 10)
                input.size = 10;
            else
                input.size = input.value.length;
            isSaved = false;
        }
    </script>
    </body>
</html>
