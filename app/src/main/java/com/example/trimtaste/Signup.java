package com.example.trimtaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    private EditText username, streetAddress, city, postalCode,
            phone, email, password, repassword;
    private Spinner province, userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseHelper = new DatabaseHelper(this);

        username = findViewById(R.id.edTxtUNameReg);
        streetAddress = findViewById(R.id.edTxtStAddress);
        city = findViewById(R.id.edTxtCity);
        province = findViewById(R.id.spnProvince);
        postalCode = findViewById(R.id.edTxtPostal);
        phone = findViewById(R.id.edTxtPhone);
        email = findViewById(R.id.edTxtEmail);
        password = findViewById(R.id.edTxtPassReg);
        repassword = findViewById(R.id.edTxtRePassword);
        userRole = findViewById(R.id.spnRole);


        Button btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                //***** GETTEXT FOR ALL EDIT TEXT *******
                String user = username.getText().toString();
                String addr = streetAddress.getText().toString();
                String cit = city.getText().toString();
                String prov = province.getSelectedItem().toString();
                String postCode = postalCode.getText().toString();
                String cell = phone.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String uRole = userRole.getSelectedItem().toString();

//                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                //SharedPreferences sharedPrefUser = getSharedPreferences("SharedPrefUser", MODE_PRIVATE);
//
//                // Creating an Editor object to edit(write to the file)
//                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                //SharedPreferences.Editor editUser = sharedPrefUser.edit();
//
//                //for user
//                //editUser.putString("username", user);
//
                // Storing the key and its value as the data fetched from edittext
                //myEdit.putString("username", user);
//                myEdit.putString("streetAddress", addr);
//                myEdit.putString("city", cit);
//               // myEdit.putString("province", prov);
//                myEdit.putString("postalCode", postCode);
//                myEdit.putString("cell", cell);
//                myEdit.putString("email", em);
//                myEdit.putString("pass", pass);
//                myEdit.putString("repass", repass);
//                //myEdit.putString("userRole", uRole);

//                myEdit.commit();


                if (user.equals("") || addr.equals("") || cit.equals("") ||
                        postCode.equals("") || cell.equals("") || em.equals("") ||
                        pass.equals("") || repass.equals("")) {

                    Toast.makeText(Signup.this, "Please enter all the fields!", Toast.LENGTH_LONG).show();
                } else {
                    if (pass.equals(repass)) {
                        boolean checkUser = databaseHelper.checkUsername(username.getText().toString());
                        //if user does not exist in database, create new user
                        if (!checkUser) {
                            isInserted = databaseHelper.addData(
                                    addr, cit, prov, postCode,
                                    em,
                                    cell, user,
                                    pass, uRole + " ");

                            if (isInserted) {
                                Toast.makeText(Signup.this, "Registered successfully!", Toast.LENGTH_LONG).show();
                                username.setText("");
                                streetAddress.setText("");
                                city.setText("");
                                postalCode.setText("");
                                phone.setText("");
                                email.setText("");
                                password.setText("");
                                repassword.setText("");
                                province.setSelection(0);
                                userRole.setSelection(0);

                            } else {
                                Toast.makeText(Signup.this, "Registration failed.", Toast.LENGTH_LONG).show();
                            }
                        }
                        //if user already exists
                        else {
                            Toast.makeText(Signup.this, "User already exists! Please sign in.", Toast.LENGTH_LONG).show();
                        }
                    }
                    //if passwords don't match
                    else {
                        Toast.makeText(Signup.this, "Passwords not matching! Please try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}