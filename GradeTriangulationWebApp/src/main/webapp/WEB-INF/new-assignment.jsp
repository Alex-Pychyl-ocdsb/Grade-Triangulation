<%-- 
    Document   : new-assignment
    Created on : May 31, 2024, 2:07:16 p.m.
    Author     : alexp
    TODO: make params not delete on incorrect entry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="html">
    <link rel="stylesheet" href="/styles/new-assignment.css" type="text/css">
    <link rel="stylesheet" href="/styles/dropdown.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getAttribute("courseCode")%> - New Assignment</title>
    </head>
    <body>
        <script src="/scripts/topnav.js"></script>
    <center>
        <div class="assignment-form">
            <h1>Create a new assignment</h1>
            <form action="new-assignment" method="post">
                Type of assignment:
                <input type="text" name="type" required> <br>
                Name:
                <input type="text" name="name">
                <h3>Criteria:</h3>
                <table name="criteria" id="criteria">
                <tr>
                <th>Name</th>
                <th>Type</th>
                </tr>
                <tr>
                    <td><input type="text" name="criterium0"></td>
                <td>
                <div class="dropdown">
                    <button type="button" class="dropbtn">[Type]</button>
                    <input name="type0" type="hidden" class="type">
                    <div class="dropdown-content">
                      <%=request.getAttribute("types")%>
                    </div>
                </div>
                </td>
                </tr>
                </table>
                    <button type="button" onclick="addRow()">Add Criterium</button>
                <br><%=request.getAttribute("message")%><br>
                <input class='important-button' type="submit" value="Create Assignment">
            </form>
        </div>
    </center>
    <script>
        document.getElementById("<%=request.getAttribute("focused")%>").focus();
        
        function addRow() {
            var criteria = document.getElementById("criteria");
            var row = criteria.insertRow();
            row.insertCell(0).innerHTML = '<input type="text" name="criterium' + (criteria.rows.length - 2) + '">';
            row.insertCell(1).innerHTML = 
                '<div class="dropdown">'
                + '<button type="button" class="dropbtn">[Type]</button>\n'
                + '<input name="type' + (criteria.rows.length - 2) + '" type="hidden" class="type">'
                + '<div class="dropdown-content">\n'
                  + '<%=request.getAttribute("types")%>'
                + '</div>'
              + '</div>';
        }
        
        function setType(type) {
            var cell = type.parentElement.parentElement;
            cell.getElementsByClassName("dropbtn")[0].textContent = type.textContent;
            cell.getElementsByClassName("type")[0].value = type.textContent;
        }
    </script>
    </body>
</html>
