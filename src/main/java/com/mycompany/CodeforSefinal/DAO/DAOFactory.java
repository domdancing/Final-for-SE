/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;

/**
 *
 * @author dominick valentine
 */
public class DAOFactory {

    private static final InvoiceDAO invoiceDAO = new InvoiceDAOImpl();
    private static final ReferenceItemDAO itemDAO = new ReferenceItemDAOImpl();

    // Factory method for InvoiceDAO
    public static InvoiceDAO getInvoiceDAO() {
        return invoiceDAO;
    }

    // Factory method for ItemDAO
    public static ReferenceItemDAO getItemDAO() {
        return itemDAO;
    }
}