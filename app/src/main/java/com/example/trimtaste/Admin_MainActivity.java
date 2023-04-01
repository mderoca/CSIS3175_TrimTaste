package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Admin_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        ImageButton imgBtnAddItems = findViewById(R.id.imgBtnAddItems);
        ImageButton imgBtnViewOrders = findViewById(R.id.imgBtnViewOrders);
        ImageButton imgBtnSendReminders = findViewById(R.id.imgBtnReminder);
        ImageButton imgBtnReports = findViewById(R.id.imgBtnReports);
        ImageButton imgBtnLogout = findViewById(R.id.imgBtnLogout);


        imgBtnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_MainActivity.this, Admin_AddRemoveItems.class));
            }
        });

        imgBtnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_MainActivity.this, Admin_ViewEditFoodOrders.class));
            }
        });

        imgBtnSendReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_MainActivity.this, Admin_reminder.class));
            }
        });

        imgBtnReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_MainActivity.this, Admin_Report.class));
            }
        });

        imgBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_MainActivity.this, Login.class));
            }
        });

    }
}