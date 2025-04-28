/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;

import com.mycompany.CodeforSefinal.DAO.QuantityItemDAO;
import com.mycompany.CodeforSefinal.DAO.QuantityItemDAOImpl;
import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author tangs
 */
public class InvoiceItemsController implements Initializable {

    @FXML
    private TableView<QuantityItem> itemsTable;
    @FXML
    private TableColumn<QuantityItem, Integer> itemIdColumn;
    @FXML
    private TableColumn<QuantityItem, String> itemNameColumn;
    @FXML
    private TableColumn<QuantityItem, Double> itemPriceColumn;
    @FXML
    private TableColumn<QuantityItem, Integer> itemQuantityColumn;

    private int invoiceId;

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
        loadItems();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void loadItems() {
        try {
            QuantityItemDAO dao = new QuantityItemDAOImpl();
            List<QuantityItem> quantityItems = dao.getQuantityItemsFromInvoiceId(invoiceId);

            itemsTable.getItems().setAll(quantityItems);
        } catch (SQLException e) {
            showError("Failed to load items: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}