package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditOrderActivity extends AppCompatActivity {

    private EditText customerNameEditText, foodItemEditText, sizeEditText, quantityEditText;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        // Get the selected order from the intent
        order = (Order) getIntent().getSerializableExtra("order");

        // Initialize the UI elements
        customerNameEditText = findViewById(R.id.customerNameEditText);
        foodItemEditText = findViewById(R.id.foodItemEditText);
        sizeEditText = findViewById(R.id.sizeEditText);
        quantityEditText = findViewById(R.id.quantityEditText);

        // Set the text of the UI elements to the values of the selected order
        customerNameEditText.setText(order.getCustomerName());
        foodItemEditText.setText(order.getFoodItem());
        sizeEditText.setText(order.getSize());
        quantityEditText.setText(String.valueOf(order.getQuantity()));

        // Set the click listener for the save button
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the values of the selected order with the new values
                order.setCustomerName(customerNameEditText.getText().toString());
                order.setFoodItem(foodItemEditText.getText().toString());
                order.setSize(sizeEditText.getText().toString());
                order.setQuantity(Integer.parseInt(quantityEditText.getText().toString()));

                // TODO: Save the updated order to the database or some other storage mechanism

                // Finish the activity and return to the previous screen
                finish();
            }
        });

        // Set the click listener for the cancel button
        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity without saving any changes
                finish();
            }
        });
    }
}

