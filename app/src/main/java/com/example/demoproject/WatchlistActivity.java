package com.example.demoproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*  This WatchList Activity contains the views of all stocks added in Watchlist.
    it fetch data of all added stocks in WatchList by user and continuously sync it with database.
*/
public class WatchlistActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Stocks>> {

    private NetworkInfo networkInfo;
    private ConnectivityManager connMgr;
    private TextView mEmptyStateTextView;
    private static final int STOCK_LOADER_ID = 1;
    private StockAdapter mAdaptor;
    private ProgressBar mProgressBar;

//    private static final String STATIC_JSON_URL =
//            "https://script.google.com/macros/s/AKfycbw62UgYHzVSsk0Afu-ffIcGVKfJOsZv7iGH1X4U-wTAWY3vxjaqhuTAINWJDsaI6qXM/exec?action=getdata";

    private static final String STATIC_JSON_URL_NSE ="https://www.nseindia.com/api/equity-stockIndices?index=SECURITIES%20IN%20F%26O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);


        ListView stockListView = (ListView) findViewById(R.id.list);

        mAdaptor = new StockAdapter(this, new ArrayList<Stocks>());

        stockListView.setAdapter(mAdaptor);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        stockListView.setEmptyView(mEmptyStateTextView);

        mProgressBar = findViewById(R.id.loadring_indicator);


        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Stocks currentStock = mAdaptor.getItem(position);

                Intent intent = new Intent(WatchlistActivity.this, DetailsActivity.class);

                intent.putExtra("A1",currentStock.getCompanyName());
                intent.putExtra("A2",currentStock.getTicker());
                intent.putExtra("A3",currentStock.getLastTradedPrice());
                intent.putExtra("A4",currentStock.getPercentChange());
                intent.putExtra("A5",currentStock.getChangeInPrice());
                intent.putExtra("A6",currentStock.getTodayOpen());
                intent.putExtra("A7",currentStock.getTodayHigh());
                intent.putExtra("A8",currentStock.getTodayLow());
                intent.putExtra("A9",currentStock.getTodayVolume());
                intent.putExtra("A10",currentStock.getLastDayClose());
                startActivity(intent);
            }
        });

        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(STOCK_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No Internet Found");

        }


    }

    @Override
    public Loader<List<Stocks>> onCreateLoader(int i, Bundle bundle) {

        mProgressBar.setVisibility(View.VISIBLE);

        return new StockLoader(this, STATIC_JSON_URL_NSE);

    }

    @Override
    public void onLoadFinished(Loader<List<Stocks>> loader, List<Stocks> stocks) {

        mProgressBar.setVisibility(View.INVISIBLE);

        mAdaptor.clear();

        if (stocks != null && !stocks.isEmpty()) {
            mAdaptor.addAll(stocks);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Stocks>> loader) {

        mAdaptor.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Refresh) {

            mAdaptor.clear();
            getLoaderManager().restartLoader(STOCK_LOADER_ID, null, this);
            mEmptyStateTextView.setVisibility(View.GONE);

            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}



