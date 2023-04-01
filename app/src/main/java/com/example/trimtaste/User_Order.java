package com.example.trimtaste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_Order extends AppCompatActivity {
    DatabaseHelper db ;
    String[] food ={"Seafood Pizza \n $10.00", "Cajun Chicken Burger \n $12.00", "Stir-fry spaghetti \n $15.00", "Chicken&Celery \n $10.00", "pesto pasta \n $20.00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        Button btnEdit = findViewById(R.id.button);
        db = new DatabaseHelper(this);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = db.addMenuItems(food, 1);
                if (isInserted) {
                    Toast.makeText(User_Order.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(User_Order.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}