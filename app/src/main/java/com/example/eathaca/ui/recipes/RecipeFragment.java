package com.example.eathaca.ui.recipes;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eathaca.MainActivity;
import com.example.eathaca.R;
import com.example.eathaca.ui.food.FoodAdderHolder;
import com.example.eathaca.ui.food.FoodFragment;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private ArrayList<RecipeItem> recipeList;
    private RecipeViewAdapter recipeViewAdapter;
    private RecyclerView  recipeRecylerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recipes, container, false);
        //Button a=root.findViewById(R.id.btn_search);
        final Dialog dialog = new Dialog(getContext(), R.style.FullHeightDialog);

        loadRecipes();
        initRecylerView(root);
        LinearLayout searchButton=root.findViewById(R.id.search_layout);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.custom_dialog_search);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Button search= (Button) dialog.findViewById(R.id.search);
                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recipeRecylerView.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        recipeViewAdapter = new RecipeViewAdapter(getActivity(), recipeList);

        return root;
    }


    private void initRecylerView(View view) {
       recipeRecylerView = view.findViewById(R.id.recipe_list);
        recipeViewAdapter = new RecipeViewAdapter(getActivity(), recipeList);
        recipeRecylerView.setAdapter(recipeViewAdapter);
        recipeRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeRecylerView.setVisibility(View.GONE);
    }

    private void loadRecipes() {
        recipeList = new ArrayList<>();

        ArrayList<RecipeItem.RecipeIngredient> in1=new ArrayList<>();
        in1.add(new RecipeItem.RecipeIngredient("avocado",1,R.drawable.avocado,false));
        in1.add(new RecipeItem.RecipeIngredient("tomato",1,R.drawable.tomato,true));
        in1.add(new RecipeItem.RecipeIngredient("toast",2,R.drawable.bread,false));
        in1.add(new RecipeItem.RecipeIngredient("pepper",1,R.drawable.pepper,true));
        ArrayList<RecipeItem.RecipeIngredient> in2=new ArrayList<>();
        in2.add(new RecipeItem.RecipeIngredient("tomato",2,R.drawable.tomato,true));
        in2.add(new RecipeItem.RecipeIngredient("egg",3,R.drawable.egg,true));
        ArrayList<RecipeItem.RecipeIngredient> in3=new ArrayList<>();
        in3.add(new RecipeItem.RecipeIngredient("potato",2,R.drawable.potato,true));
        in3.add(new RecipeItem.RecipeIngredient("garlic",1,R.drawable.garlic,true));
        in3.add(new RecipeItem.RecipeIngredient("pepper",1,R.drawable.pepper,true));

        ArrayList<RecipeItem.RecipeIngredient> in4=new ArrayList<>();
        in4.add(new RecipeItem.RecipeIngredient("tomato",1,R.drawable.tomato,true));
        in4.add(new RecipeItem.RecipeIngredient("garlic",1,R.drawable.garlic,true));
        in4.add(new RecipeItem.RecipeIngredient("onion",1,R.drawable.onion,false));
        in4.add(new RecipeItem.RecipeIngredient("spinach",1,R.drawable.spinach,false));
        ArrayList<RecipeItem.RecipeIngredient> in5=new ArrayList<>();
        in5.add(new RecipeItem.RecipeIngredient("potato",2,R.drawable.potato,true));
        in5.add(new RecipeItem.RecipeIngredient("onion",1,R.drawable.onion,false));
        in5.add(new RecipeItem.RecipeIngredient("carrot",1,R.drawable.carrot,false));
        in5.add(new RecipeItem.RecipeIngredient("garlic",1,R.drawable.garlic,true));
        ArrayList<RecipeItem.RecipeIngredient> in6=new ArrayList<>();
        in6.add(new RecipeItem.RecipeIngredient("egg",2,R.drawable.egg,true));
        in6.add(new RecipeItem.RecipeIngredient("milk",1,R.drawable.milk,false));
        in6.add(new RecipeItem.RecipeIngredient("flour",1,R.drawable.ingredient,false));
        in6.add(new RecipeItem.RecipeIngredient("banana",2,R.drawable.banana,false));

        RecipeItem recipe1 = new RecipeItem("Avocado Toast", (float)4.5,2,in1,R.drawable.avocado_toast, "https://cookieandkate.com/avocado-toast-recipe/");
        RecipeItem recipe2 = new RecipeItem("Tomato Egg Stir-fry", (float)4.9,1,in2,R.drawable.tomato_egg, "https://cookieandkate.com/crispy-smashed-potatoes-recipe/");
        RecipeItem recipe3 = new RecipeItem("Crispy Smashed Potato", (float)4.3,2,in3,R.drawable.smashed_potato, "https://cookieandkate.com/crispy-smashed-potatoes-recipe/");
        RecipeItem recipe4 = new RecipeItem("Tomato Pasta", (float)4.1,2,in4,R.drawable.tomato_pasta, "https://www.allrecipes.com/recipe/11691/tomato-and-garlic-pasta/");
        RecipeItem recipe5 = new RecipeItem("Japanese Curry", (float)4.5,3,in5,R.drawable.japanese_curry, "https://www.chopstickchronicles.com/japanese-curry-rice/");
        RecipeItem recipe6 = new RecipeItem("Banana Crepe", (float)4.4,2,in6,R.drawable.banana_crepe, "https://www.allrecipes.com/recipe/16383/basic-crepes/");

        recipeList.add(recipe1); recipeList.add(recipe2); recipeList.add(recipe3);
        recipeList.add(recipe4); recipeList.add(recipe5); recipeList.add(recipe6);
    }
}