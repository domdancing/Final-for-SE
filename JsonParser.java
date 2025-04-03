/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;
import org.json.JSONObject;
import org.json.JSONArray;

public class ORSParser {
    public static void parseResponse(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONObject summary = json.getJSONArray("routes").getJSONObject(0).getJSONObject("summary");
        
        double distanceKm = summary.getDouble("distance") / 1000; // Convert to km
        double durationMin = summary.getDouble("duration") / 60; // Convert to minutes

        System.out.println("Distance: " + distanceKm + " km");
        System.out.println("Duration: " + durationMin + " min");
    }

    public static void main(String[] args) {
        String jsonResponse = OpenRouteServiceAPI.getDistance("40.712776,-74.005974", "34.052235,-118.243683");
        parseResponse(jsonResponse);
    }
}
