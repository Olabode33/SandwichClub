package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            //Get root Sandwich JSON Object
            JSONObject sandwichJSON = new JSONObject(json);

            //Get sandwich Name JSON Object
            JSONObject sandwichName = sandwichJSON.getJSONObject("name");
            //Get sandwich mainName
            String mainName = sandwichName.optString("mainName");
            //Get sandwich other names
            List<String>  otherNames = jsonArrayList(sandwichName.getJSONArray("alsoKnownAs"));

            //Get sandwich place of origin
            String placeOfOrigin = sandwichJSON.optString("placeOfOrigin");

            //Get sandwich description
            String description = sandwichJSON.optString("description");

            //Get sandwich image
            String image = sandwichJSON.optString("image");

            //Get sandwich ingredients
            List<String> ingredients = jsonArrayList(sandwichJSON.getJSONArray("ingredients"));

            //Create sandwich object
            Sandwich sandwich = new Sandwich(mainName, otherNames, placeOfOrigin, description, image, ingredients);

            return sandwich;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> jsonArrayList(JSONArray jsonArray){
        List<String> stringList = new ArrayList<String>();
        
        if(jsonArray != null){
            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    stringList.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return stringList;
    }
}
