package com.example.demoproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.demoproject.data.OrderContract.TableEntry;


public class OrderDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Portfolio.db";

    private static final int DATABASE_VERSION = 1 ;

    public OrderDbHelper(Context context)
    {
        super (context ,DATABASE_NAME,null ,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table Portfolio {id INTEGER , symbole TEXT PRIMARY KEY,LTP INTEGER,quantity INTEGER,percentChange INTEGER,MoneyChange INTEGER,orderType INTEGER};
        //create a string that contains the sql statement to create the pets table
        String SQL_CREATE_PET_TABLE = "CREATE TABLE " + TableEntry.TABLE_NAME + "(" + TableEntry._ID +" INTEGER PRIMARY KEY," +
                TableEntry.COLUMN_PORTFOLIO_SYMBOLE +" TEXT NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE +" REAL NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_QUANTITY +" REAL NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE +" REAL NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE +" REAL NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_MONEY_CHANGE +" REAL NOT NULL ,"
                + TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE +" INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_PET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
