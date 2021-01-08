package com.example.eathaca;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eathaca.ui.community.CommunityFragment;
import com.example.eathaca.ui.food.FoodAdderHolder;
import com.example.eathaca.ui.food.FoodFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;


public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener,FloatingActionButton.OnClickListener{
    private AppCompatTextView currentTitle;
    private FloatingActionButton add;
    private FloatingActionButton ac_fab;
    private ImageButton account;
    private static Context context;
    private int accountType=1; //1 =farm users; 0 =ordinary users
    private NavDestination currentDestination;
    private static final int REQUEST_LIST_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_custom);
        View view = getSupportActionBar().getCustomView();
        currentTitle=view.findViewById(R.id.tvTitle);
        account=view.findViewById(R.id.avatar);
        account.setOnClickListener(this);


        add=findViewById(R.id.fab_add);
        add.setOnClickListener(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_community, R.id.navigation_food, R.id.navigation_recipes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener(this);

        accountType =GlobalVar.getAccountType(MainActivity.this);
        if(accountType==0)        add.setVisibility(View.GONE);
        else   add.setVisibility(View.VISIBLE);

        currentDestination = navController.getCurrentDestination();

        context=MainActivity.this;

    }






    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

        int id =controller.getCurrentDestination().getId();
        currentDestination = destination;
        switch (id){
            case R.id.navigation_food:
                currentTitle.setText(R.string.title_food);
                add.setVisibility(View.VISIBLE);
                break;
            case R.id.navigation_community:
                currentTitle.setText(R.string.title_community);
                if(accountType==1)
                add.setVisibility(View.VISIBLE);
                else add.setVisibility(View.GONE);
                break;
            case R.id.navigation_recipes:
                currentTitle.setText(R.string.title_recipes);
                add.setVisibility(View.GONE);
                break;
        }

    }



    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(MainActivity.this, R.style.FullHeightDialog);

        switch (v.getId()) {
            case R.id.fab_add:
                // add button pressed
                if (currentDestination.getId() == R.id.navigation_food) addButtonPressedAtFood(dialog);
                else addButtonPressedAtCommunity(dialog);
            break;

            case R.id.avatar:
                    addButtonPressedAtAvatar(dialog);
                break;


        }
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        dialog.getWindow().setLayout((6* width)/7, (8* height)/10);
    }

    private void addButtonPressedAtAvatar(final Dialog dialog) {
        dialog.setContentView(R.layout.custom_dialogue_account);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button logoffButton = (Button) dialog.findViewById(R.id.logoff);
        logoffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button cancelButton2 = (Button) dialog.findViewById(R.id.cancel);
        cancelButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        final EditText password=dialog.findViewById(R.id.profile_password);
        password.setText(GlobalVar.getPassword(MainActivity.this));
        final EditText username=dialog.findViewById(R.id.profile_username);
        username.setText(GlobalVar.getUsername(MainActivity.this));
        final EditText type=dialog.findViewById(R.id.profile_type);
        if(accountType==0) type.setText("Regular User");
        else type.setText("Farmer User");



        password.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onLongClick(View v) {
                password.setTransformationMethod(null);

                Handler handler = new Handler();

                handler.postDelayed(() -> password.setTransformationMethod(new PasswordTransformationMethod()),MainActivity.this, 1000);
                return true;
            }
        });
        dialog.show();
    }
    private void addButtonPressedAtCommunity(final Dialog dialog) {
        dialog.setContentView(R.layout.custom_dialogue_community);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button submitButton = (Button) dialog.findViewById(R.id.submit);
        submitButton.setOnClickListener(view -> {dialog.dismiss() ;
            CommunityFragment.saveAndUpdateNewFarmAd();
        });
        Button cancelButton = (Button) dialog.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(view -> dialog.dismiss());
        ImageButton imageButton=dialog.findViewById(R.id.front_pic);
        imageButton.setOnClickListener(view -> {
            ImagePicker.with((Activity) context)
                    .setFolderMode(true)
                    .setFolderTitle("Album")
                    .setDirectoryName("Image Picker")
                    .setMultipleMode(true)
                    .setShowNumberIndicator(true)
                    .setMaxSize(1)
                    .setRequestCode(100)
                    .start();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                //Write whatever to want to do after delay specified (1 sec)
                imageButton.setBackgroundResource(R.drawable.community_blueberries);

            }, 1000);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1000,600);

            imageButton.setLayoutParams(layoutParams);
        });
        dialog.show();
    }

    static private void addButtonPressedAtFood(final Dialog dialog) {
        dialog.setContentView(R.layout.custom_dialogue_add_ingredient);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        Button addIngredientButton = (Button) dialog.findViewById(R.id.add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodFragment foodFragment = new FoodFragment();
                foodFragment.addIngredient(new FoodAdderHolder(dialog));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}