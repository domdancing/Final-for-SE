/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.CodeforSefinal.backend;
import org.json.JSONObject;
import org.json.JSONArray;

public class RouteParser {
    public static double extractDistanceInKm(String json) {
        if (json == null) {
            System.out.println("No response from API");
            return 0;
        }

        JSONObject obj = new JSONObject(json);
        JSONArray routes = obj.getJSONArray("features");
        JSONObject summary = routes.getJSONObject(0)
                                   .getJSONObject("properties")
                                   .getJSONObject("summary");

        
        double durationMin = summary.getDouble("duration") / 60;
        return summary.getDouble("distance") / 1000; // Convert meters to kilometers
       
    }
}


