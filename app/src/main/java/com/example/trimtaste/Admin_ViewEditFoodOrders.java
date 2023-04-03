package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Admin_ViewEditFoodOrders extends AppCompatActivity {

    private ListView listView;
    private OrderAdapter adapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_edit_food_orders);

        // Initialize the list view
        listView = findViewById(R.id.listView);

        // Initialize the order list
        orderList = new ArrayList<>();

        // Create some sample orders (replace this with your actual order retrieval code)
        Order order1 = new Order(1, "John Doe", "Pizza", "Large", 2);
        Order order2 = new Order(2, "Jane Smith", "Burger", "Medium", 1);
        orderList.add(order1);
        orderList.add(order2);

        // Create the adapter and set it to the list view
        adapter = new OrderAdapter(this, orderList);
        listView.setAdapter(adapter);

        // Set the click listener for the list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected order
                Order selectedOrder = orderList.get(position);

                // Start the edit order activity and pass the selected order as an extra
                Intent intent = new Intent(Admin_ViewEditFoodOrders.this, EditOrderActivity.class);
                intent.putExtra("order", selectedOrder);
                startActivity(intent);
            }
        });
    }
}
