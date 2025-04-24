/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.Objects.Invoice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

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
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colInvoiceName.setCellValueFactory(new PropertyValueFactory<>("invoiceName"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colZipCode.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
      
        resultsTable.setItems(FXCollections.observableArrayList());
    }

    @FXML
    private void handleSearchInvoices() {
        // your DAO search call and population logic here
    }

    @FXML
    private void handleReturnToPrimary(javafx.event.ActionEvent event) throws Exception {
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("primary.fxml"));
        javafx.stage.Stage stage = (javafx.stage.Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }
}
