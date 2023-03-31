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

    Integer[] restaurants = {R.drawable.mock_restaurant1, R.drawable.mock_restaurant2, R.drawable.mock_restaurant3, R.drawable.mock_restaurant4};
    String[] restaurantsNames = {"Chef Ron restaurant and bar", "Pho Express Angkor Noodle House", "Mediterranean Grill", "Tractor Foods"};

//    User_RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_restaurant);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.txtFindARes);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
//            doMySearch(query);
        }

        ArrayList<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0; i<restaurantsNames.length; i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("imgRes",Integer.toString(restaurants[i]));
            hashMap.put("txtResName",restaurantsNames[i]);
            aList.add(hashMap);
        }

        String[] from = {"imgRes","txtResName"};
        int[] to = {R.id.imgRes,R.id.txtResName};

        SimpleAdapter adapter = new SimpleAdapter(User_SearchRestaurant.this,
                aList,R.layout.list_item_restaurant,from,to);

        ListView listView = findViewById(R.id.listViewRes);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        return true;
    }
}