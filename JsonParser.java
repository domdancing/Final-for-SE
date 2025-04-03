/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonParser {
    public static void parseResponse(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray rows = json.getJSONArray("rows");
        JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);
        
        String distance = elements.getJSONObject("distance").getString("text");
        String duration = elements.getJSONObject("duration").getString("text");

        System.out.println("Distance: " + distance);
        System.out.println("Duration: " + duration);
    }

    public static void main(String[] args) {
        String jsonResponse = GoogleDistanceAPI.getDistance("New York, NY", "Los Angeles, CA");
        parseResponse(jsonResponse);
    }
}
