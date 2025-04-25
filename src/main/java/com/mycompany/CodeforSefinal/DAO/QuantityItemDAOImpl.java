/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author dominick valentine
 */
import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import com.mycompany.CodeforSefinal.backend.ConnectToDatabase;
import com.mycompany.CodeforSefinal.factor.ItemFactory;
import com.mycompany.CodeforSefinal.factor.QuantityItemFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityItemDAOImpl implements QuantityItemDAO {

    private static final String SELECT_BY_INVOICE_SQL = "SELECT * FROM invoice_items ivi JOIN items i ON ivi.item_id = i.item_id WHERE ivi.invoice_id = ?";
    private static final String INSERT_ITEM_SQL = "INSERT INTO invoice_items (invoice_id, item_id, quantity) VALUES (?, ?, ?)";
    
    public ArrayList<QuantityItem> getQuantityItemsFromInvoiceId(int invoiceId) throws SQLException {
        ArrayList<QuantityItem> items = new ArrayList<QuantityItem>();
        
        try (Connection conn = ConnectToDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_INVOICE_SQL);
            stmt.setInt(1, invoiceId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int itemId = resultSet.getInt("i.item_id");
                double itemPrice = resultSet.getDouble("i.item_price");
                String itemName = resultSet.getString("i.item_name");
                int itemQuantity = resultSet.getInt("ivi.quantity");
                QuantityItemFactory itemFactory = new QuantityItemFactory(itemQuantity);
                QuantityItem item = (QuantityItem) itemFactory.createItem(itemId, itemName, itemPrice);
                items.add(item);
            }
            return items;
        }

    }

    @Override
    public void saveQuantityItemWithInvoiceId(QuantityItem quantityItem, int invoiceId) throws SQLException {
        try (Connection conn = ConnectToDatabase.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_ITEM_SQL)) {
            stmt.setInt(1, quantityItem.getItemId());
            stmt.setString(2, quantityItem.getName());
            stmt.setDouble(3, quantityItem.getPrice());
            stmt.executeUpdate();
        }
    }

}
