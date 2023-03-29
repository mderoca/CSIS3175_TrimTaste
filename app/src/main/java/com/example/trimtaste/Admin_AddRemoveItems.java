package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin_AddRemoveItems extends AppCompatActivity {
    String[] food = {"Seafood Pizza \n $10.00", "Burger \n $12.00", "Stir-fry spaghetti \n $15.00", "Chicken&Celery \n $10.00", "pesto pasta \n $20.00"};
    int[] images = {R.drawable.seafood_pizza, R.drawable.mock_res1_item1, R.drawable.mock_res1_item2, R.drawable.mock_res1_item3, R.drawable.mock_res1_item4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_remove_food_items);
        ArrayList<HashMap<String, String>> aList =
                new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < food.length; i++) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("txt", food[i]);
            hashMap.put("images", Integer.toString(images[i]));
            aList.add(hashMap);
        }
        String[] from = {"images", "txt"};
        int[] to = {R.id.image, R.id.txtFoodItem};

        SimpleAdapter adapter = new SimpleAdapter(Admin_AddRemoveItems.this,
                aList, R.layout.listview_layout, from, to);

        ListView listView = findViewById(R.id._dynamic);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.dominos.ca/?gclid=CjwKCAjw_MqgBhAGEiwAnYOAemmCfgrWvNvdvCRtfkOpbUS2KIm11Zb8uWdD8iJNgMj0kZBNJy8SdxoCP_MQAvD_BwE&gclsrc=aw.ds")));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;

                }

            }
        });
    }
}