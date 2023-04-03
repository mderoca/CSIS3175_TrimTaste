package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class User_ConfirmOrder extends AppCompatActivity
        implements User_RestaurantAdapter.ItemClickListener{

    ImageView imageView;

    User_RestaurantAdapter adapter;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm_order);

        // Instantiate the DatabaseHelper class
        db = new DatabaseHelper(this);

        SharedPreferences sh = getSharedPreferences("FoodSharedPref",MODE_PRIVATE);
        SharedPreferences.Editor foodEdit = sh.edit();

        String orderNum = sh.getString("orderNum", "");

        boolean orderFound = db.displayOrderInfo(orderNum);

        String[] menuItems = db.getItemsFromOrder(orderNum);
        Integer[] foodItems ={R.drawable.seafood_pizza,R.drawable.seafood_pizza,R.drawable.seafood_pizza};
        Log.d("MenuItemInfo", "menuItems: " + menuItems);

        if(orderFound){
            String itemName = db.getnItemName();
            String restaurantId = db.getnRestaurantId();
            String opPickup = db.getnOpPickup();
            String itemPrice = db.getnItemPrice();



            RecyclerView recyclerView = findViewById(R.id.recyclerView2);
            int numOfColumns = 1;
            recyclerView.setLayoutManager(new GridLayoutManager
                    (this,numOfColumns));
            //recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new User_RestaurantAdapter(this,foodItems,menuItems);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"Selected specie" + (position+1),
                Toast.LENGTH_SHORT).show();
    }
}