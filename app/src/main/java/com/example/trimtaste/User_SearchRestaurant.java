package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.SearchView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class User_SearchRestaurant extends AppCompatActivity
        implements User_RestaurantAdapter.ItemClickListener {

    Integer[] restaurants = {R.drawable.mock_restaurant1, R.drawable.mock_restaurant2, R.drawable.mock_restaurant3, R.drawable.mock_restaurant4};

    String[] restaurantsNames = {"Chef Ron restaurant and bar", "Pho Express Angkor Noodle House", "Mediterranean Grill", "Tractor Foods"};

    User_RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_restaurant);

        SearchView searchView = findViewById(R.id.searchView);
        RecyclerView recyclerViewRes = findViewById(R.id.recyclerViewRestaurant);


//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Start of search process
//                List<Restaurant> filteredRestaurants = new ArrayList<>();
//                List<String> filteredRestaurantNames = new ArrayList<>();
//
//                for (Restaurant restaurant : restaurants) {
//                    if (restaurant.getCity().equals(query)) { // Assuming getCity() returns the city name for the restaurant
//                        filteredRestaurants.add(restaurant);
//                        filteredRestaurantNames.add(restaurant.getName());
//                    }
//                }
//
//                adapter.setData(filteredRestaurants, filteredRestaurantNames);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Processing of text changes
//                if (newText.isEmpty()) {
//                    adapter.setData(restaurants, restaurantsNames);
//                    adapter.notifyDataSetChanged();
//                }
//                return false;
//            }
//        });

        recyclerViewRes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new User_RestaurantAdapter(restaurants, restaurantsNames);
        adapter.setClickListener(this);
        recyclerViewRes.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,"Selected Restaurant" + (position+1),
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(User_SearchRestaurant.this,User_EachRestaurant.class));
    }
}