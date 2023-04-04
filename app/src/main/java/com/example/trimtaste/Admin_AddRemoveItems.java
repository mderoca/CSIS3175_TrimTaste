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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Admin_AddRemoveItems extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText newItemName, newItemPrice, restaurantIdEditText;
    private Button btnListItems, btnAddItems, btnDelete;
    private int restaurantId;
    private List<FoodItem> foodItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_remove_food_items);

        btnListItems = findViewById(R.id.btnListItems);

        newItemName = findViewById(R.id.edTxtNewItem);
        newItemPrice = findViewById(R.id.edTxtNewItemPrice);
        restaurantIdEditText = findViewById(R.id.restaurantIdEditText);
        btnAddItems = findViewById(R.id.btnAddItems);

        databaseHelper = new DatabaseHelper(this);

        // Set up ListItems button
        btnListItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get menu items from the database
                String restaurantIdString = restaurantIdEditText.getText().toString();

                if (!TextUtils.isEmpty(restaurantIdString)) {
                    restaurantId = Integer.parseInt(restaurantIdString);

                    // Clear the foodItems list before adding new items
                    foodItems.clear();

                    // Get menu items for the entered restaurant ID
                    String[] menuItemNames = databaseHelper.getMenuItems(restaurantId);

                    // For each menu item, create a new FoodItem object and add it to the foodItems list
                    for (String menuItemName : menuItemNames) {
                        double menuItemPrice = databaseHelper.getMenuItemPrice(menuItemName);
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

        // Set up add button
        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add button click
                String newName = newItemName.getText().toString();
                String newPrice = newItemPrice.getText().toString();

                if (newName.equals("") || newPrice.equals("")) {
                    Toast.makeText(Admin_AddRemoveItems.this, "Please fill in both fields", Toast.LENGTH_LONG).show();
                } else {
                    boolean isAdded = databaseHelper.addMenuItem(restaurantId, newName, Double.parseDouble(newPrice));

                    if (isAdded) {
                        Toast.makeText(Admin_AddRemoveItems.this, "New item successfully added!", Toast.LENGTH_LONG).show();
                        newItemName.setText("");
                        newItemPrice.setText("");
                    } else {
                        Toast.makeText(Admin_AddRemoveItems.this, "Failed to add.", Toast.LENGTH_LONG).show();
                    }
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
            FoodItemViewHolder viewHolder = new FoodItemViewHolder(itemView);

            // Move btnDelete initialization to here
            viewHolder.btnDelete = itemView.findViewById(R.id.btndeleteItem);

            return viewHolder;
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
        Button btnDelete;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.txtFoodItem);
            txtPrice = itemView.findViewById(R.id.txtPrice); // initialize txtPrice here
            btnDelete = itemView.findViewById(R.id.btndeleteItem);
            itemView.setOnClickListener(this);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        foodItems.remove(position);
                    }

                        // Get the food item at the clicked position
                    FoodItem foodItem = foodItems.get(position);

                    // Delete the item from the database
                    databaseHelper.deleteMenuItem(restaurantId, foodItem.getName());

                    // Remove the item from the list
                    foodItems.remove(position);

                }
        })
        ;}

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (position) {
                case 0:
                case 1:
                    break;
                case 2:
                    // Handle item 2 click
                    break;
                // Handle additional items
            }
        }
    }
}
