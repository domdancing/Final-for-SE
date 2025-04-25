/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.Objects;

import com.mycompany.CodeforSefinal.backend.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Invoice {
    private int invoiceID;
    private String clientName;
    private String invoiceName;
    private Timestamp date;
    private ArrayList<QuantityItem> items;

    private String zipCode;
    private double latitude;
    private double longitude;
    private double shippingPrice;
    private double totalPrice;
    private double distance;

    private static final String START_LOCATION = "-72.7945,42.1315"; // Warehouse (WSU)
    private static final double COST_PER_KM = 0.25;

    // Constructor used when loading from DB — NO API CALL HERE
    public Invoice(String invoiceName, Timestamp date, String clientName, ArrayList<QuantityItem> items, String zipCode) {
        this.invoiceName = invoiceName;
        this.date = date;
        this.clientName = clientName;
        this.items = items;
        this.zipCode = zipCode;
    }

    // Optional: use this AFTER creating or editing ZIP
    public void fetchCoordinatesAndCalculateTotals() {
        double[] coordinates = ZipCodeService.getCoordinatesFromZip(zipCode);
        this.latitude = coordinates[0];
        this.longitude = coordinates[1];
        this.distance = calculateDistance(latitude, longitude);
        this.shippingPrice = calculateShippingPrice();
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateDistance(double lat, double lon) {
        String destination = lon + "," + lat;
        try {
            String response = OpenRouteServiceAPI.getDistance(START_LOCATION, destination);
            double rawDistance = RouteParser.extractDistanceInKm(response);
            return Math.round(rawDistance * 100.0) / 100.0;
        } catch (Exception e) {
            System.out.println("Error calculating distance: " + e.getMessage());
            return 0;
        }
    }

    private double calculateShippingPrice() {
        return distance * COST_PER_KM;
    }

    private double calculateTotalPrice() {
        double itemTotal = 0;
        for (QuantityItem item : items) {
            itemTotal += item.getPrice() * item.getQuantity();
        }
        return itemTotal + shippingPrice;
    }

   

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
        // Don't fetch coordinates automatically anymore
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ArrayList<QuantityItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<QuantityItem> items) {
        this.items = items;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getZipCode() {
        return zipCode;
    }
   
 
}



