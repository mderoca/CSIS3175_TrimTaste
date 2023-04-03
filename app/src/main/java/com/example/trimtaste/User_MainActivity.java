package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class User_MainActivity extends AppCompatActivity {
    DatabaseHelper db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        ImageButton btnSearch = findViewById(R.id.imgBtnSearch);
        ImageButton btnOrderFood = findViewById(R.id.imgBtnOrderFood);
        ImageButton btnViewOrderDetails = findViewById(R.id.imgBtnOrderDetails);
        ImageButton btnHistory = findViewById(R.id.imgBtnViewHistory);
        ImageButton btnProfile = findViewById(R.id.imgBtnProfile);
        ImageButton btnLogout = findViewById(R.id.imgBtnLogout);

        db = new DatabaseHelper(this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_MainActivity.this, User_SearchRestaurant.class));

                boolean isInserted = db.addRestaurantData();
                if (isInserted) {
                    //Toast.makeText(User_MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(User_MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_MainActivity.this, User_SearchRestaurant.class));
            }
        });


        btnViewOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_MainActivity.this, User_Order.class));
            }
        });


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_MainActivity.this, Usser_History.class));
            }
        });


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_MainActivity.this, User_edit_Profile.class));
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllMenuItems();
                db.deleteAllOrders();
                startActivity(new Intent(User_MainActivity.this, Login.class));
            }
        });



    }
}