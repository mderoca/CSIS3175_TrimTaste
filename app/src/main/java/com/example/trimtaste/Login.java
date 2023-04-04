package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    String[] res1Food ={"Seafood Pizza \n $10.00", "Cajun Chicken Burger \n $12.00", "Stir-fry spaghetti \n $15.00", "Chicken&Celery \n $10.00", "Pesto Pasta \n $20.00"};
    String[] res2Food ={"Pho Bistro Soup \n $10.00", "Beef Balls Soup \n $12.00", "Pork Vermicelli \n $15.00", "Chicken Vermicelli \n $10.00", "Pork Rice Platter \n $20.00"};
    String[] res3Food ={"Hummus and Bread \n $10.00", "Greek salad \n $12.00", "Falafel wrap \n $15.00", "Grilled fish \n $10.00", "Shakshuka \n $20.00"};
    String[] res4Food ={"Farmhouse Burger \n $10.00", "Roasted Chicken \n $12.00", "Chicken Burger \n $15.00", "Creamed Corn \n $10.00", "Apple Pie \n $20.00"};
    String[] res5Food ={"Truffle Fries \n $10.00", "Roasted Salmon \n $12.00", "Classic Cheeseburger \n $15.00", "Ravioli \n $10.00", "Rice platter \n $20.00"};
    String[] res6Food ={"Margherita \n $10.00", "Hawaiian  \n $12.00", "Meat lovers \n $15.00", "Four Cheese \n $10.00", "Pepperoni \n $20.00"};
    String[] res7Food ={"Grilled salmon \n $10.00", "Fish and chips \n $12.00", "Crab cakes \n $15.00", "Baked cod \n $10.00", "Fish kebabs \n $20.00"};
    String[] res8Food ={"Eggs Benedict \n $10.00", "French toast \n $12.00", "Pancakes \n $15.00", "Breakfast burrito \n $10.00", "Omelette \n $20.00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        EditText username = findViewById(R.id.edTxtUsername);
        EditText password = findViewById(R.id.edTxtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnToSignup = findViewById(R.id.btnToSignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                // Call the displayUserProfile method to retrieve the user's data
                boolean userFound = databaseHelper.displayUserProfile(user);

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                String userType = databaseHelper.getUserType(user, pass);
                Boolean checkUserPass = databaseHelper.checkUsernamePassword(user, pass);

                Log.d("Login", "currUsername: " + user);
                Log.d("Login", "currPass: " + pass);


                if(user.equals("")||pass.equals("")){
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //if user is in database
                    if (checkUserPass) {
                        //check if user is Admin or Customer
                        if (userType.trim().equals("Admin")) {
                            Toast.makeText(Login.this, "Sign-in Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Admin_MainActivity.class));
                        } else if (userType.trim().equals("Customer")) {
                            Toast.makeText(Login.this, "Sign-in Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, User_MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Invalid login credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(Login.this, "User is not in our database, Please register for an account!", Toast.LENGTH_SHORT).show();
                    }
                }
                // Check if the user's data was found and update the UI accordingly
                if (userFound) {
                    // Update the UI with the user's data
                    myEdit.putString("username", databaseHelper.getUsername());
                    myEdit.putString("streetAddress", databaseHelper.getStreet());
                    myEdit.putString("city", databaseHelper.getcity());
                    myEdit.putString("province", databaseHelper.getProvince());
                    myEdit.putString("postalCode", databaseHelper.getPostalCode());
                    myEdit.putString("cell", databaseHelper.getPhoneNumber());
                    myEdit.putString("email", databaseHelper.getEmail());
                    myEdit.putString("pass", databaseHelper.getPassword());

                    myEdit.commit();

                } else {
                    // Show an error message or take other actions if the user's data was not found
                    Toast.makeText(Login.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
                databaseHelper.addMenuItems(res1Food, 1);
                databaseHelper.addMenuItems(res2Food, 2);
                databaseHelper.addMenuItems(res3Food, 3);
                databaseHelper.addMenuItems(res4Food, 4);
                databaseHelper.addMenuItems(res5Food, 5);
                databaseHelper.addMenuItems(res6Food, 6);
                databaseHelper.addMenuItems(res7Food, 7);
                databaseHelper.addMenuItems(res8Food, 8);
            }
        });



        btnToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database helper when the activity is destroyed
        databaseHelper.close();
    }
    }