package com.example.demoproject.data;

import android.provider.BaseColumns;

public final class OrderContract {

    private OrderContract() {}

    public static final class TableEntry implements BaseColumns
    {

    public static final String TABLE_NAME = "Portfolio";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PORTFOLIO_SYMBOLE = "symbole";
        public static final String COLUMN_PORTFOLIO_TRADED_PRICE = "ltp";
        public static final String COLUMN_PORTFOLIO_QUANTITY = "quantity";
        public static final String COLUMN_PORTFOLIO_PRICE_CHANGE = "priceChange";
        public static final String COLUMN_PORTFOLIO_PERCENT_CHANGE = "percentChange";
        public static final String COLUMN_PORTFOLIO_MONEY_CHANGE = "moneyChange";
        public static final String COLUMN_PORTFOLIO_ORDER_TYPE = "orderType";

        public static final int ORDER_TYPE_BUY = 1;
        public static final int ORDER_TYPE_SELL = -1;

    }


}
