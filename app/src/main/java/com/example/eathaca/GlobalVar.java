package com.example.eathaca;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

public class GlobalVar {
    public static void saveLoginInfo(Context context, String username, String password, int accountType) {

        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPre.edit();

        editor.putString("username", username);
        editor.putString("password", password);
        editor.putInt("accountType",accountType);

        editor.apply();
    }


    public static String getUsername(Context context) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sharedPre.getString("username", "");
    }
    public static String getPassword(Context context) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sharedPre.getString("password", "");
    }
    public static int getAccountType(Context context) {
        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return sharedPre.getInt("accountType",0);
    }
    //TODO: For recipe? renew everytime a new food is added
    public static void saveGroceries(Context context, Set q) {

        SharedPreferences sharedPre = context.getSharedPreferences("config", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPre.edit();

        editor.putStringSet("groceries", q);

        editor.apply();
    }




}

