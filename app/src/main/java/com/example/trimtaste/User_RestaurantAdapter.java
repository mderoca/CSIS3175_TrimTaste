package com.example.trimtaste;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class User_RestaurantAdapter extends RecyclerView.Adapter {
    Integer[] rData;
    String[] restaurants;
    ItemClickListener itemClickListener;


    //Initialize the dataset of the Adapter
    public User_RestaurantAdapter(Integer[] data, String[] res){
        rData = data;
        restaurants = res;
    }

    Integer getItem(int id) {
        return  rData[id];
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_restaurant,viewGroup,false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).imageView.setImageResource(rData[position]);
        ((ViewHolder)holder).textView.setText(restaurants[position]);
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return rData.length;
    }


    void setClickListener(ItemClickListener rItemClickListener) {
        itemClickListener = rItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }


    //inner class
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imgRes);
            textView = itemView.findViewById(R.id.txtResName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener != null){
                itemClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }

}
