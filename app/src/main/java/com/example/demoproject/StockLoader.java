package com.example.demoproject;

import android.content.Context;

import android.content.AsyncTaskLoader;

import com.example.demoproject.utilities.NetworkUtils;

import java.util.List;

public class StockLoader extends AsyncTaskLoader<List<Stocks>> {

    private String mUrl;

    public StockLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<Stocks> loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        List<Stocks> stocks = NetworkUtils.fetchStockData(mUrl);

        return stocks;
    }
}