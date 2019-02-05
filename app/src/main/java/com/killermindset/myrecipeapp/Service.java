package com.killermindset.myrecipeapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/Nsikaktopdown/Recipe/master/Recipe.json")
    Call<List<Recipe_Model>> readJson();
}
