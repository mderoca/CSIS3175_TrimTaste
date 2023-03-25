package com.example.trimtaste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{
    final static String DATABASE_NAME = "FoodApp.db";
    final static int DATABASE_VERSION = 3;

    final static String TABLE1_NAME = "USER_TABLE";
    final static String T1COL_1 = "UserId";
    final static String T1COL_2 = "Address";
    final static String T1COL_3 = "Email";
    final static String T1COL_4 = "phoneNumber";
    final static String T1COL_5 = "Username";
    final static String T1COL_6 = "Password";
    final static String T1COL_7 = "UserRole";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating Customer Table
        String userTable = "CREATE TABLE " + TABLE1_NAME + " (" + T1COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + T1COL_2 + " TEXT, " + T1COL_3 + " TEXT, " +
                T1COL_4 + " TEXT, " + T1COL_5 + " TEXT, " + T1COL_6 + " TEXT, " + T1COL_7 +
                " TEXT" + ");";
        db.execSQL(userTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(db);
    }

    public boolean addData(String address, String email, String phoneNum,
                           String username, String password, String userRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, address);
        values.put(T1COL_3, email);
        values.put(T1COL_4, phoneNum);
        values.put(T1COL_5, username);
        values.put(T1COL_6, password);
        values.put(T1COL_7, userRole);

        long l = db.insert(TABLE1_NAME, null, values);
        if (l > 0)
            return true;
        else
            return false;

    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE username = ?",
                new String[]{username});

        //if user exists
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE username = ?",
                new String[]{username});

        //if user and password exists
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserType(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + T1COL_7 + " FROM " + TABLE1_NAME +
                " WHERE " + T1COL_5 + "=? AND " + T1COL_6 + " =? ", new String[]{username, password});

        String userType = "";
        if (cursor.moveToFirst()) {
            userType = cursor.getString(cursor.getColumnIndexOrThrow(T1COL_7));
        }
        Log.d("MyDatabaseHelper", "User type = " + userType);
        return userType;

    }


}
