package com.example.eathaca.ui.food;

import android.app.Dialog;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.eathaca.R;

public class FoodAdderHolder{

    EditText title;
    EditText amount;
    EditText expiredDate;


    public FoodAdderHolder(@NonNull Dialog dialog) {
        title = dialog.findViewById(R.id.input_ingredient_name);
        amount = dialog.findViewById(R.id.input_amount);
        expiredDate = dialog.findViewById(R.id.input_expired_date);
    }
}
