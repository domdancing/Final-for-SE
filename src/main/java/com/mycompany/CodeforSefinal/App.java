package com.mycompany.CodeforSefinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javafx.scene.control.Label;
import java.time.LocalDate;

/**
 * JavaFX App
 */
public class App extends Application{
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
       //TestErnest
       //Fruit Salad, Yummy Yummy
        // testinghk
        // Format: longitude,latitude (NOT latitude,longitude)
        
        // Sample items
        Item item1 = new Item("Laptop", 999.99);
        Item item2 = new Item("Mouse", 25.50);
        List<Item> itemList = Arrays.asList(item1, item2);
        // Example client location (Brooklyn, NY)
        double clientLat = 40.730610;
        double clientLon = -73.935242;
        LocalDate date1 = LocalDate.of(2001, 9, 11);
        // Create invoice
        Invoice invoice = new Invoice("20", date1, "Tech Co.", itemList, clientLat, clientLon);
        // Display results
        System.out.println("Client: " + invoice.getClientName());
        System.out.println("Items:");
        for (Item item : invoice.getItems()) {
            System.out.println(" - " + item.getName() + ": $" + item.getPrice());
        }
        System.out.printf("Shipping Price: $%.2f%n", invoice.getShippingPrice());
        System.out.printf("Total Price: $%.2f%n", invoice.getTotalPrice());
    


        
        
        
        
        
        

      
       // testing conntion 
    try (Connection conn = conncnet_to_database.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println(" Connection to the database was successful!");
            } else {
                System.out.println(" Connection failed or was closed.");
            }
        } catch (SQLException e) {
            System.out.println(" Error connecting to the database:");
            e.printStackTrace();
        }

        System.out.println("Hello World!");
        
        
        launch();
        
        
    } 

   
   
}