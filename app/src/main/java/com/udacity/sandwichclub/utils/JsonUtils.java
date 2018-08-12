package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_SANDWICH_OBJECT_NAME = "name";
    public static final String JSON_SANDWICH_OBJECT_MAINNAME = "mainName";
    public static final String JSON_SANDWICH_OBJECT_OTHERNAMES = "alsoKnownAs";
    public static final String JSON_SANDWICH_OBJECT_PLACEOFORIGIN = "placeOfOrigin";
    public static final String JSON_SANDWICH_OBJECT_DESCRIPTION = "description";
    public static final String JSON_SANDWICH_OBJECT_IMAGE = "image";
    public static final String JSON_SANDWICH_OBJECT_INGREDENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try{
            //Get root Sandwich JSON Object
            JSONObject sandwichJSON = new JSONObject(json);

            //Get sandwich Name JSON Object
            JSONObject sandwichName = sandwichJSON.getJSONObject(JSON_SANDWICH_OBJECT_NAME);
            //Get sandwich mainName
            String mainName = sandwichName.optString(JSON_SANDWICH_OBJECT_MAINNAME);
            //Get sandwich other names
            List<String>  otherNames = jsonArrayList(sandwichName.getJSONArray(JSON_SANDWICH_OBJECT_OTHERNAMES));

            //Get sandwich place of origin
            String placeOfOrigin = sandwichJSON.optString(JSON_SANDWICH_OBJECT_PLACEOFORIGIN);

            //Get sandwich description
            String description = sandwichJSON.optString(JSON_SANDWICH_OBJECT_DESCRIPTION);

            //Get sandwich image
            String image = sandwichJSON.optString(JSON_SANDWICH_OBJECT_IMAGE);

            //Get sandwich ingredients
            List<String> ingredients = jsonArrayList(sandwichJSON.getJSONArray(JSON_SANDWICH_OBJECT_INGREDENTS));

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
