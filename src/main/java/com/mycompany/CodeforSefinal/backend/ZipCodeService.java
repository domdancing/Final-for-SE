/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.backend;

/**
 *
 * @author tangs
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ZipCodeService {

    public static double[] getCoordinatesFromZip(String zipCode) {
        String apiUrl = "http://api.zippopotam.us/us/" + zipCode;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("Failed to get location from ZIP: " + status);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

            reader.close();

            JSONObject obj = new JSONObject(jsonResponse.toString());
            JSONArray places = obj.getJSONArray("places");
            JSONObject place = places.getJSONObject(0);

            double latitude = Double.parseDouble(place.getString("latitude"));
            double longitude = Double.parseDouble(place.getString("longitude"));

            return new double[] { latitude, longitude };

        } catch (Exception e) {
            System.out.println("Error retrieving coordinates for ZIP code: " + e.getMessage());
            e.printStackTrace();
            return new double[] { 0.0, 0.0 }; // Default/fallback values
        }
    }
}
