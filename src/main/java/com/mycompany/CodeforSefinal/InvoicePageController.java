/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class InvoicePageController {

    @FXML private TextField clientNameField;
    @FXML private TextField invoiceNumberField;
    @FXML private TextField latitudeField;
    @FXML private TextField longitudeField;
    @FXML private TextField itemNameField;
    @FXML private TextField itemPriceField;
    @FXML private ListView<String> itemsListView;
    @FXML private Button returnButton;
    @FXML
      private DatePicker datePicker;
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    private List<Item> items = new ArrayList<>();

    // This method handles adding items to the invoice
    @FXML
    private void handleAddItem() {
        String itemName = itemNameField.getText();
        String itemPriceText = itemPriceField.getText();

        if (itemName.isEmpty() || itemPriceText.isEmpty()) {
            showError("Please fill in both item name and item price.");
            return;
        }
        
    

        try {
            double itemPrice = Double.parseDouble(itemPriceText);

            // Create an item object and add it to the list
            Item item = new Item(itemName, itemPrice);
            items.add(item);

            // Display the added item in the ListView
            itemsListView.getItems().add(itemName + " - $" + itemPrice);

            // Clear the item fields
            itemNameField.clear();
            itemPriceField.clear();

        } catch (NumberFormatException e) {
            showError("Invalid item price.");
        }
    }

    // This method handles creating the invoice
    @FXML
    private void handleCreateInvoice() {
        String clientName = clientNameField.getText();
        String invoiceNumber = invoiceNumberField.getText();
        String latitudeText = latitudeField.getText();
        String longitudeText = longitudeField.getText();

        // Validate input
        if (clientName.isEmpty() || invoiceNumber.isEmpty() || latitudeText.isEmpty() || longitudeText.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            showError("Please select a date.");
            return;
                        }

        try {
            double latitude = Double.parseDouble(latitudeText);
            double longitude = Double.parseDouble(longitudeText);

            
            
            
            // Create the invoice with the added items
            Invoice invoice = new Invoice(invoiceNumber, selectedDate, clientName, items, latitude, longitude);

          // Save to the database
    ConnectToDatabase.saveInvoice(invoice);

    // ✅ Show confirmation message this will be delted later
    showInfo("Success", "Invoice has been saved to the database.");
            
            // Show the invoice details
            showInvoiceDetails(invoice);

        } catch (NumberFormatException e) {
            showError("Invalid latitude or longitude.");
        }
    }
    
    @FXML
    private void handleReturnToPrimary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

   private void showInvoiceDetails(Invoice invoice) {
    // Round prices to 2 decimal places
    double shippingPrice = invoice.getShippingPrice();
    double totalPrice = invoice.getTotalPrice();

    // Format the shipping and total prices to 2 decimal places
    String formattedShippingPrice = String.format("%.2f", shippingPrice);
    String formattedTotalPrice = String.format("%.2f", totalPrice);

    // Create a string to display the list of items
    StringBuilder itemsList = new StringBuilder();
    for (Item item : invoice.getItems()) {
        itemsList.append(item.getName())
                 .append(" - $")
                 .append(String.format("%.2f", item.getPrice()))
                 .append("\n");
    }

    // Create the message string with invoice details
    String message = "Invoice Number: " + invoice.getInvoiceNumber() + "\n" +
                     "Client: " + invoice.getClientName() + "\n" +
                     "Total Price: $" + formattedTotalPrice + "\n" +
                     "Shipping Price: $" + formattedShippingPrice + "\n" +
                     "Distance: " + invoice.getDistance() + " km\n\n" +
                     "Date: " + invoice.getDate().toString() + "\n" +  // <- Added date here
                     "Items:\n" + itemsList.toString();
    
    // Show the message in a pop-up or alert
    showInfo("Invoice Created", message);
}


    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
