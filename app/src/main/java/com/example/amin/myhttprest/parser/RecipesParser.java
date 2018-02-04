package com.example.amin.myhttprest.parser;

import com.example.amin.myhttprest.model.Recipes;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class RecipesParser {

    public static List<Recipes> parseFeed(String content){

        try {

        JSONArray ar = new JSONArray(content);
        List<Recipes> recipeslist = new ArrayList<>();
        for (int i =0 ; i < ar.length(); i++){

            JSONObject object = ar.getJSONObject(i);
            Recipes recipes = new Recipes();

            recipes.setReciepe_Id(object.getInt("id"));
            recipes.setName(object.getString("name"));
            recipes.setDescription(object.getString("description"));
            recipes.setPrice(object.getInt("price"));
            recipes.setThumbnail(object.getString("thumbnail"));
            recipes.setTimestamp(object.getString("timestamp"));
            recipes.setChef(object.getString("chef"));

            recipeslist.add(recipes);

        }
        return recipeslist; }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }
}
