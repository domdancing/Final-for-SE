/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author tangs
 */

import com.mycompany.CodeforSefinal.Objects.Invoice;
import java.sql.SQLException;
import java.time.LocalDate;


import java.util.List;

public interface InvoiceDAO {
    void saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    void deleteInvoicebyID(int invoiceID)throws Exception ;
    void updateInvoice(Invoice invoice) throws SQLException;
   List<Invoice> searchInvoices(String zipCode, String clientName, String invoiceName, LocalDate date) throws SQLException;

    public boolean invoiceNameExists(String invoiceName);

}