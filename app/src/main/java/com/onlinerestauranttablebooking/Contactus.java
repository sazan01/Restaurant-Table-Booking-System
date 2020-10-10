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

public class Contactus extends AppCompatActivity {
    private EditText conName, conEmail, conPhone, conSubject, conMessage;
    Button btnSend;
    private NotificationManagerCompat notificationManagerCompat;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static final String BASE_URL = "http://10.0.2.2:3000/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        notification channel = new notification(this);
        channel.notification();

        conName= findViewById(R.id.conName);
        conEmail= findViewById(R.id.conEmail);
        conPhone= findViewById(R.id.conPhone);
        conSubject = findViewById(R.id.conSubject);
        conMessage = findViewById(R.id.conMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegisAPI regisAPI = retrofit.create(RegisAPI.class);

                String name= conName.getText().toString();
                String email = conEmail.getText().toString();
                String phone = conPhone.getText().toString();
                String sub= conSubject.getText().toString();
                String message = conMessage.getText().toString();

                Intent intent = new Intent(Contactus.this,Dashboard.class);
                startActivity(intent);
                DispalyNotification();

                Call<String> feedbackcall = regisAPI.feedback(name,email,phone,sub,message);

                feedbackcall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(Contactus.this,response.body(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Contactus.this,"fail",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void DispalyNotification() {
        Notification notification = new NotificationCompat.Builder(this, com.onlinerestauranttablebooking.channel.notification.Channel_2)
                .setSmallIcon(R.drawable.ic_chat_bubble_black_24dp)
                .setContentTitle("Contact")
                .setContentText("Your Feedback Has been Sent")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1, notification);
    }
}
