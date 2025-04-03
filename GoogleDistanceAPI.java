/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleDistanceAPI {
    private static final String API_KEY = "YOUR_GOOGLE_API_KEY"; // Replace with your actual key

    public static void main(String[] args) {
        String origin = "New York,NY";
        String destination = "Los Angeles,CA";
        String jsonResponse = getDistance(origin, destination);
        System.out.println(jsonResponse);
    }

    public static String getDistance(String origin, String destination) {
        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" 
                + origin + "&destinations=" + destination + "&key=" + API_KEY;
        try {
            URL url = new URL(urlString.replace(" ", "%20")); // Encode spaces in URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return response.toString(); // JSON response
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
