package com.example.eathaca.ui.recipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eathaca.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.RecipeViewHolder>{

    private ArrayList<RecipeItem> recipes;
    private Context mContext;

    public RecipeViewAdapter(Context context, ArrayList<RecipeItem> recipes) {
        this.recipes = recipes;
        mContext = context;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_content, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        final RecipeItem myRecipe = recipes.get(position);

        // Todo: set recipe image

        holder.recipeName.setText(myRecipe.name());
        holder.rateBar.setRating(myRecipe.rate());
        holder.level.setText(myRecipe.levelString());
        holder.recipeImage.setImageResource(myRecipe.getRecipeDrawble());
        ArrayList<RecipeItem.RecipeIngredient> ingredients=myRecipe.getIngredient();
        if (ingredients.size()<4)
            holder.ellipsis.setVisibility(View.GONE);
        else
            holder.ellipsis.setVisibility(View.VISIBLE);


        try {
            int ColorAccent=Color.parseColor("#ce4257");
            // some recipe has less than 4 ingredients
            holder.label1.setText(ingredients.get(0).name+" x "+ingredients.get(0).amount);
            holder.image1.setImageResource(ingredients.get(0).drawable);
            if(ingredients.get(0).owned) {
                holder.label1.setTextColor(ColorAccent);
            }
            holder.label2.setText(ingredients.get(1).name+" x "+ingredients.get(1).amount);
            holder.image2.setImageResource(ingredients.get(1).drawable);
            if(ingredients.get(1).owned) {
                holder.label2.setTextColor(ColorAccent);
            }
            holder.label3.setText(ingredients.get(2).name+" x "+ingredients.get(2).amount);
            holder.image3.setImageResource(ingredients.get(2).drawable);
            if(ingredients.get(2).owned) {
                holder.label3.setTextColor(ColorAccent);
            }
            holder.label4.setText(ingredients.get(3).name+" x "+ingredients.get(3).amount);
            holder.image4.setImageResource(ingredients.get(3).drawable);
            if(ingredients.get(3).owned) {
                holder.label4.setTextColor(ColorAccent);
            }


        } catch (Exception e) { }



        // Todo: set ingredients

        holder.recipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myRecipe.getUrlString()));
                mContext.startActivity(browserIntent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    private void setImage(ImageView view, String name) {
        int id = mContext.getResources().getIdentifier(name.toLowerCase(), "drawable", mContext.getPackageName());
        if (id == 0) id = R.drawable.ingredient;
        view.setImageResource(id);
        Glide.with(mContext)
                .asBitmap()
                .load(id)
                .into(view);
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

         ImageView  image1;
        ImageView  image2;
        ImageView  image3;
        ImageView  image4;
        TextView label1;
        TextView label2;
        TextView label3;
        TextView label4;

        CircleImageView recipeImage;
        TextView recipeName;
        RatingBar rateBar;
        TextView level;
        ArrayList<RecipeIngredientHolder> ingredients;
        TextView ellipsis;
        RelativeLayout recipeLayout;


        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.dish_image);
            recipeName = itemView.findViewById(R.id.recipe_title);
            rateBar = itemView.findViewById(R.id.rating_bar);
            level = itemView.findViewById(R.id.level);
            ellipsis = itemView.findViewById(R.id.ellipsis);

            image1 = itemView.findViewById(R.id.image1);
             label1 = itemView.findViewById(R.id.label1);

            image2 = itemView.findViewById(R.id.image2);
           label2 = itemView.findViewById(R.id.label2);

            image3 = itemView.findViewById(R.id.image3);
            label3 = itemView.findViewById(R.id.label3);

            image4 = itemView.findViewById(R.id.image4);
            label4 = itemView.findViewById(R.id.label4);

            ingredients = new ArrayList<>();
            ingredients.add(new RecipeIngredientHolder(image1, label1));
            ingredients.add(new RecipeIngredientHolder(image2, label2));
            ingredients.add(new RecipeIngredientHolder(image3, label3));
            ingredients.add(new RecipeIngredientHolder(image4, label4));

            recipeLayout = itemView.findViewById(R.id.recipe_layout);
        }

        public class RecipeIngredientHolder {
            ImageView image;
            TextView name;

            RecipeIngredientHolder(ImageView v, TextView t) {
                image = v;
                name = t;
            }
        }
    }
}