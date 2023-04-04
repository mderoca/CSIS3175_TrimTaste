package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class User_ConfirmOrder extends AppCompatActivity
        implements User_RestaurantAdapter.ItemClickListener{

    TextView txtTotalCost;

    User_RestaurantAdapter adapter;

    DatabaseHelper db;

    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_order);

        // Instantiate the DatabaseHelper class
        db = new DatabaseHelper(this);

        SharedPreferences sh = getSharedPreferences("FoodSharedPref",MODE_PRIVATE);

        String orderNum = sh.getString("orderNum", "");

        boolean orderFound = db.displayOrderInfo(orderNum);

        String[] menuItems = db.getItemsFromOrder(orderNum);
        Integer[] foodItems;
        Log.d("MenuItemInfo", "menuItems: " + menuItems);

        if(orderFound){

            if(menuItems.length == 5) {
                foodItems = new Integer[]{R.drawable.seafood_pizza, R.drawable.seafood_pizza, R.drawable.seafood_pizza,
                        R.drawable.seafood_pizza, R.drawable.seafood_pizza};
            } else if(menuItems.length == 4) {
                foodItems = new Integer[]{R.drawable.seafood_pizza, R.drawable.seafood_pizza, R.drawable.seafood_pizza,
                        R.drawable.seafood_pizza};
            } else if(menuItems.length == 3) {
                foodItems = new Integer[]{R.drawable.seafood_pizza, R.drawable.seafood_pizza, R.drawable.seafood_pizza};
            } else if(menuItems.length == 2) {
                foodItems = new Integer[]{R.drawable.seafood_pizza, R.drawable.seafood_pizza};
            } else {
                foodItems = new Integer[]{R.drawable.seafood_pizza};
            }

            RecyclerView recyclerView = findViewById(R.id.recyclerView2);
            int numOfColumns = 1;
            recyclerView.setLayoutManager(new GridLayoutManager
                    (this,numOfColumns));
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new User_RestaurantAdapter(User_ConfirmOrder.this,foodItems,menuItems);
            adapter.setClickListener(User_ConfirmOrder.this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this,"Order not found", Toast.LENGTH_SHORT).show();
        }

        //get OpPickp
        RadioButton btnPickup, btnDelivery;
        btnPickup = findViewById(R.id.rBtnPickup);
        btnDelivery = findViewById(R.id.rBtnDelivery);




            //get total display total
            txtTotalCost = findViewById(R.id.txtTotalCost);
            double totalCost = 0.0;

            for (String menuItem : menuItems) {
                double menuItemPrice = db.getMenuItemPrice(menuItem);
                totalCost += menuItemPrice;
            }
            String totalCostString = Double.toString(totalCost);
            txtTotalCost.setText("$" + totalCostString);

            //Place Order
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnPickup.isChecked()){
                    boolean updated = db.updateOpPickup(orderNum, "Pickup");
                    if(!updated){
                        //Toast.makeText(this,"NOT UPDATED", Toast.LENGTH_SHORT).show();
                    }
                }

                if (btnDelivery.isChecked()) {
                    boolean updated = db.updateOpPickup(orderNum, "Delivery");
                    if (!updated) {
                        //Toast.makeText(this, "NOT UPDATED", Toast.LENGTH_SHORT).show();
                    }
                }
                startActivity(new Intent(User_ConfirmOrder.this, Usser_History.class));
            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {
        SharedPreferences sh = getSharedPreferences("FoodSharedPref",MODE_PRIVATE);

        String orderNum = sh.getString("orderNum", "");
        String[] menuItems = db.getItemsFromOrder(orderNum);

        Toast.makeText(this,menuItems[position], Toast.LENGTH_SHORT).show();
    }
}