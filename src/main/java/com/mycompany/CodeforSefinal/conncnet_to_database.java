/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dominick valentine
 */
public class conncnet_to_database {
    //Hey, this is Ernie!

    private static final String URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12771146";
            
            
    private static final String USER = "sql12771146";
    private static final String PASSWORD = "XDSQkgzNe9";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


