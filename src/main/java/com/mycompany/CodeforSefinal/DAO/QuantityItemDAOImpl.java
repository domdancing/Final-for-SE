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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityItemDAOImpl implements QuantityItemDAO {

    private static final String INSERT_ITEM_SQL = "INSERT INTO quantity_items (item_id, name, price, quantity) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ITEM_SQL = "UPDATE quantity_items SET name = ?, price = ?, quantity = ? WHERE item_id = ?";
    private static final String DELETE_ITEM_SQL = "DELETE FROM quantity_items WHERE item_id = ?";
   

    @Override
    public void saveQuantityItem(QuantityItem quantityItem) throws SQLException {
        try (Connection conn = ConnectToDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_ITEM_SQL)) {
            stmt.setLong(1, quantityItem.getItemId());
            stmt.setString(2, quantityItem.getName());
            stmt.setDouble(3, quantityItem.getPrice());
            stmt.setInt(4, quantityItem.getQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateQuantityItem(QuantityItem quantityItem) throws SQLException {
        try (Connection conn = ConnectToDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_ITEM_SQL)) {
            stmt.setString(1, quantityItem.getName());
            stmt.setDouble(2, quantityItem.getPrice());
            stmt.setInt(3, quantityItem.getQuantity());
            stmt.setLong(4, quantityItem.getItemId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteQuantityItem(long itemId) throws SQLException {
        try (Connection conn = ConnectToDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_ITEM_SQL)) {
            stmt.setLong(1, itemId);
            stmt.executeUpdate();
        }
    }


  
      
}
