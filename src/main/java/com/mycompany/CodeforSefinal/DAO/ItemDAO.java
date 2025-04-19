/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author dominick valentine
 */
import com.mycompany.CodeforSefinal.Item;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    void saveItem(Item item) throws SQLException;
    //void updateItem(Item item) throws SQLException;
    //void deleteItemById(Long itemId) throws SQLException;
    //Item getItemById(Long itemId) throws SQLException;
    List<Item> getAllItems() throws SQLException;
}