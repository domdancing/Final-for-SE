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
    
        
        
        
        
        
    } 

}
