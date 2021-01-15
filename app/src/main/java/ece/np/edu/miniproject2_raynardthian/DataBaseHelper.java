package ece.np.edu.miniproject2_raynardthian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAMETEXT";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_CUSTOMER_ADDRESS = "CUSTOMER_ADDRESS";
    public static final String ADMIN_FOOD_TABLE = "ADMIN_FOOD_TABLE";
    public static final String ADMIN_ID = "ADMIN_ID";
    public static final String ADMIN_DESCRIPTION = "ADMIN_DESCRIPTION";
    public static final String ADMIN_COST = "ADMIN_COST";
    public static final String ADMIN_FOOD_PICTURE = "ADMIN_FOOD_PICTURE";
    public static final String ADMIN_FOOD_NAME = "ADMIN_FOOD_NAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customers.db", null, 5);
    }

    // This is called the first time the database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT, "
                + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_CUSTOMER_ADDRESS + " TEXT)";
        String createInsertAdminFood = "CREATE TABLE " + ADMIN_FOOD_TABLE + " (" + ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_FOOD_NAME + " TEXT, " + ADMIN_DESCRIPTION + " TEXT, "
                + ADMIN_COST + " FLOAT, " + ADMIN_FOOD_PICTURE + " TEXT)";
        db.execSQL(createTableStatement);
        db.execSQL(createInsertAdminFood);
    }

    // This is called if the database version number changes. Prevents previous user apps from breaking when you change the data base design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_FOOD_TABLE);
    }

    // Adding one customer into the database
    public boolean addOne(Customer customerList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerList.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerList.getAge());
        cv.put(COLUMN_CUSTOMER_PASSWORD, customerList.getPassword());
        cv.put(COLUMN_CUSTOMER_ADDRESS, customerList.getAddress());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    // Adding one fooditem into the database
    public boolean addOneFoodItem(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ADMIN_FOOD_NAME, foodItem.getFoodName());
        cv.put(ADMIN_DESCRIPTION, foodItem.getFoodDescription());
        cv.put(ADMIN_COST, foodItem.getFoodCost());
        cv.put(ADMIN_FOOD_PICTURE, foodItem.getFoodPicture());

        long insert = db.insert(ADMIN_FOOD_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    // This is to delete the customer in the customer database
    public boolean deleteOne(Customer customer) {
        // find customer in the database, if it is found, delete it and return true
        // if it is not found, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customer.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    // this is to delete the food item in the fooditem database
    public boolean deleteOneFoodItem(FoodItem foodItem) {
        // find customer in the database, if it is found, delete it and return true
        // if it is not found, return false
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + ADMIN_FOOD_TABLE + " WHERE " + ADMIN_ID + " = " + foodItem.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    // This is to update password to the database
    public boolean updateOne(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_PASSWORD, password);
        String[] s = {name};
        db.update(CUSTOMER_TABLE, cv, COLUMN_CUSTOMER_NAME + "=?", s);
        return true;
    }

    public boolean updateAge(String name , int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_AGE, age);
        String[] s = {name};
        db.update(CUSTOMER_TABLE, cv, COLUMN_CUSTOMER_NAME + "=?", s);
        return true;
    }

    public boolean updateAddress(String name, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_ADDRESS, address);
        String[] s = {name};
        db.update(CUSTOMER_TABLE, cv, COLUMN_CUSTOMER_NAME + "=?", s);
        return true;
    }

    // This is to get everyone in the datasheet
    public List<Customer> getEveryone() {
        List<Customer> returnList = new ArrayList<>();
        // get data from the data base
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return list
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerPassword = cursor.getString(2);
                int customerAge = cursor.getInt(3);
                String customerAddress = cursor.getString(4);

                Customer newCustomer = new Customer(customerID, customerName, customerPassword, customerAge, customerAddress);
                returnList.add(newCustomer);
            } while (cursor.moveToNext());
        } else {
            // Failure. do not add anything to the list

        }
        // close the cursor and database when done
        cursor.close();
        db.close();
        return returnList;
    }
    // Trying to use the cursor to select the data of the person.
    public Cursor getPersonDatas(String name) {
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_NAME + " = '" + name+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
    // Trying to use the cursor to select the data of the fooditem.
    public Cursor getFoodItemData(String foodItem){
        String queryString = "SELECT * FROM " + ADMIN_FOOD_TABLE + " WHERE " + ADMIN_FOOD_NAME + " = '" + foodItem+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
    // This is to get all the food item in the data sheet
    public List<FoodItem> getAllFoodItem() {
        List<FoodItem> returnList = new ArrayList<>();
        // get data from the data base
        String queryString = "SELECT * FROM " + ADMIN_FOOD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new Food Item objects. Put them into the return list
            do {
                int FoodID = cursor.getInt(0);
                String FoodName = cursor.getString(1);
                String FoodDescription = cursor.getString(2);
                float FoodCost = cursor.getFloat(3);
                String PicturePath = cursor.getString(4);

                FoodItem newfooditem = new FoodItem(FoodID, FoodName, FoodDescription, FoodCost, PicturePath);
                returnList.add(newfooditem);
            } while (cursor.moveToNext());
        } else {
            // Failure. do not add anything to the list

        }
        // close the cursor and database when done
        cursor.close();
        db.close();
        return returnList;
    }

    public Cursor getFoodNames() {
//        List<String> returnList = new ArrayList<>();
        // get data from the data base
        String queryString = "SELECT * FROM " + ADMIN_FOOD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        return cursor;
//        return returnList;
    }


    public Cursor getAllData() {
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    public Cursor getAllFoodData() {
        String queryString = "SELECT * FROM " + ADMIN_FOOD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
