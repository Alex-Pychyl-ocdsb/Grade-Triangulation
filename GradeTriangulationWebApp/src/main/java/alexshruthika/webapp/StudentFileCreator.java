/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.io.*;
import java.sql.*;

/**
 *
 * @author alexp
 */
public class StudentFileCreator {
    public static String getName(int studentID) {
        String out = null;
        try (Connection con = DatabaseConnection.init()) {
            // get student name
            PreparedStatement st = con.prepareStatement(
            "select first_name, last_name from students where id=?");
            st.setInt(1, studentID);
            ResultSet result = st.executeQuery();
            result.next();
            out = result.getString("last_name") + "_"
                 + result.getString("first_name");
            out = out.replace('.', '_');
            out = out.replace(',', '_');
            out = out.replace(' ', '_');
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return out;
    }
    
    public static String create(int studentID, int classID, PrintWriter out) {
        try (Connection con = DatabaseConnection.init()) {
            int assignmentID;
            String value;
            ResultSet result;
            ResultSet assignments;
            PreparedStatement st;
            
            // get assignment ids and names
            st = con.prepareStatement(
            "select id, name from assignments where assignment_class_id=?");
            st.setInt(1, classID);
            assignments = st.executeQuery();
            while (assignments.next()) {
                assignmentID = assignments.getInt("id");
                // get assignment table value
                st = con.prepareStatement("select * from assignment"
                   + assignmentID + " where assignment" + assignmentID
                   + "_student_id=?");
                st.setInt(1, studentID);
                result = st.executeQuery();
                result.next();
                // print criteria
                for (int i = 2; i <= result.getMetaData().getColumnCount(); i++) {
                    value = result.getMetaData().getColumnName(i);
                    value = value.substring(0, value.lastIndexOf('_'));
                    out.print("," + value);
                }
                out.print("\n" + assignments.getString("name"));
                for (int i = 2; i <= result.getMetaData().getColumnCount(); i++) {
                    value = result.getString(i);
                    if (value == null) value = "";
                    out.print("," + value);
                }
                out.println();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e);
        }
        return null;
    }
}
