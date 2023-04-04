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
import android.widget.Toast;

public class Restaurant2 extends AppCompatActivity
        implements User_RestaurantAdapter.ItemClickListener{

    Integer[] foodItems ={R.drawable.seafood_pizza,R.drawable.seafood_pizza,R.drawable.seafood_pizza,
            R.drawable.seafood_pizza,R.drawable.seafood_pizza};
    ImageView imageView;

    User_RestaurantAdapter adapter;

    Button btnAdd, btnDelete, btnConfirm;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant2); //correct layout
        imageView = findViewById(R.id.imgLarge);
        btnAdd = findViewById(R.id.btnAddToOrder);
        btnDelete = findViewById(R.id.btnDeleteFromOrder);
        btnConfirm = findViewById(R.id.btnConfirm);

        db = new DatabaseHelper(this);

        //restaurant id
        String[] menuItems = db.getMenuItems(2);
        Log.d("MenuItems", "menuItems = " + menuItems);

        //recycler view id
        RecyclerView recyclerView = findViewById(R.id.recyclerView3);
        int numOfColumns = 1;
        recyclerView.setLayoutManager(new GridLayoutManager
                (this,numOfColumns));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new User_RestaurantAdapter(this,foodItems,menuItems);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Restaurant2.this, User_ConfirmOrder.class));
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        // Instantiate the DatabaseHelper class
        DatabaseHelper db = new DatabaseHelper(this);

        String[] menuItems2 = db.getMenuItems(2);

        int menuId = db.getMenuItemId(menuItems2[position]);

        boolean menuItemFound = db.displayMenuInfo(menuId);

        SharedPreferences userSh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        SharedPreferences sh = getSharedPreferences("FoodSharedPref",MODE_PRIVATE);
        SharedPreferences.Editor foodEdit = sh.edit();

        if(menuItemFound){
            String menuItemName = db.getMenuItemName();
            String menuItemPrice = db.getMenuItemPrice();
            String menuItemResId = db.getMenuRestaurantId();

            foodEdit.putString("orderNum", menuItemResId);
            foodEdit.commit();

            String user = userSh.getString("username", "");
            db.displayUserProfile(user);
            String userId = db.getUserId();

            Log.d("MenuItemInfo", "Menu Item Name " + menuItemName);
            Log.d("MenuItemInfo", "Menu Item Price " + menuItemPrice);
            Log.d("MenuItemInfo", "Menu Item ResID " + menuItemResId);
            Log.d("MenuItemInfo", "UserID " + userId);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String menuIdd = Integer.toString(menuId);
                    String status = "Ordered";
                    db.addOrder(userId, menuItemResId, menuItemResId, menuIdd,
                            menuItemName, " ", menuItemPrice, status);


                }
            });

        }

        Toast.makeText(this,"Selected specie" + (position+1),
                Toast.LENGTH_SHORT).show();
        imageView.setImageResource(adapter.getItem(position));

    }
}