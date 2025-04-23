/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.backend;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenRouteServiceAPI {
    private static final String API_KEY = "5b3ce3597851110001cf62488506a8b1b34e43aa8d360caaf7a4b122"; // Replace with your actual key

    public static String getDistance(String startLonLat, String endLonLat) {
        String apiUrl = "https://api.openrouteservice.org/v2/directions/driving-car";
        String url = apiUrl + "?api_key=" + API_KEY + "&start=" + startLonLat + "&end=" + endLonLat;

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
