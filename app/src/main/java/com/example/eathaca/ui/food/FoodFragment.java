
package com.example.eathaca.ui.food;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eathaca.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;
//import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class FoodFragment extends Fragment {

    private ArrayList<Ingredient> ingredientList;
    private static FoodViewAdapter foodViewAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_food, container, false);

        loadIngredients();
        initRecylerView(root);


        foodViewAdapter = new FoodViewAdapter(getActivity(), ingredientList);

        return root;
    }

    private void initRecylerView(View view) {
        RecyclerView foodRecylerView = view.findViewById(R.id.food_list);
        foodViewAdapter = new FoodViewAdapter(getActivity(), ingredientList);
        foodRecylerView.setAdapter(foodViewAdapter);
        foodRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadIngredients() {
//        //Load ingredient data from firebase
//        FirebaseAuth fAuth;
//        FirebaseFirestore db;
////        FirebaseDatabase database;
//        String userEmail;
//
//        fAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
////        database =FirebaseDatabase.getInstance();
//
//        userEmail = fAuth.getCurrentUser().getEmail();
////        DatabaseReference mRef = database.getReference("users");
////        Query userInfo = db.collection("users")
////                .whereEqualTo("email",userEmail.toString());
////        db.document("user1").collection("Ingredients")
////                .get().addOnSuccessListener(new OnCompleteListener<QuerySnapshot>()) {
////                                                @Override
////                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                                                    if (task.isSuccessful()) {
////                                                        for (DocumentSnapshot document : task.getResult()) {
////                                                            Ingredient ingredient = document.toObject(Ingredient.class);
////                                                            ingredientList.add(ingredient);
////                                                        }
////                                                    }
////                                                }
////                                            };
////        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////            @Override
////            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                if (task.isSuccessful()) {
////                    for (DocumentSnapshot document : task.getResult()) {
//////                        Ingredient ingredient = document.toObject(Ingredient.class);
//////                        ingredientList.add(ingredient);}
////                    }
////                }
//        Query Ingredients = db.collection("users")
//                .whereEqualTo("email",userEmail.toString());
//
//        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//           @Override
//           public void onComplete(@NonNull Task<QuerySnapshot> task) {
//               if (task.isSuccessful()) {
//                   for (DocumentSnapshot document : task.getResult()) {
//                       Ingredient ingredient = document.toObject(Ingredient.class);
//                       ingredientList.add(ingredient);
//                   }
//               }
//           }
//       });


        // test code below
        ingredientList = new ArrayList<>();

        ingredientList = new ArrayList<>();
        Ingredient garlic = new Ingredient("Garlic", 3, 5,0.5);
        Ingredient potato = new Ingredient("Potato", 3, 10,0.4);
        Ingredient tomato = new Ingredient("Tomato", 5, 7,0.8);
        Ingredient pepper = new Ingredient("Pepper", 2, 12,0.6);
        Ingredient beef = new Ingredient("Beef", 2, 5, 0.9);

        ingredientList.add(tomato);
        ingredientList.add(garlic);
        ingredientList.add(potato);
        ingredientList.add(pepper);
        ingredientList.add(beef);
    }


    public void addIngredient(FoodAdderHolder adder) {
        Ingredient ingredient;

        String name = adder.title.getText().toString();
        int amount = Integer.parseInt(adder.amount.getText().toString());
        String dateString = adder.expiredDate.getText().toString();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date expiredDate = formatter.parse(dateString);
            ingredient = new Ingredient(name, amount, expiredDate);
        } catch (Exception e) {
            ingredient = new Ingredient(name, amount, 7, 1);
        }

        foodViewAdapter.addIngredient(ingredient);
    }
}