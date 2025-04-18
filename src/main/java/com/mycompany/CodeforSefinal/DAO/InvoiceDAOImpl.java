/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;
import static com.mycompany.CodeforSefinal.ConnectToDatabase.getConnection;
import com.mycompany.CodeforSefinal.Invoice;
import com.mycompany.CodeforSefinal.QuantityItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author tangs
 */
public class InvoiceDAOImpl implements InvoiceDAO{

    @Override
    public void saveInvoice(Invoice invoice) {
        try (Connection conn = getConnection()) {
        String sql = "INSERT INTO invoices (invoice_name, delivery_latitude, delivery_longitude, customer_name, delivery_date) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        // Setting the values from the invoice object
        pstmt.setString(1, invoice.getInvoiceName()); // invoice_name
        pstmt.setDouble(2, invoice.getLatitude()); // delivery_latitude
        pstmt.setDouble(3, invoice.getLongitude()); // delivery_latitude
        pstmt.setString(4, invoice.getClientName()); // customer_name
        pstmt.setTimestamp(5, invoice.getDate()); // delivery_date
        
        pstmt.executeUpdate();
        
        //This didn't work, but I'm going to leave it here commented out for now
        /*
        //Get the generated id of the invoice we just inserted
        PreparedStatement lidStmnt = conn.prepareStatement("SELECT LAST_INSERT_ID()");
        ResultSet lidrs = lidStmnt.executeQuery();
        long insertId = lidrs.getLong(1);
        
        //Add items if any; we should also have a separate function to attach items to existing invoices
        ArrayList<Item> items = invoice.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            
            //Get Item Id; once we make item creation, we should save the item_id so we don't have to search this every time
            String itemQuery = "SELECT item_id FROM items WHERE name = " + item.getName();
            PreparedStatement itemStmnt = conn.prepareStatement(itemQuery);
            ResultSet itemrs = itemStmnt.executeQuery();
            long itemId = itemrs.getLong(1);
            
            //Match this invoice's id with this item's id.
            String itemInvoiceSql = "INSERT INTO invoice_items (invoice_id, item_id) " +
                     "VALUES (?, ?)";
            PreparedStatement itemInvoicePstmt = conn.prepareStatement(itemInvoiceSql);
            
            itemInvoicePstmt.setLong(1, insertId);
            itemInvoicePstmt.setLong(2, itemId);
            
            itemInvoicePstmt.executeUpdate();
        }
        */
        
        System.out.println("Saved invoice to DB.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    

    @Override
        public ArrayList<Invoice> getAllInvoices() {
        try {
            //Make query and place the results in resulSet
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT * FROM formatted_invoices";
            ResultSet resultSet = statement.executeQuery(queryString);
            
            HashMap<Integer, Invoice> invoiceMap = new HashMap<Integer, Invoice>();
            ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
            
            //Iterate through resultSet 
            while (resultSet.next()) { 
             
                //Get invoiceID
                int invoiceId = resultSet.getInt("id");
                
                //Create item from this result
                String itemName = resultSet.getString("item_name");
                double itemPrice = resultSet.getDouble("item_price");
                QuantityItem item = null;
                
                if (invoiceMap.containsKey(invoiceId)) {
                    //Just add item to the existing invoices
                    Invoice addItemInvoice = invoiceMap.get(invoiceId);
                    addItemInvoice.addItem(item);
                } else {
                    //Create new invoice, add the item to it, and add the new invoice to the Array and the Hashmap
                    
                    //Create invoice object
                    //TO DO
                     String invoiceName = "" + invoiceId;
                    
                    Invoice invoice = null; //new Invoice(invoice_number, )
                    invoice.addItem(item);
                    //END OF TO DO
                    
                    //Add to Array List and Hash Map
                    invoiceList.add(invoice);
                    invoiceMap.put(invoiceId, invoice);
                }
                
            }
            
            //Return list of all invoices
            return invoiceList;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Should never reach here
        return null;
    }
    

    @Override
    public void deleteInvoicebyID(int invoiceID)throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


   
    
    
    
}
