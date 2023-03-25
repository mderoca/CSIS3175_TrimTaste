package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

                String userType = databaseHelper.getUserType(user, pass);
                Boolean checkUserPass = databaseHelper.checkUsernamePassword(user, pass);

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