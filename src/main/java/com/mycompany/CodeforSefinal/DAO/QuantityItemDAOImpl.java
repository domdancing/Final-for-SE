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
        try (Connection conn = ConnectToDatabase.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(INSERT_ITEM_SQL);
            System.out.println("Invoice ID: "+invoiceId);
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, quantityItem.getItemId());
            stmt.setDouble(3, quantityItem.getQuantity());
            stmt.executeUpdate();
        }
    }

    
    public ArrayList<QuantityItem> getAllQuantityItems() {
    ArrayList<QuantityItem> quantityItems = new ArrayList<>();
    
    try {
        Connection conn = ConnectToDatabase.getConnection();
        String sql = "SELECT item_id, item_name, item_price, item_quantity FROM invoice_items"; // Adjust table/column names if needed
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int itemId = rs.getInt("item_id");
            String itemName = rs.getString("item_name");
            double itemPrice = rs.getDouble("item_price");
            int itemQuantity = rs.getInt("item_quantity");
            
            QuantityItem qItem = new QuantityItem(itemId, itemName, itemPrice, itemQuantity);
            quantityItems.add(qItem);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return quantityItems;
}
    
   // tied to make a join table  
    // its not working but here is what I made 
    
    /*
   public ArrayList<QuantityItem> getAllQuantityItemsWithReference() {
    ArrayList<QuantityItem> quantityItems = new ArrayList<>();
    
    try {
        Connection conn = ConnectToDatabase.getConnection();
        String sql = """
            SELECT q.item_id, r.name, r.price, q.quantity
            FROM quantity_items q
            JOIN reference_items r ON q.item_id = r.item_id
        """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int itemId = rs.getInt("item_id");
            String itemName = rs.getString("name");
            double itemPrice = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            
            QuantityItem qItem = new QuantityItem(itemId, itemName, itemPrice, quantity);
            quantityItems.add(qItem);
        }
        
        rs.close();
        stmt.close();
        conn.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return quantityItems;
}
 
    */

    @Override
    public ArrayList<QuantityItem> getAllQuantityItemsWithReference() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
