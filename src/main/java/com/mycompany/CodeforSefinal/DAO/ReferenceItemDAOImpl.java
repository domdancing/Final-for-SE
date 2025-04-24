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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReferenceItemDAOImpl implements ReferenceItemDAO {

    @Override
    public void saveItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (item_id, name, price) VALUES (?, ?, ?)";
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
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                itemList.add(new Item(id, name, price) {});
            }
        }

        return itemList;
    }
}
