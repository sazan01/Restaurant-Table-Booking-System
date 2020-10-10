package com.onlinerestauranttablebooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.onlinerestauranttablebooking.Adapter.DishAdapter;
import com.onlinerestauranttablebooking.Model.ViewDishModel;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class ViewDish extends AppCompatActivity {
    RecyclerView dishrecyclerview;
    Retrofit retrofit;
    RegisAPI regisAPI;

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dish);

        dishrecyclerview=findViewById(R.id.dishRecyclerView);
        LinearLayoutManager lm= new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        dishrecyclerview.setLayoutManager(lm);

        getDishes();
    }

    private void getDishes() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        regisAPI = retrofit.create(RegisAPI.class);
        Call<List<ViewDishModel>> listCall=regisAPI.getDishes();
        listCall.enqueue(new Callback<List<ViewDishModel>>() {
            @Override
            public void onResponse(Call<List<ViewDishModel>> call, Response<List<ViewDishModel>> response) {
                List<ViewDishModel> viewDishModelList=response.body();
                if(viewDishModelList!=null){
                    dishrecyclerview.setAdapter(new DishAdapter(viewDishModelList,getApplicationContext()));
                }

            }

            @Override
            public void onFailure(Call<List<ViewDishModel>> call, Throwable t) {

            }
        });
    }
}
