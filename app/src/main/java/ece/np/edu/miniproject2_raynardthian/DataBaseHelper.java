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

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customers.db", null, 1);
    }

    // This is called the first time the database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT, "
                + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_CUSTOMER_ADDRESS + " TEXT)";
        db.execSQL(createTableStatement);
    }

    // This is called if the database version number changes. Prevents previous user apps from breaking when you change the data base design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

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

    public boolean updateOne(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_CUSTOMER_NAME,name);
//        cv.put(COLUMN_CUSTOMER_PASSWORD,password);
//        db.update(CUSTOMER_TABLE,cv ,"CUSTOMER_PASSWORD=?" ,"password");
        String queryString = "UPDATE " + CUSTOMER_TABLE + " SET " + COLUMN_CUSTOMER_PASSWORD + " = " + password + " WHERE " + COLUMN_CUSTOMER_NAME + " = " + "" + name + "";

        db.execSQL(queryString);
//        if (cursor.moveToFirst()) {
//            return true;
//        } else {
//            return false;
//        }
        return true;
    }

    public boolean updateToDataBase(Customer customerList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_PASSWORD, customerList.getPassword());
        Cursor cursor = db.rawQuery("SELECT * FROM CUSTOMER_TABLE WHERE CUSTOMER_NAMETEXT=?", new String[]{customerList.getName()});
        if (cursor.getCount() > 0) {
            long insert = db.update("customers", cv, "CUSTOMER_NAMETEXT=?", new String[]{customerList.getName()});
            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
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


    public Cursor getAllData(){
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
