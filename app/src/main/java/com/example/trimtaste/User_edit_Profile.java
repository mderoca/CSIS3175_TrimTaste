package com.example.trimtaste;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

        // Retrieving the value using its keys the file name
        //must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        String username = sh.getString("username", "");
        String streetAddress = sh.getString("streetAddress", "");
        String city = sh.getString("city", "");
       // String province = sh.getString("province", "");
        String postalCode = sh.getString("postalCode", "");
        String cell = sh.getString("username", "");
        String email = sh.getString("username", "");
        String pass = sh.getString("username", "");
        String repass = sh.getString("username", "");

        //set the text as the same as user info using shared pref
        editProfileName.setText(username);
        editProfileAddress.setText(streetAddress);
        editProfileCity.setText(city);

        editProfilePostalCode.setText(postalCode);
        editPhone.setText(cell);
        editProfileEmail.setText(email);
        editProfilePassword.setText(pass);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int provincePos = spnProvince.getSelectedItemPosition();
               databaseHelper.populateUserProfileData(username, editProfileName, editProfileAddress,
                       editProfileCity, editProfilePostalCode, editPhone,
                       editProfileEmail, editProfilePassword );
            }
        });
    }
}