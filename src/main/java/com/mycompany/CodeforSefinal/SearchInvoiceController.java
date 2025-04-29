/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.DAO.InvoiceDAOImpl;
import com.mycompany.CodeforSefinal.Objects.Invoice;
import com.mycompany.CodeforSefinal.InvoiceViewController.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SearchInvoiceController implements Initializable {

    @FXML private TextField invoiceNameField;
    @FXML private TextField clientNameField;
    @FXML private TextField zipCodeField;
    @FXML private DatePicker datePicker;

    @FXML private TableView<Invoice> resultsTable;
    @FXML private TableColumn<Invoice, String> colInvoiceName;
    @FXML private TableColumn<Invoice, String> colClientName;
    @FXML private TableColumn<Invoice, String> colZipCode;
    @FXML private TableColumn<Invoice, String> colDate;
     // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    
       
    
    @FXML
private Button viewItemsButton;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        colInvoiceName.setCellValueFactory(new PropertyValueFactory<>("invoiceName"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colZipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
      
        resultsTable.setItems(FXCollections.observableArrayList());
    }

    
        // your DAO search call and population logic here
        @FXML
private void handleSearchInvoices() {
    String invoiceName = invoiceNameField.getText().trim();
    String clientName = clientNameField.getText().trim();
    String zipCode = zipCodeField.getText().trim();
    LocalDate date = datePicker.getValue(); // can be null

    InvoiceDAOImpl dao = new InvoiceDAOImpl();
    ArrayList<Invoice> invoices = dao.searchInvoices(invoiceName, clientName, zipCode, date);

    ObservableList<Invoice> observableInvoices = FXCollections.observableArrayList(invoices);
    resultsTable.setItems(observableInvoices);
}

       
    @FXML
    private void handleReturnToPrimary(javafx.event.ActionEvent event) throws Exception {
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("primary.fxml"));
        javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }
    
    @FXML
    private void handleViewItems(ActionEvent event) throws IOException {
        Invoice selectedInvoice = resultsTable.getSelectionModel().getSelectedItem();

        if (selectedInvoice == null) {
            showError("Please select an invoice to view its items.");
            return;
        }

      FXMLLoader loader = new FXMLLoader(getClass().getResource("InvoiceItemsPage.fxml"));
    Parent invoicePage = loader.load();
    InvoiceItemsController controller = loader.getController();
    controller.setInvoiceId(selectedInvoice.getInvoiceID()); // ✅ This fixes the issue
        
        // Get the current stage (the window where the button was clicked)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Set the new scene to the stage
        Scene scene = new Scene(invoicePage);
        currentStage.setScene(scene);
        // Show the new scene (second screen)
        currentStage.show();
    }
    
    
    
  
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
   
    
    
    
    
}
