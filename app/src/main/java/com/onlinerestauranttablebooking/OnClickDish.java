package com.onlinerestauranttablebooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class OnClickDish extends AppCompatActivity {
    ImageView img;
    TextView txtname,txtprice,txtcat;
    Button btnres;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_dish);
        
        bundle=getIntent().getExtras();
        Individualitem();
    }

    private void Individualitem() {
        img=findViewById(R.id.ivitmimage);
        txtname=findViewById(R.id.itmname);
        txtprice=findViewById(R.id.itmprice);
        txtcat=findViewById(R.id.itmcat);
        btnres=findViewById(R.id.btnres);

        if(bundle != null){
            txtname.setText(bundle.getString("itemName"));
            txtprice.setText(bundle.getString("itemPrice"));
            txtcat.setText(bundle.getString("itemCategory"));
            String image=bundle.getString("itemDishImage");

            Picasso.with(this).load(image).into(img);

            btnres.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(OnClickDish.this, Tablereserve.class);
                    startActivity(intent);
                }
            });

        }
    }
}
