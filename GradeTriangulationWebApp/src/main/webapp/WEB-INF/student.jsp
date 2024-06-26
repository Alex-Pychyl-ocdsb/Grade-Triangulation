<%-- 
    Document   : student
    Created on : Jun 7, 2024, 1:19:51 p.m.
    Author     : alexp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link href="/styles/dropdown.css" rel="stylesheet" type="text/css">
    <link href="/styles/student.css" rel="stylesheet" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("name")%></title>
    </head>
    <body>
        <script src="/scripts/topnav.js"></script>
    <center>
        <h1><%=request.getAttribute("name")%></h1>
        <form action="/save-student" method="post" style="display:inline-block">
            <div align="left" class="container">
                <h3 align="left">General Notes:</h3>
                <input class="bordered" 
                       type="text" 
                       name="notes" 
                       value="<%=request.getAttribute("notes")%>" 
                       onkeyup="resizeInput(this)"><br>
                <%=request.getAttribute("rows")%>
            </div>
            <button class="important-button" onclick="isSaved = true" type="submit">Save</button>
            <button class="important-button" onclick="isSaved = true" type="submit" name="download">Download this table</button>
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
