package com.onlinerestauranttablebooking.BLL;

import com.onlinerestauranttablebooking.Model.TokenAuth;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class LoginBLL {
    private String email;
    private String pass;
    TokenAuth authtoken;
    private static final String BASE_URL = "http://10.0.2.2:3000/";

    Retrofit retrofit;

    public LoginBLL(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public TokenAuth checkUser(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RegisAPI regisAPI = retrofit.create(RegisAPI.class);

        Call<TokenAuth> Logincall = regisAPI.Login(email, pass);
        try {
            Response<TokenAuth> loginResponse = Logincall.execute();
            authtoken = loginResponse.body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return authtoken;
    }
}


