/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.sql.*;
import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.PrivateServlet;

/**
 *
 * @author alexp
 */
public class Classes extends PrivateServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classes = "";
        response.setContentType("text/html;charset=UTF-8");
        try (Connection con = DatabaseConnection.init()) {
            PreparedStatement st = con.prepareStatement(
                    "select * from classes where user_id=?");
            st.setInt(1, (Integer)request.getSession().getAttribute("userID"));
            ResultSet result = st.executeQuery();
            while (result.next()) {
                classes += makeClass(result);
            }
            request.setAttribute("classes", classes);
            if (classes.isEmpty()) {
                request.setAttribute("newButton", "You do not have any classes. <button onclick='window.location = \"/new-class\"'>Create one now.</button>");
            } else {
                request.setAttribute("newButton", "<br><button onclick='window.location = \"/new-class\"'>Create new class</button>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        request.getRequestDispatcher("/WEB-INF/classes.jsp").include(request, response);
    }
    
    private String makeClass(ResultSet result) throws SQLException {
        return "<div class='class-info' align='left'><div class='header'><span>"
             + result.getString("course_code") + "</span><span style='float:right'>"
             + result.getInt("year") + "<div class='dropdown'><button class='more-options'>" 
             + "<img src='/images/more-options.png'></button><div class='dropdown-content'>"
             + "<a onclick='window.location = \"/download-class\"'>Download</a>"
             + "<a onclick='window.location = \"/edit-class\"'>Edit</a>"
             + "<a class='delete' onclick='deleteClass(" + result.getInt("id")
             + ", \"" + result.getString("course_code") + "\")'>Delete</a></div></div></span></div>"
             + "Semester " + result.getInt("semester") + "<span style='float:right'>"
             + "Period " + result.getInt("period") + "</span><br>"
             + "<button onclick=\"goToClass(\'" + result.getInt("id")
             + "', '/assignments')\">assignments</button>&nbsp&nbsp&nbsp"
             + "<button onclick=\"goToClass('" + result.getInt("id")
                  + "', '/students')\">students</button></div>";
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Shows all classes of user";
    }// </editor-fold>

}
