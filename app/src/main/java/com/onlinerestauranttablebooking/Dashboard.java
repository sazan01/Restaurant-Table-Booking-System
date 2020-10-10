package com.onlinerestauranttablebooking;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    ImageButton btnreserve, btnorder, btnuserupdate, btncontact;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnreserve = findViewById(R.id.btnreserve);
        btnorder = findViewById(R.id.btnorder);
        btnuserupdate = findViewById(R.id.btnuserupdate);
        btncontact = findViewById(R.id.btncontact);

        btnreserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Tablereserve.class);
                startActivity(intent);
            }
        });

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, ViewDish.class);
                startActivity(intent);
            }
        });

        btnuserupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Updatedetail.class);
                startActivity(intent);
            }
        });

        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Contactus.class);
                startActivity(intent);
            }
        });
    }

        public void proximity(){
            sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
            Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if (sensor == null)
            {
                Toast.makeText(this, "No sensor detected", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Sensor Kicking in .....", Toast.LENGTH_SHORT).show();
            }

            SensorEventListener proximityListener=new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    WindowManager.LayoutParams params = Dashboard.this.getWindow().getAttributes();
                    if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                        if (event.values[0] == 0) {
                            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                            params.screenBrightness = 0;
                            getWindow().setAttributes(params);
                        } else {
                            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                            params.screenBrightness = -1f;
                            getWindow().setAttributes(params);
                        }
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            sensorManager.registerListener(proximityListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);


    }
}
