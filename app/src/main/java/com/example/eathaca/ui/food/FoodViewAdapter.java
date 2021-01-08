package com.example.eathaca.ui.food;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.example.eathaca.R;

import java.util.ArrayList;

public class FoodViewAdapter extends RecyclerView.Adapter<FoodViewAdapter.FoodViewHolder>{

    private static final String TAG = "FoodViewAdapter";
    private ArrayList<Ingredient> ingredients;
    private Context mContext;

    public FoodViewAdapter(Context context, ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
        mContext = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_content, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Ingredient myIngredient = ingredients.get(position);

        setImage(holder.ingredientImage, myIngredient.name());
        setFreshnessImage(holder.freshnessImage, myIngredient);

        holder.ingredientName.setText(myIngredient.name());
        holder.storageLife.setText(myIngredient.storageLife());
        holder.amount.setText(myIngredient.amountString());

        holder.ingredientLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext, R.style.FullHeightDialog);

                Log.d(TAG, "onClick: clicked on: " + myIngredient.name());

                Toast.makeText(mContext, myIngredient.name(), Toast.LENGTH_SHORT).show();

                dialog.setContentView(R.layout.custom_dialogue_food);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                FoodDialogHolder ingredientCard = new FoodDialogHolder(dialog);

                setImage(ingredientCard.ingredientImage, myIngredient.name());

                ingredientCard.ingredientName.setText(myIngredient.name());
                ingredientCard.amount.setText(myIngredient.amountString());
                ingredientCard.addedDate.setText(myIngredient.addedDateString());
                ingredientCard.expiredDate.setText(myIngredient.expiredDateString());
                ingredientCard.freshnessBar.setProgress((int)(myIngredient.freshness()*100));

                ingredientCard.addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ingredientCard.minusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return ingredients.size();
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

    private void setFreshnessImage(ImageView view, Ingredient ingredient) {
        double freshness = ingredient.freshness();
        if (freshness > 0.4) setImage(view, "good");
        else if (freshness > 0.2) setImage(view, "median");
        else setImage(view, "bad");
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
        //Todo: add new ingredient to database
        notifyDataSetChanged();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView ingredientImage;
        TextView ingredientName;
        TextView amount;
        ImageView freshnessImage;
        TextView storageLife;
        RelativeLayout ingredientLayout;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            amount = itemView.findViewById(R.id.ingredient_amount);
            freshnessImage = itemView.findViewById(R.id.freshness_image);
            storageLife = itemView.findViewById(R.id.storage_life);
            ingredientLayout = itemView.findViewById(R.id.ingredient_layout);
        }
    }

    public class FoodDialogHolder{

        ImageView ingredientImage;
        TextView ingredientName;
        TextView amount;
        TextView addedDate;
        TextView expiredDate;
        ImageButton addButton;
        ImageButton minusButton;
        RoundCornerProgressBar freshnessBar;

        FoodDialogHolder(@NonNull Dialog dialog) {
            ingredientImage = dialog.findViewById(R.id.ingredient_image);
            ingredientName = dialog.findViewById(R.id.ingredient_title);
            amount = dialog.findViewById(R.id.ingredient_amount);
            addedDate = dialog.findViewById(R.id.added_date);
            expiredDate = dialog.findViewById(R.id.expired_date);
            addButton = dialog.findViewById(R.id.add_amount);
            minusButton = dialog.findViewById(R.id.minus_amount);
            freshnessBar = dialog.findViewById(R.id.freshness_bar);
        }
    }
}
