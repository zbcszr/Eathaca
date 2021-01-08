package com.example.eathaca.ui.recipes;

import java.util.ArrayList;

public class RecipeItem {
    private String name;
    private float rate;
    private int level;
    private ArrayList<RecipeIngredient> ingredient;
    private int recipeDrawble;
    private String urlString;

    public RecipeItem(String name, float rate, int level, ArrayList<RecipeIngredient> ingredient,int recipeDrawble, String url){
        this.name = name;
        this.rate = rate;
        this.level = level;
        this.ingredient=ingredient;
        this.recipeDrawble=recipeDrawble;
        this.urlString = url;

    }

    public String name(){
        return name;
    }

    public String levelString() {
        return "Level "+level;
    }

    public float rate() {
        return rate;
    }

    public ArrayList<RecipeIngredient> getIngredient() {
        return ingredient;
    }

    public int getRecipeDrawble() {
        return recipeDrawble;
    }

    public String getUrlString() {
        return urlString;
    }

    public static class RecipeIngredient {
        String name;
        int amount;
        int drawable;
        boolean owned;

        public RecipeIngredient(String name, int amount,int drawble,boolean b) {
            this.name = name;
            this.amount = amount;
            this.drawable=drawble;
            owned=b;

        }
    }
}
