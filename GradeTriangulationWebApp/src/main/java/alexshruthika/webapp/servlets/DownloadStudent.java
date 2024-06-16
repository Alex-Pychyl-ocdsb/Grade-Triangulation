/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.util.*;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import alexshruthika.webapp.PrivateServlet;
import alexshruthika.webapp.DatabaseConnection;
import alexshruthika.webapp.StudentFileCreator;

/**
 *
 * @author alexp
 */
public class DownloadStudent extends PrivateServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/csv");
        // send back to assignments if no assignmentID
        if (request.getSession().getAttribute("studentID") == null) {
            response.sendRedirect("/students");
            return;
        }
        // get student id
        int studentID = (int)request.getSession().getAttribute("studentID");
        
        
        try (PrintWriter out = response.getWriter()) {
            response.setHeader("Content-Disposition", "attachment; filename="
                    + StudentFileCreator.getName(studentID) + ".csv");
            StudentFileCreator.create(
                    (int)request.getSession().getAttribute("studentID"), 
                    (int)request.getSession().getAttribute("classID"), 
                    out);
        }
        
        response.sendRedirect("/assignment");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Downloads Student to csv";
    }// </editor-fold>

}