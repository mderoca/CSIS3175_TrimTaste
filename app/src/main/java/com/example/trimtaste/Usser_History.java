package com.example.trimtaste;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Usser_History extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_history);

        // Instantiate the DatabaseHelper class
        db = new DatabaseHelper(this);

        SharedPreferences sh = getSharedPreferences("FoodSharedPref",MODE_PRIVATE);

        String orderNum = sh.getString("orderNum", "");

        boolean orderFound = db.displayOrderInfo(orderNum);



        if(orderFound){
            List<String> orderList = db.getOrdersByNumber(orderNum);


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderList);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);

        } else{
            Toast.makeText(this,"Order not found", Toast.LENGTH_SHORT).show();
        }
    }
}