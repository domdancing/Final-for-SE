/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author dominick valentine
 */
import com.mycompany.CodeforSefinal.Objects.Item;
import java.sql.SQLException;
import java.util.List;

public interface ReferenceItemDAO {
    void saveItem(Item item) throws SQLException;
    
    List<Item> getAllItems() throws SQLException;
}
// make a DAO for each item type 
//
// make a DAO for QItem