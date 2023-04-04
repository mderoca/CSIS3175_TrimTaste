package com.example.trimtaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class User_RestaurantAdapter extends RecyclerView.Adapter {
    Integer[] mData;
    String[] animals;
    LayoutInflater mInflater;
    ItemClickListener itemClickListener;

    public User_RestaurantAdapter(Context context, Integer[] data, String[] anim){
        mData = data;
        animals = anim;
        mInflater = LayoutInflater.from(context);
    }

    Integer getItem(int id){
        return mData[id];
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).imageView.setImageResource(mData[position]);
        ((ViewHolder)holder).textView.setText(animals[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    void setClickListener(ItemClickListener mItemClickListener){
        itemClickListener = mItemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    //inner class
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgSmall);
            textView = itemView.findViewById(R.id.txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClickListener!= null)
                itemClickListener.onItemClick(view,getAbsoluteAdapterPosition());
        }
    }
}
