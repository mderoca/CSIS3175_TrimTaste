package com.example.trimtaste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trimtaste.Order;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false);
        }

        Order order = orders.get(position);

        TextView customerNameTextView = view.findViewById(R.id.customerNameTextView);
        customerNameTextView.setText(order.getCustomerName());

        TextView foodItemTextView = view.findViewById(R.id.foodItemTextView);
        foodItemTextView.setText(order.getFoodItem());

        TextView sizeTextView = view.findViewById(R.id.sizeTextView);
        sizeTextView.setText(order.getSize());

        TextView quantityTextView = view.findViewById(R.id.quantityTextView);
        quantityTextView.setText(String.valueOf(order.getQuantity()));

        return view;
    }
}

