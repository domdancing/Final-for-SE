
package com.mycompany.CodeforSefinal;
import com.mycompany.CodeforSefinal.Objects.ReferenceItem;
import com.mycompany.CodeforSefinal.factor.DAOFactory;

import com.mycompany.CodeforSefinal.DAO.InvoiceDAO;
import com.mycompany.CodeforSefinal.DAO.InvoiceDAOImpl;
import com.mycompany.CodeforSefinal.DAO.ReferenceItemDAO;
import com.mycompany.CodeforSefinal.DAO.ReferenceItemDAOImpl;
import com.mycompany.CodeforSefinal.Objects.Item;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    @FXML private TableColumn<ReferenceItem, Integer> IDFX;
    @FXML private TableColumn<ReferenceItem, String> NameFX;
    @FXML private TableColumn<ReferenceItem, Double> PriceFX;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private ReferenceItemDAO referenceItemDAO;
    private ArrayList<ReferenceItem> listofItems;

    // CONSTRUCTOR
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         referenceItemDAO = new ReferenceItemDAOImpl(); // ✅ connect to the DB access object
        
        // Set up the table columns
        IDFX.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        NameFX.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceFX.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load all existing items from the database
        updateItemsTable();
    }
    
    // Handles returning back to the main page.
    
    @FXML
    private void handleReturnToPrimaryFromICP(ActionEvent event) throws IOException 
    {
       

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
       try {
        List<Item> items = referenceItemDAO.getAllItems();
        List<ReferenceItem> referenceItems = new ArrayList<>();

        for (Item item : items) {
            if (item instanceof ReferenceItem) {
                referenceItems.add((ReferenceItem) item);
            }
        }
        
        ObservableList<ReferenceItem> observableItems = FXCollections.observableArrayList(referenceItems);
        createItemTable.setItems(observableItems);
    } catch (SQLException e) {
        showError("Failed to load items: " + e.getMessage());
    }
    }
            
    
    // Handles the creation of the Item instance.
    
    @FXML
    private void handleCreatingItem(ActionEvent event) throws IOException
    {
       String itemName = createItemNameField.getText().trim();
        String strItemPrice = createItemPriceField.getText().trim();

        if (itemName.isEmpty() || strItemPrice.isEmpty()) {
            showError("Please fill out all fields.");
            return;
        }
        
        double itemPrice;
        try {
            itemPrice = Double.parseDouble(strItemPrice);
        } catch (NumberFormatException e) {
            showError("Invalid price entered.");
            return;
        }

        try {
             ReferenceItemDAO referenceItemDAO = new ReferenceItemDAOImpl();

        // Generate a new ID (you could improve this later)
        int newItemId = referenceItemDAO.getNextAvailableId();  // You'll add this method!

        ReferenceItem newItem = new ReferenceItem(newItemId, itemName, itemPrice);

        referenceItemDAO.saveItem(newItem);  // 🛠️ Save to the database
        showInfo("Addition Complete", "Successfully added: " + itemName);

        updateItemsTable();  // ✅ Reload table from the database
    } catch (Exception e) {
        showError("Error creating item: " + e.getMessage());
    }
        
    }
   
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
