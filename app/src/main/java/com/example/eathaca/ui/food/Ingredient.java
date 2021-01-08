package com.example.eathaca.ui.food;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ingredient {
    private String ingredientName;
    private int amount;
    private Date dateAdded; // represented as millis from January 1, 1970
    private Date dateExpired; // represented as millis from January 1, 1970
    private int duration;
    private long storageLife;
    private double freshness;
    String pattern = "MM/dd/yyyy";

    public Ingredient(String name, int amount, int duration, double freshness) {
        // constructor for testing
        ingredientName = name;
        this.amount = amount;

        requestDuration(duration);

        Calendar today = Calendar.getInstance();
        dateAdded = today.getTime();
        today.add(Calendar.DATE, duration);
        dateExpired =today.getTime() ;
        //updateFreshness();
        this.freshness = freshness;

    }

    public Ingredient(String name, int amount, Date dateExpired) {
        ingredientName = name;
        this.amount = amount;
        this.dateExpired = dateExpired;
        Calendar today = Calendar.getInstance();

        long durationL=dateExpired.getTime()-today.getTime().getTime();
        duration =(int) ((durationL / (1000 * 60 * 60 * 24)));
        updateFreshness();

    }


    private void requestDuration(int duration) {
        // TODO: check for the duration the ingredient
        if (duration == 0) {
            this.duration = 5;
        } else {
            this.duration = duration;
        }
    }

    void updateFreshness() {
        Date today = new Date();
        long storageTime = today.getTime()- dateAdded.getTime();
        int storage_In_Days = (int) ((storageTime / (1000 * 60 * 60 * 24)));
        storageLife = duration - storageTime-storage_In_Days;
        if (storageTime == 0) {
            freshness = 1.0;
        } else {
            freshness = storage_In_Days / duration;
        }
    }

    String name() {
        return ingredientName;
    }

    String freshnessImage() {
        return "";
    }

    double freshness() {
        return freshness;
    }

    String amountString() {
        return Integer.toString(amount);
    }

    String storageLife() {
        int storageLife = (int) (freshness * duration);
        if (storageLife == 1) {
            return "1 day";
        } else {
            return Integer.toString(storageLife) + " days";
        }
    }

    static private String decodeDate(long millis) {
        Date date = new Date(millis);
        return date.getMonth()+"/"+date.getDate()+"/"+(date.getYear()+1900);
    }

    public String addedDateString() {
        DateFormat df = new SimpleDateFormat(pattern);
        return "Added on "+df.format(dateAdded);}

    public String expiredDateString() {DateFormat df = new SimpleDateFormat(pattern);
        return "Best used by "+df.format(dateExpired);}
}
