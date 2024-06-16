/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package alexshruthika.webapp.servlets;

import java.io.*;
import java.util.zip.*;
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
public class DownloadClass extends PrivateServlet {

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
        response.setContentType("application/zip");
        // send back to assignments if no assignmentID
        if (request.getParameter("id") == null) {
            response.sendRedirect("/classes");
            return;
        }
        try (Connection con = DatabaseConnection.init();
             OutputStream out = response.getOutputStream()) {
            // get course code
            ResultSet result;
            PreparedStatement st = con.prepareStatement(
            "select course_code from classes where id=?");
            st.setInt(1, Integer.parseInt(request.getParameter("id")));
            result = st.executeQuery();
            result.next();
            response.setHeader("Content-Disposition", "attachment; filename=" + result.getString(1) + ".zip");
            
            // set up zip file
            File zipFile = File.createTempFile("/temp/" + result.getString(1), ".zip");
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File studentFile;
            PrintWriter studentWriter;
            String fileName;
            
            // get student ids
            st = con.prepareStatement(
            "select id from students where student_class_id=?");
            st.setInt(1, Integer.parseInt(request.getParameter("id")));
            result = st.executeQuery();
            while (result.next()) {
                fileName = StudentFileCreator.getName(result.getInt(1));
                studentFile = File.createTempFile("/temp/" + fileName, ".csv");
                studentWriter = new PrintWriter(new FileWriter(studentFile));
                StudentFileCreator.create(result.getInt(1), Integer.parseInt(request.getParameter("id")), studentWriter);
                studentWriter.close();
                addFile(studentFile, fileName, zos);
                studentFile.delete();
            }
            zos.close();
            fos.close();
            
            // send to client
            FileInputStream inputStream = new FileInputStream(zipFile);
            int in;
            while ((in = inputStream.read()) != -1)
                out.write(in);
            inputStream.close();
            zipFile.delete();
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
            System.err.println("Error: " + e);
        }
        
        
        response.sendRedirect("/classes");
    }
    
    private void addFile(File file, String newName, ZipOutputStream zos) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(newName + ".csv");
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Downloads all Students to csv";
    }// </editor-fold>

}