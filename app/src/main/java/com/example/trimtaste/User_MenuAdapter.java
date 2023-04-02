package com.example.trimtaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class User_MenuAdapter extends RecyclerView.Adapter<User_MenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<MenuItem> menuItems;
    public class MenuViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public TextView itemPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
        }
    }

    public User_MenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public User_MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.itemName.setText(User_MenuItem.getName());
        holder.itemPrice.setText("$" + String.format("%.2f", User_MenuItem.getPrice()));
    }


    @Override
    public int getItemCount() {
        return menuItems.size();
    }

}
