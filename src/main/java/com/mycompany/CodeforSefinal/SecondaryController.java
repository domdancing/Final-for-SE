package com.mycompany.CodeforSefinal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


    

public class SecondaryController implements Initializable{
    
    @FXML private Button returnButton;
    @FXML private TableView<Invoice> invoiceViewTable;
    @FXML private TableColumn<Invoice, Integer> IDFX;
    @FXML private TableColumn<Invoice, String> INameFX;
    @FXML private TableColumn<Invoice, String> CNameFX;
    @FXML private TableColumn<Invoice, Double> LatFX;
    @FXML private TableColumn<Invoice, Double> LongFX;
    @FXML private TableColumn<Invoice, String> DDateFX;
    
    private ArrayList<Invoice> invoices = new ArrayList<>();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void handleReturnToPrimaryFromInvoicesView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void getList(ArrayList<Invoice> newList){
        invoices = newList;
    }
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Invoice> observableInvoices = FXCollections.observableArrayList(invoices);
        
        IDFX.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceID"));
        INameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("invoiceName"));
        CNameFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("clientName"));
        LatFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("latitude"));
        LongFX.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("longitude"));
        DDateFX.setCellValueFactory(new PropertyValueFactory<Invoice, String>("date"));

        invoiceViewTable.setItems(observableInvoices);
        
    }
    
   
    }
    
    
    
    
    

                