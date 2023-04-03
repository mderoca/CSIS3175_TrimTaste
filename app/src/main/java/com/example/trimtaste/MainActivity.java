package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnaddorRemove = findViewById(R.id.btnAddorRemove);
        Button btnVieworEdit = findViewById(R.id.btnVieworEdit);
        Button btnSendAReminder = findViewById(R.id.btnSendaReminder);
        Button btnGenerateReports = findViewById(R.id.btnGenerateReport);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnSendAReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Admin_reminder.class));

            }
        });
        btnGenerateReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Admin_Report.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"You have successfully logged out",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        btnaddorRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Admin_AddRemoveItems.class));
            }
        });
        btnVieworEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Admin_ViewEditFoodOrders.class));
            }
        });
    }
}
