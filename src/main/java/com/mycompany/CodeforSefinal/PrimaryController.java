package com.mycompany.CodeforSefinal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// take all the needed classes from HW2 and put them in the floder for project
// make the array list of monster ojcets that is going to be in the controller
// make a GUI with all the needed things 
// Now do 2 new features for java fx


public class PrimaryController implements Initializable{
    
    @FXML private Button testButton;
    @FXML private Button viewInvoices;
    @FXML private Button deleteInvoice;

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @FXML
    private void GotoInvoices (javafx.event.ActionEvent event) throws IOException {
        System.out.println("Button Clicked");
        // Load the new scene (InvoicePage.fxml)
        Parent invoicePage = FXMLLoader.load(getClass().getResource("InvoicePage.fxml"));
        
        // Get the current stage (the window where the button was clicked)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Set the new scene to the stage
        Scene scene = new Scene(invoicePage);
        currentStage.setScene(scene);
        // Show the new scene (second screen)
        currentStage.show();
    }
    
    @FXML
    private void ViewInvoices (javafx.event.ActionEvent event) throws IOException {
        Parent invoicePage = FXMLLoader.load(getClass().getResource("secondary.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(invoicePage);
        currentStage.setScene(scene);
        currentStage.show();
    }
    @FXML
    private void DeleteInvoice (javafx.event.ActionEvent event) throws IOException {
        System.out.println("Button Clicked");
    }
}
        
        
        
        
        
        
        
        

    
    
    
    
    
    
    
    
    
    
    
    
    
   

