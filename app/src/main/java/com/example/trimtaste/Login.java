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
//                Log.d("Login", "newUsername: " + newUsername);
//                Log.d("Login", "newAddress: " + newAddress);
//                Log.d("Login", "newEmail: " + newEmail);
//                Log.d("Login", "newCell: " + newCell);
//                Log.d("Login", "newPass: " + newPass);

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