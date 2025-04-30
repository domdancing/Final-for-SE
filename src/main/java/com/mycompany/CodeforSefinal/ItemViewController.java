package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.DAO.QuantityItemDAOImpl;
import com.mycompany.CodeforSefinal.DAO.ReferenceItemDAO;
import com.mycompany.CodeforSefinal.DAO.ReferenceItemDAOImpl;
import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import com.mycompany.CodeforSefinal.Objects.Invoice;
import com.mycompany.CodeforSefinal.Objects.Item;
import com.mycompany.CodeforSefinal.Objects.ReferenceItem;
import com.mycompany.CodeforSefinal.factor.DAOFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


    

public class ItemViewController implements Initializable{
    
    // Implementing all JavaFX ids so that they can be utilized in the code 
    @FXML private Button returnButton;
     @FXML private TableView<ReferenceItem> itemViewTable;
    @FXML private TableColumn<ReferenceItem, Integer> IDFX;
    @FXML private TableColumn<ReferenceItem, String> NameFX;
    @FXML private TableColumn<ReferenceItem, Double> ItemPriceFX;
    //@FXML private TableColumn<QuantityItem, Integer> ItemQuantityFX;
    //<TableColumn fx:id="ItemQuantityFX" prefWidth="156.0" style="-fx-font-family: 'Eras Bold ITC'; -fx-font-size: 15px;" text="Item Quantity" />
    
    // Test ArrayList - does nothing for now 
    private ArrayList<Invoice> invoiceItems = new ArrayList<>();
    
    // Needed imports for JavaFX
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ReferenceItemDAO referenceItemDAO;
    
    // FXML Connected method, uses ActionEvent to detect when the button "return" is pressed, when it is pressed it will call the method. 
    @FXML
    private void handleReturnToInvoiceView(ActionEvent event) throws IOException {
        
        // Code that will load up the "InvoiceViewController.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InvoiceViewPage.fxml"));
        root = loader.load();
        InvoiceViewController controller = loader.getController();
        
        // Code that loads up the "primary.fxml"
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
    // FXML file that runs when the "secondary.fxml" loads
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   referenceItemDAO = new ReferenceItemDAOImpl(); // ✅ Connect to DAO
        
        // Set up table columns
        IDFX.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        NameFX.setCellValueFactory(new PropertyValueFactory<>("name"));
        ItemPriceFX.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load items into table
        updateItemsTable();
    }

    @FXML
    private void updateItemsTable() {
        try {
            List<Item> items = referenceItemDAO.getAllItems();
            List<ReferenceItem> referenceItems = new ArrayList<>();

            for (Item item : items) {
                if (item instanceof ReferenceItem) {
                    referenceItems.add((ReferenceItem) item);
                }
            }

            ObservableList<ReferenceItem> observableItems = FXCollections.observableArrayList(referenceItems);
            itemViewTable.setItems(observableItems);

        } catch (SQLException e) {
            showError("Failed to load items: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        
    
    
   
    }
    
    
    
    
    

                