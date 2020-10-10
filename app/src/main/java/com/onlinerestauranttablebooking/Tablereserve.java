package com.onlinerestauranttablebooking;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import com.onlinerestauranttablebooking.channel.notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tablereserve extends AppCompatActivity {
    private EditText resName, etTime, etDate, etnumofpep;
    private Button btnreserve;
    private NotificationManagerCompat notificationManagerCompat;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablereserve);

        notificationManagerCompat = NotificationManagerCompat.from(Tablereserve.this);
        notification channel = new notification(Tablereserve.this);
        channel.notification();

        resName=findViewById(R.id.resname);
        etTime= findViewById(R.id.ettime);
        etDate= findViewById(R.id.etdate);
        etnumofpep= findViewById(R.id.etnumofpep);
        btnreserve= findViewById(R.id.btnreserve);

        btnreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final RegisAPI regisAPI = retrofit.create(RegisAPI.class);

                String name= resName.getText().toString();
                String time = etTime.getText().toString();
                String date = etDate.getText().toString();
                String num= etnumofpep .getText().toString();

                Intent intent = new Intent(Tablereserve.this, Dashboard.class);
                startActivity(intent);

                Call<String> reservetablecall = regisAPI.reservetable(name,time,date,num);
                DisplayNotification();

                reservetablecall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Tablereserve.this,response.body(),Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(Tablereserve.this,"fail",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });

    }

    private void DisplayNotification() {
        Notification notification = new NotificationCompat.Builder(Tablereserve.this, com.onlinerestauranttablebooking.channel.notification.Channel_3)
                .setSmallIcon(R.drawable.ic_chat_bubble_black_24dp)
                .setContentTitle("Table Reserved Successfully")
                .setContentText("Welcome to dashboard")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }
}
