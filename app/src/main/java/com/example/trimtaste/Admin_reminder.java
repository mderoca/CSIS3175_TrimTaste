package com.example.trimtaste;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Admin_reminder extends Activity {

    // Declare variables to hold customer information and order details
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String orderNumber;
    private String orderDate;
    private String orderTotal;
    private String orderItems;
    private String orderDetails;
    private String pickupTime;

    // Declare UI elements
    private TextView txtRName;
    private TextView txtDate;
    private TextView txtTPrice;
    private TextView txtItem;
    private TextView txtDetail;
    private TextView txtPickup;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reminder);

        // Get references to UI elements
        txtRName = (TextView) findViewById(R.id.txtRName);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTPrice = (TextView) findViewById(R.id.txtTPrice);
        txtItem = (TextView) findViewById(R.id.txtItem);
        txtDetail = (TextView) findViewById(R.id.txtDetail);
        txtPickup = (TextView) findViewById(R.id.txtPickup);
        btnSend = (Button) findViewById(R.id.btnSend);

        // Set text for UI elements using customer information and order details
        txtRName.setText(orderNumber);
        txtDate.setText(orderDate);
        txtTPrice.setText(orderTotal);
        txtItem.setText(orderItems);
        txtDetail.setText(orderDetails);
        txtPickup.setText(pickupTime);

        // Set click listener for send button
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use an intent to open the default email app and pre-populate an email to the customer
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + customerEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reminder: Your food is ready for pickup/delivery");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi " + customerName + ",\n\nYour order (#" + orderNumber + ") is ready for pickup/delivery at "
                        + pickupTime + ".\n\nOrder details:\n" + orderItems + "\n\nTotal: " + orderTotal + "\n\n" + orderDetails + "\n\nThanks for choosing our restaurant!\n\nBest,\nThe Restaurant Team");
                startActivity(intent);
            }
        });
    }
//    this implementation uses an intent to open the default email app on the customer's device and
//    pre-populate an email with the necessary information. You may need to modify this implementation to fit your specific use case, depending on how you want to send the reminder to your customers.
}
