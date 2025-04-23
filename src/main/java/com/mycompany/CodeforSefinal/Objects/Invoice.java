/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.Objects;


import com.mycompany.CodeforSefinal.backend.OpenRouteServiceAPI;
import com.mycompany.CodeforSefinal.backend.RouteParser;
import com.mycompany.CodeforSefinal.backend.ZipCodeService;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.mycompany.CodeforSefinal.backend.ZipCodeService;

public class Invoice {
    private int invoiceID;
    private String clientName;
    private String invoiceName;
    private Timestamp date;
    private ArrayList<QuantityItem> items;
    
    private String zipCode;             // <-- new field for ZIP
    private double latitude;
    private double longitude;

    private double shippingPrice;
    private double totalPrice;
    private double distance;

    private static final String START_LOCATION = "-72.7945,42.1315"; // Warehouse (WSU)
    private static final double COST_PER_KM = 0.75;

    // Updated constructor using ZIP
    public Invoice(String invoiceName, Timestamp date, String clientName, ArrayList<QuantityItem> items, String zipCode) {
        this.invoiceName = invoiceName;
        this.date = date;
        this.clientName = clientName;
        this.items = items;
        this.zipCode = zipCode;

        // Use ZipCodeService to get coordinates
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

        // Round to 2 decimal places
        double roundedDistance = Math.round(rawDistance * 100.0) / 100.0;
        return roundedDistance;
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

    // Getters and Setters
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    public ArrayList<QuantityItem> getItems() {
        return items;
    }

    public void addItem(QuantityItem item) {
        items.add(item);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDistance() {
        return distance;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;

        double[] coordinates = ZipCodeService.getCoordinatesFromZip(zipCode);
        this.latitude = coordinates[0];
        this.longitude = coordinates[1];

        this.distance = calculateDistance(latitude, longitude);
        this.shippingPrice = calculateShippingPrice();
        this.totalPrice = calculateTotalPrice();
    }
}











