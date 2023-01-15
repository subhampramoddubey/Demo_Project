package com.example.demoproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;


public class StockAdapter extends ArrayAdapter<Stocks> {

    public StockAdapter(Context context, List<Stocks> stock) {
        super(context, 0,stock);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView==null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.stocks_list_items,parent,false);
        }

        Stocks currentStock = getItem(position);

        TextView tickerView = (TextView) listItemView.findViewById(R.id.Ticker);
        tickerView.setText(currentStock.getTicker());

        TextView companyNameView = (TextView) listItemView.findViewById(R.id.company_name);
        companyNameView.setText(currentStock.getCompanyName());

        TextView lastTradedPriceView = (TextView) listItemView.findViewById(R.id.last_price);
        lastTradedPriceView.setText(currentStock.getLastTradedPrice());

        TextView changeInPriceView = (TextView) listItemView.findViewById(R.id.change_in_price);
        changeInPriceView.setText(currentStock.getChangeInPrice());

        TextView percentChangeView = (TextView) listItemView.findViewById(R.id.percent_change);
        percentChangeView.setText(currentStock.getPercentChange());


        return listItemView;
    }
}
