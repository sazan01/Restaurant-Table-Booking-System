package com.onlinerestauranttablebooking.Fragments;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.onlinerestauranttablebooking.channel.notification;
import com.onlinerestauranttablebooking.R;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterFragment extends Fragment implements View.OnClickListener{

    private EditText name, email, pass, re_pass;
    private Button btnsignup;
    RegisAPI regisAPI;

    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        name = view.findViewById(R.id.etname);
        email = view.findViewById(R.id.etemail);
        pass = view.findViewById(R.id.etpass);
        re_pass = view.findViewById(R.id.etrepass);
        btnsignup= view.findViewById(R.id.btnsignup);

        btnsignup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnsignup) {
//            Gson gson=new GsonBuilder()
//            .setLenient().create();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    regisAPI = retrofit.create(RegisAPI.class);

                    String userType="User";
                    Call<String> UserCall = regisAPI.useradd(name.getText().toString(),email.getText().toString(),pass.getText().toString(),re_pass.getText().toString(),userType);

                    UserCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            if (!response.isSuccessful()){
                                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                            return;
                            }

                            Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginFragment.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getActivity(), "Registered Successsfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

    }

}
