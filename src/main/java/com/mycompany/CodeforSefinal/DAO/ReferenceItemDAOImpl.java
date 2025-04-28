/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author dominick valentine
 */
import com.mycompany.CodeforSefinal.backend.ConnectToDatabase;
import com.mycompany.CodeforSefinal.Objects.Item;
import com.mycompany.CodeforSefinal.Objects.ReferenceItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReferenceItemDAOImpl implements ReferenceItemDAO {

    @Override
    public void saveItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (item_id, item_name, item_price) VALUES (?, ?, ?)";
        try (Connection conn = ConnectToDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, item.getItemId());
            stmt.setString(2, item.getName());
            stmt.setDouble(3, item.getPrice());

            stmt.executeUpdate();
        }
    }

   

    @Override
    public List<Item> getAllItems() throws SQLException {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Connection conn = ConnectToDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("item_id");
                String name = rs.getString("item_name");
                double price = rs.getDouble("item_price");
               //Create a ReferenceItem instead of an Item
                ReferenceItem referenceItem = new ReferenceItem(id, name, price);
                itemList.add(referenceItem);
            }
        }

        return itemList;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public int getNextAvailableId() throws SQLException {
    String sql = "SELECT MAX(item_id) FROM items";
    try (Connection conn = ConnectToDatabase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1) + 1;
        } else {
            return 1;  // First item
        }
    }
}
}
