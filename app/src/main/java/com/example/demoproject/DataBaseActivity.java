package com.example.demoproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.demoproject.data.OrderContract.TableEntry;
import com.example.demoproject.data.OrderDbHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DataBaseActivity extends AppCompatActivity {

    private OrderDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        mDbHelper = new OrderDbHelper(this);

    }

//    private void displayDatabaseInfo() {
//        // To access our database, we instantiate our subclass of SQLiteOpenHelper
//        // and pass the context, which is the current activity.
//        OrderDbHelper mDbHelper = new OrderDbHelper(this);
//
//        // Create and/or open a database to read from it
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        // Perform this raw SQL query "SELECT * FROM pets"
//        // to get a Cursor that contains all rows from the pets table.
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TableEntry.TABLE_NAME, null);
//        try {
//            // Display the number of rows in the Cursor (which reflects the number of rows in the
//            // pets table in the database).
//            TextView displayView = (TextView) findViewById(R.id.text_view_pet);
//            displayView.setText("Number of rows in pets database table: " + cursor.getCount());
//        } finally {
//            // Always close the cursor when you're done reading from it. This releases all its
//            // resources and makes it invalid.
//            cursor.close();
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TableEntry._ID,
                TableEntry.COLUMN_PORTFOLIO_SYMBOLE,
                TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE,
                TableEntry.COLUMN_PORTFOLIO_QUANTITY,
                TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE,
                TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE,
                TableEntry.COLUMN_PORTFOLIO_MONEY_CHANGE,
                TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE,
        };
        Cursor cursor = db.query(
                TableEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {

            displayView.setText("The Pets Table Contains " + cursor.getCount() + "STOCKS\n\n");
            displayView.append(TableEntry._ID + " - " +
                    TableEntry.COLUMN_PORTFOLIO_SYMBOLE + " - " +
                    TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE + " - " +
                    TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE + " - " +
                    TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE + " - " +
                    TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE + " - " +
                    TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(TableEntry._ID);
            int symboleColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_SYMBOLE);
            int ltpColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE);
            int qtyColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_QUANTITY);
            int priceChangeColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE);
            int percentChangeColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE);
            int moneyChangeColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_MONEY_CHANGE);
            int orderTypeColumnIndex = cursor.getColumnIndex(TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentSymbole = cursor.getString(symboleColumnIndex);
                float currentLTP = cursor.getInt(ltpColumnIndex);
                float currentQty = cursor.getInt(qtyColumnIndex);
                float currentpricechange = cursor.getInt(priceChangeColumnIndex);
                float currentpercentChange = cursor.getInt(percentChangeColumnIndex);
                float currentmoneyChange = cursor.getInt(moneyChangeColumnIndex);
                int currentOrdertype = cursor.getInt(orderTypeColumnIndex);

                displayView.append("\n" + currentId + " - " +
                        currentSymbole + " - " +
                        currentLTP + " - " +
                        currentQty + " - " +
                        currentpricechange + " - " +
                        currentpercentChange + " - " +
                        currentmoneyChange + " - " +
                        currentOrdertype + " - "
                );

            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertPet() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableEntry.COLUMN_PORTFOLIO_SYMBOLE, "toto");
        values.put(TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE, 520);
        values.put(TableEntry.COLUMN_PORTFOLIO_QUANTITY, 65);
        values.put(TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE, 5);
        values.put(TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE, 5.5);
        values.put(TableEntry.COLUMN_PORTFOLIO_MONEY_CHANGE, 0.25);
        values.put(TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE, TableEntry.ORDER_TYPE_BUY);

        long newRoId = db.insert(TableEntry.TABLE_NAME, null, values);

        Log.v("CatalogActivity", "New Row Id" + newRoId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}