package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AddorRemoveActivity extends AppCompatActivity {
    String[] food = {"Food Item 1 \n $00.00","Food Item 2 \n $00.00","Food Item 3 \n $00.00", "Food Item 4 \n $00.00"};
    int[] images = {R.drawable.seafood_pizza, R.drawable.seafood_pizza, R.drawable.seafood_pizza};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addor_remove);

        ArrayList<HashMap<String,String>> aList =
                new ArrayList<HashMap<String,String>>();

        for(int i=0;i<food.length;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("txt",food[i]);
            hashMap.put("images",Integer.toString(images[i]));
            aList.add(hashMap);
        }
        String[] from = {"images","txt"};
        int[] to = {R.id.image,R.id.txtFoodItem};

        SimpleAdapter adapter = new SimpleAdapter(AddorRemoveActivity.this,
                aList,R.layout.list_item,from,to);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
                switch (position){
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