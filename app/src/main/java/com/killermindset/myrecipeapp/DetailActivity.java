package com.killermindset.myrecipeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

public class DetailActivity extends AppCompatActivity {


    public static final String RECIPE_POSITION = "recipe_position";

    TextView nameOfRecipe;
    TextView favoriteName;
    TextView Ingredient;
    TextView headline;
    TextView nameOfFiber;
    ImageView imageview;
    TextView description;
    TextView carbos;
    TextView Protein;
    TextView String;
    TextView fats;
    TextView difficulty;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageview = findViewById(R.id.thumb_header);
        Intent intent = getIntent();
        nameOfRecipe = findViewById(R.id.RecipeName);
        Ingredient =  findViewById(R.id.recipe_ingredient);
        description = findViewById(R.id.recipe_description);
        headline = findViewById(R.id.recipe_extension);
        difficulty = findViewById(R.id.difficulty);
        if (intent != null) {
            int position = intent.getIntExtra(RECIPE_POSITION, 0);
            Recipe_Model Recipe_Model = MainActivity.getRecipe(position);
            Glide.with(this).load(Recipe_Model.getImage()).into(imageview);


            nameOfRecipe.setText(Recipe_Model.getName());
            Ingredient.setText(Recipe_Model.getIngredients().toString());
            description.setText(Recipe_Model.getDescription());
            headline.setText(Recipe_Model.getHeadline());
            difficulty.setText(Recipe_Model.getDifficulty().toString());
            MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favourite);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite){
                                SharedPreferences.Editor editor = getSharedPreferences(" com.killermindset.myrecipeapp.DetailActivity",MODE_PRIVATE).edit();
                                editor.putBoolean("Favourite Added", true);
                                editor.commit();
                                /*saveFavourite();*/
                                Snackbar.make(buttonView, "Added to Favourite",Snackbar.LENGTH_SHORT).show();
                            }else{
                                SharedPreferences.Editor editor = getSharedPreferences(" com.killermindset.myrecipeapp.DetailActivity",MODE_PRIVATE).edit();
                                editor.putBoolean("Favourite Removed", true);
                                editor.commit();
                                Snackbar.make(buttonView, "Removed to Favourite",Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

        }

        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         initCollapsingToolbar();



    }

    private void initCollapsingToolbar() {
    }
}
