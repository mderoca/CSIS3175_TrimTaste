package com.example.trimtaste;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
//
//        SharedPreferences sh = getSharedPreferences("FoodSharedPref", MODE_PRIVATE);
//
//        String orderNum = sh.getString("orderNum", "");

        List<String> orderNumbers = db.getOrderNumbers();

        // Create an ArrayAdapter for the order numbers
        ArrayAdapter<String> orderNumberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderNumbers);

        // Get the ListView for the order numbers
        ListView orderNumberListView = findViewById(R.id.order_number_list_view);
        orderNumberListView.setAdapter(orderNumberAdapter);

        // Set an item click listener for the order number ListView
        orderNumberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String orderNumber = (String) parent.getItemAtPosition(position);

                // Get the orders for the selected order number
                List<String> orderList = db.getOrdersByNumber(orderNumber);

                // Create an ArrayAdapter for the orders
                ArrayAdapter<String> orderListAdapter = new ArrayAdapter<>(Usser_History.this, android.R.layout.simple_list_item_1, orderList);

                // Get the ListView for the orders
                ListView orderListView = findViewById(R.id.order_list_view);
                orderListView.setAdapter(orderListAdapter);
            }
        });
    }

}