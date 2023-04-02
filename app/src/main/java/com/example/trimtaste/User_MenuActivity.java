package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class User_MenuActivity extends AppCompatActivity {
    private RecyclerView menuRecyclerView;
    private User_MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        // Get the restaurant ID from the previous activity
        int restaurantId = getIntent().getIntExtra("restaurantId", 0);

        // Fetch the menu items from the database
        ArrayList<MenuItem> menuItems = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.T3COL_1, DatabaseHelper.T3COL_2, DatabaseHelper.T3COL_3};
        String selection = DatabaseHelper.T3COL_4 + " = ?";
        String[] selectionArgs = {String.valueOf(restaurantId)};
        Cursor cursor = db.query(DatabaseHelper.TABLE3_NAME, projection, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL_1));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL_2));
            double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.T3COL_3));
            menuItems.add((MenuItem) new User_MenuItem(id, name, price));
        }
        cursor.close();
        db.close();

        // Initialize the RecyclerView and adapter
        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuAdapter = new User_MenuAdapter(this, menuItems);

        // Set the RecyclerView layout manager and adapter
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        menuRecyclerView.setLayoutManager(layoutManager);
        menuRecyclerView.setAdapter(menuAdapter);
    }
}