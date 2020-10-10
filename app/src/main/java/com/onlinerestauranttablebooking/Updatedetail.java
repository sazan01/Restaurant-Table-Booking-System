package com.onlinerestauranttablebooking;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.onlinerestauranttablebooking.Model.RegisModel;
import com.onlinerestauranttablebooking.RestaurantAPI.RegisAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Updatedetail extends AppCompatActivity implements View.OnClickListener {
    private EditText etuname, etuemail, etupass;
    private Button btnupdateuser;
    Boolean isLoggedIn= false;

    private static final String BASE_URL= "http://10.0.2.2:3000/";

    RegisAPI regisAPI;
    Retrofit retrofit;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedetail);

        LoadUserDetail();

        etuname= findViewById(R.id.etuname);
        etuemail= findViewById(R.id.etuemail);
        etupass= findViewById(R.id.etpass);
        btnupdateuser= findViewById(R.id.btnupdateuser);

        btnupdateuser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnupdateuser){
            updatedetail();
        }
    }

    private void updatedetail() {
        createInstance();

        String newName, newEmail, newPassword;

        newName= etuname.getText().toString();
        newEmail = etuemail.getText().toString();
        newPassword = etupass.getText().toString();

        SharedPreferences sharedPreferences = (Updatedetail.this).getSharedPreferences("UserData", 0 );
        String userId = sharedPreferences.getString("id", null);

        Toast.makeText(this, "User name: +" +userId, Toast.LENGTH_SHORT).show();

        Call<String> updateProfileData = regisAPI.updatedetail(userId, newName, newEmail,newPassword);

        updateProfileData.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(Updatedetail.this, "User Detail Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Updatedetail.this, "Error Occured!!"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        regisAPI = retrofit.create(RegisAPI.class);
    }

    private void LoadUserDetail() {
        createInstance();

        sharedPreferences = getSharedPreferences("UserData", 0);

        String userid = sharedPreferences.getString("uid", null);
        Toast.makeText(Updatedetail.this, "User id:"+ userid, Toast.LENGTH_SHORT).show();

        Call<RegisModel> userModelCall = regisAPI.loadregis(userid);

        userModelCall.enqueue(new Callback<RegisModel>() {
            @Override
            public void onResponse(Call<RegisModel> call, Response<RegisModel> response) {
                RegisModel regisModel= response.body();
                etuname.setText(regisModel.getName());
                etuemail.setText(regisModel.getEmail());
                etupass.setText(regisModel.getPass());
            }

            @Override
            public void onFailure(Call<RegisModel> call, Throwable t) {
                Toast.makeText(Updatedetail.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
