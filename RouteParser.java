/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal;
import org.json.JSONObject;
import org.json.JSONArray;

public class RouteParser {
    public static void parseDistanceData(String json) {
        if (json == null) {
            System.out.println("No response from API");
            return;
        }

        JSONObject obj = new JSONObject(json);
        JSONArray routes = obj.getJSONArray("features");
        JSONObject summary = routes.getJSONObject(0)
                                   .getJSONObject("properties")
                                   .getJSONObject("summary");

        double distanceKm = summary.getDouble("distance") / 1000;
        double durationMin = summary.getDouble("duration") / 60;

        System.out.println("Distance: " + distanceKm + " km");
        System.out.println("Duration: " + durationMin + " minutes");
    }
}
