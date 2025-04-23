package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import com.mycompany.CodeforSefinal.Objects.Invoice;
import com.mycompany.CodeforSefinal.DAO.DAOFactory;
import com.mycompany.CodeforSefinal.DAO.InvoiceDAO;
import com.mycompany.CodeforSefinal.DAO.InvoiceDAOImpl;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.converter.DoubleStringConverter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


    

public class InvoiceViewController implements Initializable{
    
    // Implementing all JavaFX ids so that they can be utilized in the code 
    // buttons
    @FXML private Button returnButton;
    @FXML private Button viewItemsButton;
    @FXML private Button deleteButton;
    // for the tables 
    @FXML private TableView<Invoice> invoiceViewTable;
    @FXML private TableColumn<Invoice, Integer> IDFX;
    @FXML private TableColumn<Invoice, String> INameFX;
    @FXML private TableColumn<Invoice, String> CNameFX;
    @FXML private TableColumn<Invoice, Double> LatFX;
    @FXML private TableColumn<Invoice, Double> LongFX;
    @FXML private TableColumn<Invoice, Timestamp> DDateFX;
    
    // Test ArrayList - does nothing for now 
    private ArrayList<Invoice> invoices = new ArrayList<>();
    
    // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    // FXML Connected method, uses ActionEvent to detect when the button "return" is pressed, when it is pressed it will call the method. 
    @FXML
    private void handleReturnToPrimaryFromInvoicesView(ActionEvent event) throws IOException {
        
        // Code that will load up the "primary.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();
        
        // Code that loads up the "primary.fxml"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    @FXML
    private void handleSwitchToItemView(ActionEvent event) throws IOException {
        
        //Load
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemViewPage.fxml"));
        root = loader.load();
        ItemViewController controller = loader.getController();
        
        //Set Scene
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

@FXML
    private void handleDeleteInvoice(ActionEvent event) throws IOException {
        Invoice selectedInvoice = invoiceViewTable.getSelectionModel().getSelectedItem();

        if(selectedInvoice == null){
            System.out.println("Please select an invoice to delete");
            return;
        }
        else {
             // ✅ Correct: create DAO instance and call the method
             
    // ✅ Use the DAO Factory
    InvoiceDAO invoiceDAO = DAOFactory.getInvoiceDAO();

    try {
        invoiceDAO.deleteInvoicebyID(selectedInvoice.getInvoiceID());
        invoiceViewTable.getItems().remove(selectedInvoice);
        System.out.println("Invoice deleted successfully.");
    } catch (Exception e) {
        System.out.println("Failed to delete invoice: " + e.getMessage());
        e.printStackTrace();
    }
            //ConnectToDatabase.deleteInvoicebyID(selectedInvoice.getInvoiceID());

            invoiceViewTable.getItems().remove(selectedInvoice);
        }
    }







    
    // FXML file that runs when the "secondary.fxml" loads
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Fake back-end filler data
        ArrayList<QuantityItem> testItemArray = new ArrayList<QuantityItem>();
        
        // Creates a sample time test object for test data 
        Timestamp timestampTest = Timestamp.from(Instant.now());
        
        var invoice1 = new Invoice("New Tools", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        var invoice2 = new Invoice("New Printer", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        var invoice3 = new Invoice("New RJ-45 Ports", timestampTest, "Ryan", testItemArray, 99.9, 99.9);
        ArrayList<Invoice> testArray = new ArrayList<Invoice>();
        testArray.add(invoice1);
        testArray.add(invoice2);
        testArray.add(invoice3);
        
        // The arraylist must be turned into a obervablelist to be observed by the viewTable
        ObservableList<Invoice> observableInvoices = FXCollections.observableArrayList(testArray);
        
        // Checks all the getter methods inside of the Invoice and searches for the attribute in the quotes
        // If it finds it, it will call its getter method found inside the invoice class, if there is no getter then the program will fail
        IDFX.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
        INameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceName"));
        CNameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("clientName"));
        LatFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("latitude"));
        LongFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("longitude"));
        DDateFX.setCellValueFactory(new PropertyValueFactory<Invoice, Timestamp>("date"));

         // Set table to be editable
        invoiceViewTable.isEditable();
        INameFX.setCellFactory(TextFieldTableCell.forTableColumn());
        CNameFX.setCellFactory(TextFieldTableCell.forTableColumn());
        LatFX.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        LongFX.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        // Set the tableView to display these attributes
        invoiceViewTable.setItems(observableInvoices);
        
    }
    
   
    }
    
    /*

private void handleUpdateInvoice(ActionEvent event) {
    Invoice selectedInvoice = invoiceViewTable.getSelectionModel().getSelectedItem();

    if (selectedInvoice == null) {
        showError("Please select an invoice to update.");
        return;
    }

    try {
        // Update fields on the selectedInvoice object based on user inputs
        selectedInvoice.setInvoiceName(invoiceNumberField.getText());
        selectedInvoice.setClientName(clientNameField.getText());
        selectedInvoice.setLatitude(Double.parseDouble(latitudeField.getText()));
        selectedInvoice.setLongitude(Double.parseDouble(longitudeField.getText()));

        // Optional: update items list, date, etc.
        Timestamp selectedDate = Timestamp.valueOf(datePicker.getValue().atStartOfDay());
        selectedInvoice.setDate(selectedDate);

        // Recalculate values
        // You can add logic to call a recalculate method or recreate total, shipping, etc.

        // Get DAO and update
        InvoiceDAO invoiceDAO = DAOFactory.getInvoiceDAO();
        invoiceDAO.updateInvoice(selectedInvoice);

        showInfo("Success", "Invoice updated successfully.");
        invoiceViewTable.refresh();

    } catch (Exception e) {
        showError("Failed to update invoice: " + e.getMessage());
        e.printStackTrace();
    }
}

*/
    
    
    

                
