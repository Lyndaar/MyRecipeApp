package com.killermindset.myrecipeapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.MyHolder>{


    Context mContext;
    List<Recipe_Model> recipes;
    RecipeOnclickListener mListener;


    public interface  RecipeOnclickListener{
        void onClick(int position);
    }

    public MyRecipeAdapter(Context context, List<Recipe_Model> recipes) {

        mContext = context;
        this.recipes = recipes;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Recipe_Model recipe = recipes.get(position);
        holder.tagline.setText(recipe.getHeadline());
        holder.name.setText(recipe.getName());
        holder.calories.setText(recipe.getCalories());


        if (recipe.getRating() == null){
            holder.rating.setRating(0);
        }else{
            holder.rating.setRating(recipe.getRating().floatValue());
        }

        Glide.with(mContext).load(recipe.getImage()).into(holder.recipe_image);
    }

    @Override
    public int getItemCount() { return recipes == null ? 0 : recipes.size(); }

    public void setRecipes(List<Recipe_Model> recipes){
        for(Recipe_Model recipe:recipes){
            addRecipe(recipe);
        }
    }

    public void addRecipe(Recipe_Model recipe){
        recipes.add(recipe);
        notifyItemInserted(recipes.size());
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_item, parent, false);

        return new MyHolder(view);
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView recipe_image;
        private TextView tagline;
        private RatingBar rating;
        private TextView name;
        private TextView calories;



        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.RecipeName);
            recipe_image = itemView.findViewById(R.id.food_image);
            tagline = itemView.findViewById(R.id.RecipeDetail);
            rating = itemView.findViewById(R.id.recipe_rating);
            calories = itemView.findViewById(R.id.calories);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(DetailActivity.RECIPE_POSITION,getAdapterPosition());
            mContext.startActivity(intent);
        }
    }

}
