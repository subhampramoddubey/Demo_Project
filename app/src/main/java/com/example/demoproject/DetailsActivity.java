package com.example.demoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoproject.data.OrderContract.TableEntry;
import com.example.demoproject.data.OrderDbHelper;

public class DetailsActivity extends AppCompatActivity {

    private  TextView mTickerDisplay;
    private  TextView mCompanyNameDisplay;
    private  TextView mLTPDisplay;
    private  TextView mChangeInPriceDisplay;
    private  TextView mPercentChangeDisplay;
    private  TextView mTodayOpenDisplay;
    private  TextView mTodayHighDisplay;
    private  TextView mTodayLowDisplay;
    private  TextView mTodayVolumeDisplay;
    private  TextView mLastDayCloseDisplay;
    private  EditText mQuantity;
    private int mOrderType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mCompanyNameDisplay = (TextView) findViewById(R.id.company_name_detail_layout);
        mTickerDisplay = (TextView) findViewById(R.id.Ticker_detail_layout);
        mLTPDisplay = (TextView) findViewById(R.id.last_price_detail_layout);
        mChangeInPriceDisplay = (TextView) findViewById(R.id.change_in_price_detail_layout);
        mPercentChangeDisplay = (TextView) findViewById(R.id.percent_change_detail_layout);
        mTodayOpenDisplay = (TextView) findViewById(R.id.today_open_detail_view);
        mTodayHighDisplay = (TextView) findViewById(R.id.today_high_detail_view);
        mTodayLowDisplay = (TextView) findViewById(R.id.today_low_detail_view);
        mTodayVolumeDisplay = (TextView) findViewById(R.id.today_Volume_detail_view);
        mLastDayCloseDisplay = (TextView) findViewById(R.id.lastDay_close_detail_view);
        mQuantity = (EditText) findViewById(R.id.quantity);


        Intent intent = getIntent();

        if (intent !=null) {

            if (intent.hasExtra("A2")&& intent.hasExtra("A3")&& intent.hasExtra("A4")&& intent.hasExtra("A5")
                    && intent.hasExtra("A6")&& intent.hasExtra("A7")&& intent.hasExtra("A8")&& intent.hasExtra("A9")&& intent.hasExtra("A10"))
            {
                mCompanyNameDisplay.setText(intent.getStringExtra("A1"));
                mTickerDisplay.setText(intent.getStringExtra("A2"));
                mLTPDisplay.setText(intent.getStringExtra("A3"));
                mChangeInPriceDisplay.setText(intent.getStringExtra("A4"));
                mPercentChangeDisplay.setText(intent.getStringExtra("A5"));
                mTodayOpenDisplay.setText(intent.getStringExtra("A6"));
                mTodayHighDisplay.setText(intent.getStringExtra("A7"));
                mTodayLowDisplay.setText(intent.getStringExtra("A8"));
                mTodayVolumeDisplay.setText(intent.getStringExtra("A9"));
                mLastDayCloseDisplay.setText(intent.getStringExtra("A10"));

            }
        }

        Button buyButton = (Button) findViewById(R.id.buy_button);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderType = 1;
                insertPet();
            }
        });

        Button sellButton = (Button) findViewById(R.id.sell_button);
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrderType = -1;
                insertPet();
            }
        });

    }

    private void insertPet() {

        String symbole = mTickerDisplay.getText().toString().trim();
        String tradedPriceString = mLTPDisplay.getText().toString().trim();
        float tradedPrice = Float.parseFloat(tradedPriceString);
        String quantityString = mQuantity.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);

        OrderDbHelper mDbHelper = new OrderDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TableEntry.COLUMN_PORTFOLIO_SYMBOLE , symbole);
        values.put(TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE , tradedPrice);
        values.put(TableEntry.COLUMN_PORTFOLIO_QUANTITY , quantity);
        values.put(TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE , mOrderType );

        values.put(TableEntry.COLUMN_PORTFOLIO_PRICE_CHANGE, 5);
        values.put(TableEntry.COLUMN_PORTFOLIO_PERCENT_CHANGE, 5.5);
        values.put(TableEntry.COLUMN_PORTFOLIO_MONEY_CHANGE, 0.25);

        long newRoId = db.insert(TableEntry.TABLE_NAME, null, values);

        Log.v("CatalogActivity", "New Row Id" + newRoId);

    }







//    private void insertPet()
//    {
//        String symbole = mTickerDisplay.getText().toString().trim();
//        String tradedPriceString = mLTPDisplay.getText().toString().trim();
//        float tradedPrice = Float.parseFloat(tradedPriceString);
//        String quantityString = mQuantity.getText().toString().trim();
//        int quantity = Integer.parseInt(quantityString);
//
//
//        OrderDbHelper mDbHelper = new OrderDbHelper(this);
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(TableEntry.COLUMN_PORTFOLIO_SYMBOLE , symbole);
//        values.put(TableEntry.COLUMN_PORTFOLIO_TRADED_PRICE , tradedPrice);
//        values.put(TableEntry.COLUMN_PORTFOLIO_QUANTITY , quantity);
//        values.put(TableEntry.COLUMN_PORTFOLIO_ORDER_TYPE , mOrderType );
//
//        long newRoId =db.insert(TableEntry.TABLE_NAME , null ,values);
//
//        if (newRoId==-1)
//        {
//            Toast.makeText(this,"error",Toast.LENGTH_SHORT);
//        }else {
//            Toast.makeText(this,"Successful"+newRoId,Toast.LENGTH_SHORT);
//        }
//
//
////        finish();
//    }








}