package com.mycompany.CodeforSefinal;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DeleteInvoiceController implements Initializable{

    // Implementing all JavaFX ids so that they can be utilized in the code 
    @FXML private Button returnButton;
    @FXML private Button deleteButton;
    @FXML private TableView<Invoice> invoiceViewTable;
    @FXML private TableColumn<Invoice, Integer> IDFX;
    @FXML private TableColumn<Invoice, String> INameFX;
    @FXML private TableColumn<Invoice, String> CNameFX;
    @FXML private TableColumn<Invoice, Double> LatFX;
    @FXML private TableColumn<Invoice, Double> LongFX;
    @FXML private TableColumn<Invoice, Timestamp> DDateFX;

    // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;

    // FXML Connected method, uses ActionEvent to detect when the button "return" is pressed, when it is pressed it will call the method. 
    @FXML
    private void handleReturnToPrimary(ActionEvent event) throws IOException {

        // Code that will load up the "primary.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();

        // Code that loads up the "primary.fxml"
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Creates a temporary initialization to test delete function
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Item> testItemArray = new ArrayList<Item>();

        Timestamp timestampTest = Timestamp.from(Instant.now());

        var invoice1 = new Invoice("New Tools", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        var invoice2 = new Invoice("New Printer", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        var invoice3 = new Invoice("New RJ-45 Ports", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        ArrayList<Invoice> testArray = new ArrayList<Invoice>();
        testArray.add(invoice1);
        testArray.add(invoice2);
        testArray.add(invoice3);

        ObservableList<Invoice> observableInvoices = FXCollections.observableArrayList(testArray);

        IDFX.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
        INameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceName"));
        CNameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("clientName"));
        LatFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("latitude"));
        LongFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("longitude"));
        DDateFX.setCellValueFactory(new PropertyValueFactory<Invoice, Timestamp>("date"));

        invoiceViewTable.setItems(observableInvoices);
    }
    
    @FXML
    private void handleDeleteInvoice(ActionEvent event) throws IOException {
        Invoice selectedInvoice = invoiceViewTable.getSelectionModel().getSelectedItem();
        
        if(selectedInvoice == null){
            System.out.println("Please select an invoice to delete");
            return;
        }
        else {
            //ConnectToDatabase.deleteInvoiceById(selectedInvoice.getInvoiceID());

            invoiceViewTable.getItems().remove(selectedInvoice);
        }
    }
}