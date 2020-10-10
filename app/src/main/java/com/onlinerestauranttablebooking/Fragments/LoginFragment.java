package com.onlinerestauranttablebooking.Fragments;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onlinerestauranttablebooking.BLL.LoginBLL;
import com.onlinerestauranttablebooking.Dashboard;
import com.onlinerestauranttablebooking.MainActivity;
import com.onlinerestauranttablebooking.Model.TokenAuth;
import com.onlinerestauranttablebooking.R;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import com.onlinerestauranttablebooking.channel.notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment implements View.OnClickListener{

    private NotificationManagerCompat notificationManagerCompat;

    private EditText etemail, etpass;
    private Button btnlogin;
//    Boolean isLoggedIn=false;

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    RegisAPI regisAPI;
     Retrofit retrofit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    
    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notification channel = new notification(getActivity());
        channel.notification();

        etemail = view.findViewById(R.id.etemail);
        etpass = view.findViewById(R.id.etpass);
        btnlogin = view.findViewById(R.id.btnlogin);


        btnlogin.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("APP", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return view;
    }

   public void createInstance(){
////       Gson gson=new GsonBuilder()
////           .setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        regisAPI =retrofit.create(RegisAPI.class);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnlogin)
        {
            createInstance();

           final String email=etemail.getText().toString().trim();
           final String pass=etpass.getText().toString().trim();

            LoginBLL loginBusinessLogic = new LoginBLL(email,pass);
            StrictMode();
            TokenAuth userData = loginBusinessLogic.checkUser();

            sharedPreferences = getActivity().getSharedPreferences("UserData",MODE_PRIVATE);
            editor = sharedPreferences.edit();

            editor.putString("token", userData.getToken());
            //editor.putString("uid", userData.getRegisModel().get_id());
            editor.commit();

            Toast.makeText(getActivity(), "User Data:" + userData.getToken(), Toast.LENGTH_SHORT).show();

            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(2000);

            Intent intent = new Intent(getActivity(), Dashboard.class);
            startActivity(intent);
            getActivity().finish();
            DisplayNotification();

        }
    }

    private void DisplayNotification() {
        Notification notification = new NotificationCompat.Builder(getActivity(), com.onlinerestauranttablebooking.channel.notification.Channel_1)
                .setSmallIcon(R.drawable.ic_chat_bubble_black_24dp)
                .setContentTitle("Login Successfully")
                .setContentText("Welcome to dashboard")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }



    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
