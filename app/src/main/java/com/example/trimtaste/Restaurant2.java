package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Restaurant2 extends AppCompatActivity
        implements User_RestaurantAdapter.ItemClickListener{

    Integer[] foodItems ={R.drawable.seafood_pizza,R.drawable.seafood_pizza,R.drawable.seafood_pizza,
            R.drawable.seafood_pizza,R.drawable.seafood_pizza};
    ImageView imageView;

    User_RestaurantAdapter adapter;

    DatabaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_each_restaurant);
        imageView = findViewById(R.id.imgLarge);

        // Instantiate the DatabaseHelper class
        db = new DatabaseHelper(this);

        String[] menuItems = db.getMenuItems(2);
        Log.d("MenuItems", "menuItems = " + menuItems);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager
                (this,numOfColumns));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new User_RestaurantAdapter(this,foodItems,menuItems);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"Selected specie" + (position+1),
                Toast.LENGTH_SHORT).show();
        imageView.setImageResource(adapter.getItem(position));

    }
}