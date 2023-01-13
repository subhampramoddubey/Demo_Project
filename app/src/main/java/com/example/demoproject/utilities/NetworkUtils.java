package com.example.demoproject.utilities;

import android.text.TextUtils;
import android.util.Log;

import com.example.demoproject.Stocks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public final class NetworkUtils {

    public static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    private static final String STATIC_JSON_URL =
            "https://script.googleusercontent.com/macros/echo?user_content_key=cAD9GEle59V2BIYezXz3Kk0pUD3pmvbio_POQP5Cyl-7oDnMX2bvcTg4TwhoHYI0L1nlaEBT99HMbqdXXRA46QTse9HkrJ6Am5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnHCXjEF6Jlhc6xxekuCeEECBgtsxmG-5TM7axaDKW3_TzqKnJuYsy7B3Xdo4QnfC2Q&lib=MSKxoTD8KE-03f2YxPJKn5sQfnqTZGV-_";




    private static void updateUi(Stocks stocks) {

    }


    public static List<Stocks> fetchStockData(String requestUrl)
    {


        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in doInBackGround....makeHttpRequest", e);
        }



        // Extract relevant fields from the JSON response and create an {@link Event} object

        List<Stocks> stock = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return stock;

    }



    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


    /**
     * the below commented line is for makking https request using inputstream and buffer reader
     *
     * and above method (makeHttpRequest) is using input Stream reader and scanner
     */

//    private static String makeHttpRequest(URL url) throws IOException {
//        String jsonResponse = "";
//
//        if(url==null)
//        {
//            return jsonResponse;
//        }
//
//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//        try {
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.setReadTimeout(10000 /* milliseconds */);
//            urlConnection.setConnectTimeout(15000 /* milliseconds */);
//            urlConnection.connect();
//            if (urlConnection.getResponseCode() == 200) {
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//            }
//        }catch (IOException e) {
//            // TODO: Handle the exception
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (inputStream != null) {
//                // function must handle java.io.IOException here
//                inputStream.close();
//            }
//        }
//        return jsonResponse;
//    }


//    private static String readFromStream(InputStream inputStream) throws IOException {
//        StringBuilder output = new StringBuilder();
//        if (inputStream != null) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            String line = reader.readLine();
//            while (line != null) {
//                output.append(line);
//                line = reader.readLine();
//            }
//        }
//        return output.toString();
//    }

    public static List<Stocks> extractFeatureFromJson(String stockJSON) {


        if(TextUtils.isEmpty(stockJSON)){
            return null;
        }

        List<Stocks> stock = new ArrayList<>();


        try {


            JSONObject baseJsonResponse = new JSONObject(stockJSON);
            JSONArray stockArray = baseJsonResponse.getJSONArray("data");

            for(int i= 0; i<stockArray.length(); i++)
            {

                JSONObject record = stockArray.getJSONObject(i);

//                String tickerName = record.getString("NAME");
                String tickerSymbole = record.getString("symbol");
                String tickerLTP = record.getString("lastPrice");
                String changeInPrice =  record.getString("change");
                String percentChange = record.getString("pChange");
                String todayOpen = record.getString("open");
                String todayHigh = record.getString("dayHigh");
                String todayLow = record.getString("dayLow");
                String todayVolume = record.getString("totalTradedVolume");
                String lastDayClosa = record.getString("previousClose");
                JSONObject metadata = record.getJSONObject("meta");
                String tickerName = metadata.getString("companyName");



                Stocks stockss = new Stocks(tickerName,tickerSymbole,tickerLTP,changeInPrice,percentChange,todayOpen,todayHigh,todayLow,todayVolume,lastDayClosa);
                stock.add(stockss);

            }
        } catch (JSONException e) {


//        try {
//
////            JSONArray stockArray = new JSONArray(stockJSON);
//
//            JSONObject baseJsonResponse = new JSONObject(stockJSON);
//
//            JSONArray stockArray = baseJsonResponse.getJSONArray("undefined");
//
//            for(int i= 0; i<stockArray.length(); i++)
//            {
//
//                JSONObject record = stockArray.getJSONObject(i);
//
//                String tickerName = record.getString("NAME");
//                String tickerSymbole = record.getString("SYMBOLE");
//                String tickerLTP = record.getString("LTP");
//                String changeInPrice =  record.getString("ChangeInPRICE");
//                String percentChange = record.getString("% change");
//
//                Stocks stocks = new Stocks(tickerSymbole,tickerName,tickerLTP,changeInPrice,percentChange);
//                stock.add(stocks);
//
//            }
//        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the stocks JSON results", e);
        }
        return stock;
    }


    }
