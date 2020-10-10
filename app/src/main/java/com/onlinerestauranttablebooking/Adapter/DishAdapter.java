package com.onlinerestauranttablebooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.onlinerestauranttablebooking.Model.ViewDishModel;
import com.onlinerestauranttablebooking.OnClickDish;
import com.onlinerestauranttablebooking.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    List<ViewDishModel> viewModelList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL= "http://10.0.2.2:3000";

    public DishAdapter(List<ViewDishModel> viewModelList, Context context){
        this.viewModelList = viewModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public DishAdapter.DishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View dishView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dishlist,viewGroup,false);
        return new DishViewHolder(dishView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DishViewHolder dishViewHolder, int i) {
        final ViewDishModel viewDishModel=viewModelList.get(i);
        dishViewHolder.image_name.setText(viewDishModel.getDishName());


        Picasso.with(context).load(BASE_URL+"/imageDish/"+viewDishModel.getDishImage()).into(dishViewHolder.img);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"/imageDish/"+viewDishModel.getDishImage());


        Toast.makeText(context, ""+viewModelList.size(), Toast.LENGTH_SHORT).show();

        dishViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context vcontext = v.getContext();
                String send_path= BASE_URL+"imageDish/"+viewDishModel.getDishImage();
                System.out.println(send_path);

                Intent intent = new Intent(context, OnClickDish.class);

                intent.putExtra("itemName",viewDishModel.getDishName());
                intent.putExtra("itemPrice", viewDishModel.getPrice());
                intent.putExtra("itemCategory",viewDishModel.getCategory());
                intent.putExtra("itemDishImage", BASE_URL+"/imageDish/"+viewDishModel.getDishImage());

                vcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return viewModelList.size();
    }

    public class DishViewHolder extends RecyclerView.ViewHolder {
        public TextView image_name;
        public ImageView img;
        RelativeLayout parent_layout;

        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            image_name= itemView.findViewById(R.id.image_name);
            img= itemView.findViewById(R.id.img);
            parent_layout= itemView.findViewById(R.id.parent_layout);
        }
    }
}
