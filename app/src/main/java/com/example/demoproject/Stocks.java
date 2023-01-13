package com.example.demoproject;

public class Stocks {

    private String  mTicker;
    private String  mCompanyName;
    private String  mLastTradedPrice;
    private String  mChangeInPrice;
    private String  mPercentChange;
    private String  mTodayOpen;
    private String  mTodayHigh;
    private String  mTodayLow;
    private String  mTodayVolume;
    private String  mLastDayClose;



    public Stocks(String CompanyName,String Ticker , String LastTradedPrice , String ChangeInPrice, String PercentChange, String TodayOpen, String TodayHigh, String TodayLow, String TodayVolume, String LastDayClose )
    {
        mTicker = Ticker;
        mCompanyName = CompanyName;
        mLastTradedPrice = LastTradedPrice;
        mChangeInPrice = ChangeInPrice;
        mPercentChange = PercentChange;
        mTodayOpen = TodayOpen;
        mTodayHigh = TodayHigh;
        mTodayLow = TodayLow;
        mTodayVolume = TodayVolume;
        mLastDayClose = LastDayClose;


    }

    public String getTicker()
    {
        return mTicker;
    }
    public String getCompanyName()
    {
        return mCompanyName;
    }
    public String getLastTradedPrice()
    {
        return mLastTradedPrice;
    }
    public String getChangeInPrice()
    {
        return  mChangeInPrice;
    }
    public String getPercentChange()
    {
        return  mPercentChange;
    }
    public String getTodayOpen()
    {
        return  mTodayOpen;
    }
    public String getTodayHigh()
    {
        return  mTodayHigh;
    }
    public String getTodayLow()
    {
        return  mTodayLow;
    }
    public String getTodayVolume()
    {
        return  mTodayVolume;
    }
    public String getLastDayClose()
    {
        return  mLastDayClose;
    }




}
