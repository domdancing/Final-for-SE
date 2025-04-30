/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dominick valentine
 */
public interface QuantityItemDAO {
    ArrayList<QuantityItem> getQuantityItemsFromInvoiceId(int invoiceId) throws SQLException;
    void saveQuantityItemWithInvoiceId(QuantityItem quantityItem, int invoiceId) throws SQLException;

    public ArrayList<QuantityItem> getAllQuantityItemsWithReference();
    
}
