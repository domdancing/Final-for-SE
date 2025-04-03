/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenRouteServiceAPI {
    private static final String API_KEY = "YOUR_API_KEY"; // Replace with your OpenRouteService API Key

    public static void main(String[] args) {
        String origin = "40.712776,-74.005974"; // New York (latitude,longitude)
        String destination = "34.052235,-118.243683"; // Los Angeles

        String jsonResponse = getDistance(origin, destination);
        System.out.println(jsonResponse);
    }

    public static String getDistance(String origin, String destination) {
        String urlString = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" 
                + API_KEY + "&start=" + origin + "&end=" + destination;

        try {
            URL url = new URL(urlString);
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
