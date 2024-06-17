/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alexshruthika.webapp;

import java.sql.*;

/**
 * Initializes connection to database
 * Found on https://www.geeksforgeeks.org/java-servlet-and-jdbc-example-insert-data-in-mysql/
 * 
 * @author alexp
 */
public class DatabaseConnection {
    public static Connection init() throws SQLException, ClassNotFoundException {
        // connection to windows mysql server
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3006/gradetriangulation"
//              , "root"
//              , "admin");
        // connection to debian mariadb server
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/gradetriangulation"
              , "root"
              , "admin");
    }
}
