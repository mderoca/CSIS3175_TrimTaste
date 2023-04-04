package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Admin_AddRemoveItems extends AppCompatActivity {

    private List<FoodItem> foodItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_remove_food_items);
        Button btnAddItems = findViewById(R.id.btnAddItems);
        // Initialize food items
        initFoodItems();


        findViewById(R.id.btnAddItems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get menu items from the database
                DatabaseHelper dbHelper = new DatabaseHelper(Admin_AddRemoveItems.this);
                EditText restaurantIdEditText = findViewById(R.id.restaurantIdEditText);
                String restaurantIdString = restaurantIdEditText.getText().toString();

                if (!TextUtils.isEmpty(restaurantIdString)) {
                    int enteredRestaurantId = Integer.parseInt(restaurantIdString);

                    // Get menu items for the entered restaurant ID
                    String[] menuItemNames = dbHelper.getMenuItems(enteredRestaurantId);

                    // For each menu item, create a new FoodItem object and add it to the foodItems list
                    for (String menuItemName : menuItemNames) {
                        double menuItemPrice = dbHelper.getMenuItemPrice(menuItemName);
                        FoodItem foodItem = new FoodItem(menuItemName, menuItemPrice);
                        foodItems.add(foodItem);
                    }

                    // Set up RecyclerView
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Admin_AddRemoveItems.this));
                    recyclerView.setAdapter(new FoodItemAdapter());
                }
            }
        });
    }

    private void initFoodItems() {
        // Get menu items from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        EditText restaurantIdEditText = findViewById(R.id.restaurantIdEditText);
        String restaurantIdString = restaurantIdEditText.getText().toString();

        if (!TextUtils.isEmpty(restaurantIdString)) {
            int enteredRestaurantId = Integer.parseInt(restaurantIdString);

            // Get menu items for the entered restaurant ID
            String[] menuItemNames = dbHelper.getMenuItems(enteredRestaurantId);

            // For each menu item, create a new FoodItem object and add it to the foodItems list
            for (String menuItemName : menuItemNames) {
                double menuItemPrice = dbHelper.getMenuItemPrice(menuItemName);
                FoodItem foodItem = new FoodItem(menuItemName, menuItemPrice);
                foodItems.add(foodItem);
            }  // Set up RecyclerView
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new FoodItemAdapter());


        }
    }




    private class FoodItemAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {

        @Override
        public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.food_item_row, parent, false);
            return new FoodItemViewHolder(itemView);

        }



        @Override
        public void onBindViewHolder(FoodItemViewHolder holder, int position) {

            FoodItem foodItem = foodItems.get(position);
            holder.textView.setText(foodItem.getText());
            holder.txtPrice.setText(String.format("%.2f", foodItem.getPrice()));

        }

        @Override
        public int getItemCount() {
            return foodItems.size();
        }
    }

    private class FoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;
        TextView txtPrice; // declare txtPrice here

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.txtFoodItem);
            txtPrice = itemView.findViewById(R.id.txtPrice); // initialize txtPrice here
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (position) {
                case 0:
                case 1:
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.dominos.ca/?gclid=CjwKCAjw_MqgBhAGEiwAnYOAemmCfgrWvNvdvCRtfkOpbUS2KIm11Zb8uWdD8iJNgMj0kZBNJy8SdxoCP_MQAvD_BwE&gclsrc=aw.ds")));
                    break;
                case 2:
                    // Handle item 2 click
                    break;
                // Handle additional items
            }
        }
    }
}
