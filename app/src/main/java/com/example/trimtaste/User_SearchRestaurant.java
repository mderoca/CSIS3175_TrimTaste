package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;

public class User_SearchRestaurant extends AppCompatActivity
//        implements User_RestaurantAdapter.ItemClickListener
{
    private ListView listView;
    private SimpleAdapter adapter;

    Integer[] restaurants = {R.drawable.mock_restaurant1,
            R.drawable.mock_restaurant2,
            R.drawable.mock_restaurant3,
            R.drawable.mock_restaurant4,
            R.drawable.mock_restaurant5,
            R.drawable.mock_restaurant6,
            R.drawable.mock_restaurant7,
            R.drawable.mock_restaurant8};

    String[] restaurantsNames = {"Chef Ron restaurant and bar",
            "Pho Express Angkor Noodle House",
            "Mediterranean Grill",
            "Tractor Foods",
            "Cactus Club Cafe",
            "Pizza Garden",
            "The Fish Counter",
            "Red Wagon Cafe"};
    String[] cities = {"Vancouver", "Richmond", "Burnaby", "Vancouver", "Burnaby", "Burnaby", "Vancouver", "Richmond"};


//    User_RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_restaurant);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.txtFindARes);

        ArrayList<HashMap<String,String>> aList = new ArrayList<>();
        for (int i = 0; i < restaurantsNames.length; i++) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("imgRes", Integer.toString(restaurants[i]));
            hashMap.put("txtResName", restaurantsNames[i]);
            aList.add(hashMap);
        }

        adapter = new SimpleAdapter(User_SearchRestaurant.this, aList,
                R.layout.list_item_restaurant, new String[]{"imgRes", "txtResName"},
                new int[]{R.id.imgRes, R.id.txtResName});
        listView = findViewById(R.id.listViewRes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(User_SearchRestaurant.this,User_EachRestaurant.class));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

            }
        });
    }
    private void doSearch(String query) {
        // Filter the list of restaurants by the user's search query
        ArrayList<HashMap<String,String>> filteredList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].toLowerCase().contains(query.toLowerCase())) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("imgRes", Integer.toString(restaurants[i]));
                hashMap.put("txtResName", restaurantsNames[i]);
                hashMap.put("cities", cities[i]);
                filteredList.add(hashMap);
            }
        }

        // Update the adapter with the filtered list of restaurants
        adapter = new SimpleAdapter(User_SearchRestaurant.this, filteredList,
                R.layout.list_item_restaurant, new String[]{"imgRes", "txtResName","cities"},
                new int[]{R.id.imgRes, R.id.txtResName, R.id.txtCityName});
        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        // Set up a listener for the search bar
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform the search and update the list view
                doSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list view as the user types
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

}