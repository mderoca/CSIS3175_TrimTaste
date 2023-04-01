package com.example.trimtaste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{
    final static String DATABASE_NAME = "FoodApp.db";
    final static int DATABASE_VERSION = 4;


    //-------------------User Table --------------------

    final static String TABLE1_NAME = "USER_TABLE";
    final static String T1COL_1 = "UserId";
    final static String T1COL_2 = "Address";
    final static String T1COL_3 = "Email";
    final static String T1COL_4 = "phoneNumber";
    final static String T1COL_5 = "Username";
    final static String T1COL_6 = "Password";
    final static String T1COL_7 = "UserRole";
    //-------------------------------------------------------------------

    //-----------------Restaurnat Table ----------------------------

    final static String TABLE2_NAME = "RESTAURANT";
    final static String T2COL_1 = "RestaurantID";
    final static String T2COL_2 = "RestaurantName";
    final static String T2COL_3 = "RestaurantAddress";
    final static String T2COL_4 = "RestaurantCity";
    final static String T2COL_5 = "MenuId";
    //    CREATE TABLE Restaurant (
//            RestaurantId INT PRIMARY KEY,
//            RestaurantName VARCHAR(50),
//    RestaurantAddress VARCHAR(100),
//    RestaurantCity VARCHAR(50),
//    MenuId INT,
//    FOREIGN KEY (MenuId) REFERENCES Menu(MenuId)
//            );
    //-------------------------------------------------------------------

//----------------Menu Table --------------------------------

    final static String TABLE3_NAME = "MENU";
    final static String T3COL_1 = "MenuId";
    final static String T3COL_2 = "MenuItemName";
    final static String T3COL_3 = "MenuItemPrice";
    final static String T3COL_4 = "RestaurantID";
    //CREATE TABLE Menu (
//            MenuId INT PRIMARY KEY,
//            MenuItemName VARCHAR(50),
//    MenuItemPrice DECIMAL(10, 2),
//    RestaurantID INT,
//    FOREIGN KEY (RestaurantID) REFERENCES Restaurant(RestaurantID)
//            );
    //-------------------------------------------------------------------

    //-----------------------ORDERS TABLE---------------------------

    final static String TABLE4_NAME = "ORDERS";
    final static String T4COL_1 = "OrderId";
    final static String T4COL_2 = "UserId";
    final static String T4COL_3 = "RestaurantId";
    final static String T4COL_4 = "MenuID";
    final static String T4COL_5 = "OrderDate";
    final static String T4COL_6 = "OpPickup";
    //    CREATE TABLE Orders (
//            OrderId INT PRIMARY KEY,
//            UserId INT,
//            RestaurantId INT,
//            MenuID INT,
//            OrderDate DATETIME,
//            OpPickup VARCHAR(50),
//    FOREIGN KEY (UserId) REFERENCES User(UserId),
//    FOREIGN KEY (RestaurantId) REFERENCES Restaurant(RestaurantId),
//    FOREIGN KEY (MenuId) REFERENCES Menu(MenuId)
//            );
    //---------------------------------------------------------------------

    //--------------------_BILL TALBE-------------------------------------

    final static String TABLE5_NAME = "_BILL";
    final static String T5COL_1 = "BillId";
    final static String T5COL_2 = "OrderId";
    final static String T5COL_3 = "TotalAmount";
    final static String T5COL_4 = "BillDate";
    //    CREATE TABLE Bill (
//            BillId INT PRIMARY KEY,
//            OrderId INT,
//            TotalAmount DECIMAL(10, 2),
//    BillDate DATETIME,
//    FOREIGN KEY (OrderId) REFERENCES Orders(OrderId)
//            );
    //---------------------------------------------------------------------
    //
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

        //Creating Restaurant
        String restaurantTable = "CREATE TABLE " + TABLE2_NAME + " (" + T2COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + T2COL_2 + " TEXT, " + T2COL_3 + " TEXT, " +
                T2COL_4 + " TEXT, " + T2COL_5 + " INTEGER, " +
                "FOREIGN KEY(" + T2COL_5 + ") REFERENCES MENU(MenuId)" +
                ");";

        //Creating Menu
        String menuTable = "CREATE TABLE " + TABLE3_NAME + " (" + T3COL_1 + " INTEGER PRIMARY KEY, " +
                T3COL_2 + " TEXT, " + T3COL_3 + " DECIMAL(10, 2), " + T3COL_4 + " INT, " +
                "FOREIGN KEY (" + T3COL_4 + ") REFERENCES " + TABLE2_NAME + "(" + T2COL_1 + ")" +
                ");";

        //Creating Order
        String ordersTable = "CREATE TABLE " + TABLE4_NAME + " (" + T4COL_1 +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + T4COL_2 + " INTEGER, " + T4COL_3 + " INTEGER, " +
                T4COL_4 + " INTEGER, " + T4COL_5 + " DATETIME, " + T4COL_6 + " TEXT, " +
                "FOREIGN KEY(" + T4COL_2 + ") REFERENCES " + TABLE1_NAME + "(" + T1COL_1 + "), " +
                "FOREIGN KEY(" + T4COL_3 + ") REFERENCES " + TABLE2_NAME + "(" + T2COL_1 + "), " +
                "FOREIGN KEY(" + T4COL_4 + ") REFERENCES " + TABLE3_NAME + "(" + T3COL_1 + ")" +
                ");";

        //Creating Bill
        String billTable = "CREATE TABLE " + TABLE5_NAME + " (" +
                T5COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                T5COL_2 + " INTEGER, " +
                T5COL_3 + " DECIMAL(10, 2), " +
                T5COL_4 + " DATETIME, " +
                "FOREIGN KEY (" + T5COL_2 + ") REFERENCES " + TABLE4_NAME + "(" + T4COL_1 + ")" +
                ");";

        db.execSQL(userTable);
        db.execSQL(restaurantTable);
        db.execSQL(menuTable);
        db.execSQL(ordersTable);
        db.execSQL(billTable);
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
    public boolean addRestaurantData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Add restaurant 1
        ContentValues restaurant1 = new ContentValues();
        restaurant1.put(T2COL_2, "Restaurant 1");
        restaurant1.put(T2COL_3, "123 Main St");
        restaurant1.put(T2COL_4, "City 1");
        long restaurant1Id = db.insert(TABLE2_NAME, null, restaurant1);
        if (restaurant1Id == -1) {
            return false;
        }

        // Add restaurant 2
        ContentValues restaurant2 = new ContentValues();
        restaurant2.put(T2COL_2, "Restaurant 2");
        restaurant2.put(T2COL_3, "456 Elm St");
        restaurant2.put(T2COL_4, "City 2");
        long restaurant2Id = db.insert(TABLE2_NAME, null, restaurant2);
        if (restaurant2Id == -1) {
            return false;
        }

        // Add restaurant 3
        ContentValues restaurant3 = new ContentValues();
        restaurant3.put(T2COL_2, "Restaurant 3");
        restaurant3.put(T2COL_3, "789 Oak St");
        restaurant3.put(T2COL_4, "City 3");
        long restaurant3Id = db.insert(TABLE2_NAME, null, restaurant3);
        if (restaurant3Id == -1) {
            return false;
        }

        return true;
    }

    public boolean addMenuItem(String itemName, double itemPrice, int restaurantId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_2, itemName);
        values.put(T3COL_3, itemPrice);
        values.put(T3COL_4, restaurantId);

        long l = db.insert(TABLE3_NAME, null, values);
        if (l > 0)
            return true;
        else
            return false;
    }
    public boolean addOrder(int userId, int restaurantId, int menuId, String orderDate, String OpPickup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL_2, userId);
        values.put(T4COL_3, restaurantId);
        values.put(T4COL_4, menuId);
        values.put(T4COL_5, orderDate);
        values.put(T4COL_6, OpPickup);

        long l = db.insert(TABLE4_NAME, null, values);
        if (l > 0)
            return true;
        else
            return false;
    }

    public boolean addBill(int orderId, double totalAmount, String billDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL_2, orderId);
        values.put(T5COL_3, totalAmount);
        values.put(T5COL_4, billDate);

        long l = db.insert(TABLE5_NAME, null, values);
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

    public boolean updateData(String currUsername, String username, String address, String email, String phoneNumber,
                              String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL_5, username);
        contentValues.put(T1COL_2, address);
        contentValues.put(T1COL_3, email);
        contentValues.put(T1COL_4, phoneNumber);
        contentValues.put(T1COL_6, password);

        int result = db.update(TABLE1_NAME, contentValues, "username=?", new String[]{currUsername});
        return result != -1;
    }

}
