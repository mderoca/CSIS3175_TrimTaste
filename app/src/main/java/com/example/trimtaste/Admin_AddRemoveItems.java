package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FoodItemAdapter());

        // Set up add button
        findViewById(R.id.btnAddItems).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add button click
            }
        });
    }

    private void initFoodItems() {
        String[] food = {"Seafood Pizza \n $10.00", "Cajun Chicken Burger \n $12.00", "Stir-fry spaghetti \n $15.00", "Chicken&Celery \n $10.00", "pesto pasta \n $20.00"};
        int[] images = {R.drawable.seafood_pizza, R.drawable.mock_res1_item1, R.drawable.mock_res1_item2, R.drawable.mock_res1_item3, R.drawable.mock_res1_item4};

        for (int i = 0; i < food.length; i++) {
            FoodItem foodItem = new FoodItem();
            foodItem.setText(food[i]);
            foodItem.setImage(images[i]);
            foodItems.add(foodItem);
        }
    }

    private class FoodItemAdapter extends RecyclerView.Adapter<FoodItemViewHolder> {

        @Override
        public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new FoodItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(FoodItemViewHolder holder, int position) {
            FoodItem foodItem = foodItems.get(position);
            holder.imageView.setImageResource(foodItem.getImage());
            holder.textView.setText(foodItem.getText());
        }

        @Override
        public int getItemCount() {
            return foodItems.size();
        }
    }

    private class FoodItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;

        public FoodItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.txtFoodItem);
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
