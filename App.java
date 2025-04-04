package com.mycompany.CodeforSefinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App   {

    

    public static void main(String[] args) {
        //launch();
        
        // Format: longitude,latitude (NOT latitude,longitude)
        String start = "-74.005974,40.712776";  // New York
        String end = "-73.935242,40.730610";    // Brooklyn

        String response = OpenRouteServiceAPI.getDistance(start, end);
        RouteParser.parseDistanceData(response);


        try (Connection conn = conncnet_to_database.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Connection to the database was successful!");
            } else {
                System.out.println("❌ Connection failed or was closed.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error connecting to the database:");
            e.printStackTrace();
        }
        
    
        
        
        
        
        
    } 

}
