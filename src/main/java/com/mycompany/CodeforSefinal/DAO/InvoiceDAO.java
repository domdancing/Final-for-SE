/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author tangs
 */

import com.mycompany.CodeforSefinal.Invoice;


import java.util.List;

public interface InvoiceDAO {
    void saveInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    void deleteInvoicebyID(int invoiceID)throws Exception ;
}