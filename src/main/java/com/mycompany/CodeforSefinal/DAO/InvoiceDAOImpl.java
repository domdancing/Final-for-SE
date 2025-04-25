/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.DAO;
import com.mycompany.CodeforSefinal.backend.ConnectToDatabase;
import static com.mycompany.CodeforSefinal.backend.ConnectToDatabase.getConnection;
import com.mycompany.CodeforSefinal.Objects.Invoice;
import com.mycompany.CodeforSefinal.Objects.QuantityItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
            String sql = "INSERT INTO invoices (invoice_name, delivery_zipcode, customer_name, delivery_date) " +
                         "VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // Setting the values from the invoice object
            pstmt.setString(1, invoice.getInvoiceName()); // invoice_name
            pstmt.setString(2, invoice.getZipCode());     // delivery_zipcode
            pstmt.setString(3, invoice.getClientName());  // customer_name
            pstmt.setTimestamp(4, invoice.getDate());     // delivery_date

            pstmt.executeUpdate();

            int invoiceId = ConnectToDatabase.getLastInsertId();
            invoice.setInvoiceID(invoiceId);

            for (int i = 0; i < invoice.getItems().size(); i++) {
                QuantityItem item = invoice.getItems().get(i);
                QuantityItemDAOImpl qidao = new QuantityItemDAOImpl();
                qidao.saveQuantityItemWithInvoiceId(item, invoiceId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<Invoice> getAllInvoices() {
        
        ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();
        
        try {
            //Make query and place the results in resulSet
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT * FROM invoices";
            ResultSet resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) { 
                int invoiceId = resultSet.getInt("invoice_id");
                String invoiceName = resultSet.getString("invoice_name");
                String zipcode = resultSet.getString("delivery_zipcode");
                String customerName = resultSet.getString("customer_name");
                Timestamp deliveryDate = resultSet.getTimestamp("delivery_date");
                
                QuantityItemDAOImpl qidao = new QuantityItemDAOImpl();
                ArrayList<QuantityItem> itemList = qidao.getQuantityItemsFromInvoiceId(invoiceId);
                        
                //public Invoice(String invoiceName, Timestamp date, String clientName, ArrayList<QuantityItem> items, String zipCode) {
                Invoice invoice = new Invoice(invoiceName, deliveryDate, customerName, itemList, zipcode);  
                invoice.setInvoiceID(invoiceId);
                
                invoiceList.add(invoice);
            } 
            
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

    @Override
    // a sample not working yet
    // this should call getallincoives to update them
    public void updateInvoice(Invoice invoice) throws SQLException {
         String sql = "UPDATE invoices SET invoice_name = ?, client_name = ?, date = ?, latitude = ?, longitude = ?, shipping_price = ?, total_price = ?, distance = ? WHERE invoice_id = ?";

    try (Connection conn = ConnectToDatabase.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, invoice.getInvoiceName());
        stmt.setString(2, invoice.getClientName());
        stmt.setTimestamp(3, invoice.getDate());
        stmt.setString(4, invoice.getZipCode());      // delivery_zipcode
        stmt.setDouble(5, invoice.getShippingPrice());
        stmt.setDouble(6, invoice.getTotalPrice());
        stmt.setDouble(7, invoice.getDistance());
        stmt.setInt(8, invoice.getInvoiceID());

        stmt.executeUpdate();
    }
    }

   
public ArrayList<Invoice> searchInvoices(String invoiceName, String clientName, String zipCode, LocalDate date) {
    ArrayList<Invoice> invoiceList = new ArrayList<>();
    
    try {
        Connection connection = getConnection();
        
        // Build dynamic query
        StringBuilder query = new StringBuilder("SELECT * FROM invoices WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (invoiceName != null && !invoiceName.isEmpty()) {
            query.append(" AND invoice_name LIKE ?");
            params.add("%" + invoiceName + "%");
        }
        if (clientName != null && !clientName.isEmpty()) {
            query.append(" AND customer_name LIKE ?");
            params.add("%" + clientName + "%");
        }
        if (zipCode != null && !zipCode.isEmpty()) {
            query.append(" AND delivery_zipcode = ?");
            params.add(zipCode);
        }
        if (date != null) {
            query.append(" AND DATE(delivery_date) = ?");
            params.add(Date.valueOf(date));  // convert LocalDate to SQL Date
        }

        PreparedStatement stmt = connection.prepareStatement(query.toString());

        // Fill in parameters
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            int invoiceId = resultSet.getInt("invoice_id");
            String resultInvoiceName = resultSet.getString("invoice_name");
            String resultZip = resultSet.getString("delivery_zipcode");
            String resultClientName = resultSet.getString("customer_name");
            Timestamp deliveryDate = resultSet.getTimestamp("delivery_date");

            QuantityItemDAOImpl qidao = new QuantityItemDAOImpl();
            ArrayList<QuantityItem> itemList = qidao.getQuantityItemsFromInvoiceId(invoiceId);

            Invoice invoice = new Invoice(resultInvoiceName, deliveryDate, resultClientName, itemList, resultZip);
            invoice.setInvoiceID(invoiceId);

            invoiceList.add(invoice);
        }

        return invoiceList;

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return new ArrayList<>();
}

    
    
}
