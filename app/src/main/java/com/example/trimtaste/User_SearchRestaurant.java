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

    DatabaseHelper db;
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
        db = new DatabaseHelper(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
//                        boolean isInserted = db.addMenuItems(res1Food, 1);
                        boolean checkMenu = db.checkMenu(1);

                        if(checkMenu){
                            startActivity(new Intent(User_SearchRestaurant.this,User_EachRestaurant.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        if (isInserted) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,User_EachRestaurant.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 1:
//                        boolean isInserted2 = db.addMenuItems(res2Food, 2);

                        boolean checkMenu2 = db.checkMenu(2);

                        if(checkMenu2){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant2.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        if (isInserted2) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant2.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 2:
                        boolean checkMenu3 = db.checkMenu(3);

                        if(checkMenu3){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant3.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted3 = db.addMenuItems(res3Food, 3);
//                        if (isInserted3) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant3.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 3:
                        boolean checkMenu4 = db.checkMenu(4);

                        if(checkMenu4){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant4.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted4 = db.addMenuItems(res4Food, 4);
//                        if (isInserted4) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant4.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 4:
                        boolean checkMenu5 = db.checkMenu(5);

                        if(checkMenu5){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant5.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted5 = db.addMenuItems(res5Food, 5);
//                        if (isInserted5) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant5.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 5:
                        boolean checkMenu6 = db.checkMenu(6);

                        if(checkMenu6){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant6.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted6 = db.addMenuItems(res6Food, 6);
//                        if (isInserted6) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant6.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 6:
                        boolean checkMenu7 = db.checkMenu(7);

                        if(checkMenu7){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant7.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted7 = db.addMenuItems(res7Food, 7);
//                        if (isInserted7) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant7.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
                        break;
                    case 7:
                        boolean checkMenu8 = db.checkMenu(8);

                        if(checkMenu8){
                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant8.class));
                        }else {
                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                        }

//                        boolean isInserted8 = db.addMenuItems(res8Food, 8);
//                        if (isInserted8) {
//                            Toast.makeText(User_SearchRestaurant.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(User_SearchRestaurant.this,Restaurant8.class));
//                        } else {
//                            Toast.makeText(User_SearchRestaurant.this, "Data not inserted", Toast.LENGTH_SHORT).show();
//                        }
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