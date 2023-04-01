package com.example.trimtaste;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class User_edit_Profile extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profile);

        EditText editProfileName = findViewById(R.id.editProfileName);
        EditText editProfileAddress = findViewById(R.id.editProfileAddress);
        EditText editProfileCity = findViewById(R.id.editProfileCity);
        EditText editProfilePostalCode = findViewById(R.id.editProfilePostalcode);
        Spinner spnProvince = findViewById(R.id.spnProvince);
        EditText editPhone = findViewById(R.id.editTextPhone);
        EditText editProfileEmail = findViewById(R.id.editProfilemail);
        EditText editProfilePassword = findViewById(R.id.editProfilePassword);

        Button btnUpdateProfile = findViewById(R.id.btnUpdateProfile);

        // Get the username of the current user
        //String user = getIntent().getStringExtra("user");

        //user shared pref
//        SharedPreferences sharedPrefUser = getSharedPreferences("SharedPrefUser", MODE_PRIVATE);
//        String user = sharedPrefUser.getString("username", "");

        // Retrieving the value using its keys the file name
        //must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

//
        String user = sh.getString("username", "");
        String streetAddress = sh.getString("streetAddress", "");
        String city = sh.getString("city", "");
        String province = sh.getString("province", "");
        String postalCode = sh.getString("postalCode", "");
        String cell = sh.getString("cell", "");
        String email = sh.getString("email", "");
        String pass = sh.getString("pass", "");
        //String repass = sh.getString("repass", "");

        //set the text as the same as user info using shared pref
        editProfileName.setText(user);
        editProfileAddress.setText(streetAddress);
        editProfileCity.setText(city);
        editProfilePostalCode.setText(postalCode);
        editPhone.setText(cell);
        editProfileEmail.setText(email);
        editProfilePassword.setText(pass);

        for(int i= 0; i < spnProvince.getAdapter().getCount(); i++)
        {
            if(spnProvince.getAdapter().getItem(i).toString().contains(province))
            {
                spnProvince.setSelection(i);
            }
        }


        //**** NEED TO FIGURE OUT HOW TO KEEP CHANGED DATA DISPLAYED. (SHARED PREF MAYBE?) *******
        databaseHelper = new DatabaseHelper(this);


        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //get text *******
                String newUsername = editProfileName.getText().toString();
                String newStreet = editProfileAddress.getText().toString();
                String newCity = editProfileCity.getText().toString();
                String newProvince = spnProvince.getSelectedItem().toString();
                String newPostalCode = editProfilePostalCode.getText().toString();
                String newCell = editPhone.getText().toString();
                String newEmail = editProfileEmail.getText().toString();
                String newPass = editProfilePassword.getText().toString();

                // Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sh.edit();

               boolean isUpdated = databaseHelper.updateData(user, newUsername, newStreet, newCity,
                                        newProvince, newPostalCode, newCell, newEmail, newPass);
                // Storing the key and its value as the data fetched from edittext
                myEdit.putString("username", newUsername);
                myEdit.putString("streetAddress", newStreet);
                myEdit.putString("city", newCity);
                myEdit.putString("province", newProvince);
                myEdit.putString("postalCode", newPostalCode);
                myEdit.putString("cell", newCell);
                myEdit.putString("email", newEmail);
                myEdit.putString("pass", newPass);
                myEdit.commit();

                String newUser = sh.getString("username", "");
                String newStre = sh.getString("streetAddress", "");
                String newCit = sh.getString("city", "");
                String province = sh.getString("province", "");
                String newPcode = sh.getString("postalCode", "");
                String newCel = sh.getString("cell", "");
                String newEm = sh.getString("email", "");
                String newPas = sh.getString("pass", "");
                //String repass = sharedPreferences.getString("repass", "");

                //set the text as the same as user info using shared pref
                editProfileName.setText(newUser);
                editProfileAddress.setText(newStre);
                editProfileCity.setText(newCit);
                editProfilePostalCode.setText(newPcode);
                editPhone.setText(newCel);
                editProfileEmail.setText(newEm);
                editProfilePassword.setText(newPas);

                for(int i= 0; i < spnProvince.getAdapter().getCount(); i++)
                {
                    if(spnProvince.getAdapter().getItem(i).toString().contains(province))
                    {
                        spnProvince.setSelection(i);
                    }
                }

                Log.d("User_edit_Profile", "currUsername: " + user);
                Log.d("User_edit_Profile", "newUsername: " + newUsername);
                //Log.d("User_edit_Profile", "newAddress: " + newAddress);
                Log.d("User_edit_Profile", "newEmail: " + newEmail);
                Log.d("User_edit_Profile", "newCell: " + newCell);
                Log.d("User_edit_Profile", "newPass: " + newPass);

                if(isUpdated){
                   Toast.makeText(User_edit_Profile.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(User_edit_Profile.this, "Profile update failed", Toast.LENGTH_SHORT).show();

               }

            }
        });
    }
}