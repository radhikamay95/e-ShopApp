package com.example.task.ecommerce.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "cartDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "cartTB";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "ProdName";
    private static final String PROD_ID_COL = "ProdID";
    private static final String PRICE_COL = "ProdPrice";
    private static final String IMAGE_COL = "ProdImage";
    private static final String PROD_COUNT = "ProdCount";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROD_ID_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + IMAGE_COL + " TEXT,"
                + PROD_COUNT + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        sqLiteDatabase.execSQL(query);
    }

    public void addPurchasedItems(String prodID, String prodImg, String prodName, String prodPrice, String itemCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROD_ID_COL, prodID);
        values.put(NAME_COL, prodName);
        values.put(PRICE_COL, prodPrice);
        values.put(IMAGE_COL, prodImg);
        values.put(PROD_COUNT, itemCount);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    public void updateProuctiItem(String prodID, String itemCount) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_NAME+" SET  ProdCount = "+"'"+itemCount+"' "+ "WHERE ProdID = "+"'"+prodID+"'");

        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public ArrayList<PurchasedProduct> readPurchasedData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<PurchasedProduct> productArrayList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    // on below line adding the data from cursor to our array list.
                    productArrayList.add(new PurchasedProduct(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)));
                } while (cursor.moveToNext());

            }
        }
        Log.i("stored---", productArrayList.toString());
        cursor.close();
        return productArrayList;
    }

    public void deleteItem(String prodID) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "ProdID=?", new String[]{prodID});
        db.close();
    }
}
