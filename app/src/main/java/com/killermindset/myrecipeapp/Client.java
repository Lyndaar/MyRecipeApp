package com.killermindset.myrecipeapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Client {

    public static final String BASE_URL = "https://raw.githubusercontent.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;


    }
}
