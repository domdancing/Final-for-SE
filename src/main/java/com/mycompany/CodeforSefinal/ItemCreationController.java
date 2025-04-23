
package com.mycompany.CodeforSefinal;
import com.mycompany.CodeforSefinal.Objects.ReferenceItem;
import com.mycompany.CodeforSefinal.DAO.DAOFactory;

import com.mycompany.CodeforSefinal.DAO.InvoiceDAO;
import com.mycompany.CodeforSefinal.DAO.InvoiceDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.fxml.Initializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ItemCreationController implements Initializable
{
    @FXML private TextField createItemNameField;
    @FXML private TextField createItemPriceField;
    
    @FXML private Button createItemButton;
    @FXML private Button returnFromCIButton;
    
    @FXML private TableView<ReferenceItem> createItemTable;
    @FXML private TableColumn<ReferenceItem, Long> IDFX;
    @FXML private TableColumn<ReferenceItem, String> NameFX;
    @FXML private TableColumn<ReferenceItem, Double> PriceFX;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<ReferenceItem> listofItems;

    // CONSTRUCTOR
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ReferenceItem item1 = new ReferenceItem(1, "Washing Machine", 200.0);
        ReferenceItem item2 = new ReferenceItem(2, "Toaster Oven", 100.0);
        ReferenceItem item3 = new ReferenceItem(3, "Microwave", 150.0);
        ArrayList<ReferenceItem> testArray = new ArrayList<ReferenceItem>();
        testArray.add(item1);
        testArray.add(item2);
        testArray.add(item3);
        listofItems = testArray;
        updateItemsTable();
    }
    
    // Handles returning back to the main page.
    
    @FXML
    private void handleReturnToPrimaryFromICP(ActionEvent event) throws IOException 
    {
        System.out.println("Button Clicked");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        root = loader.load();
        PrimaryController primaryController = loader.getController();
        
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
       
    // Updates the table.
    
    @FXML
    private void updateItemsTable()
    {
        ObservableList<ReferenceItem> observableItems = FXCollections.observableArrayList(listofItems);
        
        IDFX.setCellValueFactory(new PropertyValueFactory<ReferenceItem, Long>("itemId"));
        NameFX.setCellValueFactory(new PropertyValueFactory<ReferenceItem, String>("name"));
        PriceFX.setCellValueFactory(new PropertyValueFactory<ReferenceItem, Double>("price"));
        
        createItemTable.setItems(observableItems);
    }
            
    
    // Handles the creation of the Item instance.
    
    @FXML
    private void handleCreatingItem(ActionEvent event) throws IOException
    {
        String itemName = createItemNameField.getText();
        String strItemPrice = createItemPriceField.getText();
        Double itemPrice = Double.parseDouble(strItemPrice);
        
        long itemID = (1 + listofItems.size());
        
       // This implementation is not meant to be a permanent measure. This is only until a proper method
        // of locating the next ItemID from the database can be ascertained and sent to this class.

        if (itemName.isEmpty() || strItemPrice.isEmpty())
        {
            showError("Please fill out all fields.");
            return;
        }
        
        ReferenceItem newItem = new ReferenceItem(itemID, itemName, itemPrice);
        listofItems.add(newItem);
        showInfo("Addition Complete", "Successfully added: " + itemName);
        updateItemsTable();
    }
    
    // Took these from InvoicePageController to assist in debugging.
    
    @FXML
    private void showError(String message) 
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void showInfo(String title, String message) 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
