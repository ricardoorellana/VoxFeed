package com.ricardoorellana.welcometovoxfeed.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ricardoorellana.welcometovoxfeed.beans.Publication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HttpConnectionUtil to make HTTP rquest and set publications objects
 */
public class HttpConnectionUtil {

    /**
     * Debug Tag for use logging debug output to LogCat
     */
    private static final String LOG_TAG = HttpConnectionUtil.class.getSimpleName();

    public HttpConnectionUtil() {

    }

    /**
     *
     * @param url String URL endpoint to make the HTTP request
     * @return
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000);
            urlConnection.setConnectTimeout(1000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            } else {
                Log.i(LOG_TAG, " Error getting JSON response");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem retrieving the movies JSON results.", e);
        } finally {

            if(urlConnection != null) {
                urlConnection.disconnect();
            }

            if(inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder outPutString = new StringBuilder();
        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while(line != null) {
                outPutString.append(line);
                line = reader.readLine();
            }
        }
        return outPutString.toString();
    }

    /**
     * Query the USGS dataset and return a list of {@link com.ricardoorellana.welcometovoxfeed.beans.Publication} objects.
     */
    public static List<Publication> fetchPublicationData(String requestUrl) {
        Log.i(LOG_TAG, "fetchPublicationData");

        /**
         *  Forcing the background thread to pause execution and wait for 2 seconds (which is 2000 milliseconds),
         *  before proceeding to execute the rest of lines of code in this method. If you try to add the Thread.sleep(2000);
         *  method call by itself, Android Studio will complain that there is an uncaught exception, so we need to
         *  surround that statement with a try/catch block.
         */
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Publication}s
        List<Publication> publications = extractFeatureFromJson(jsonResponse);

        Log.i(LOG_TAG, jsonResponse);

        // Return the list of {@link Publication}s
        return publications;
    }

    public static URL createUrl(String stringUrl){
        URL url = null;

        try {
            url = new URL(stringUrl);

        } catch (MalformedURLException e) {
//            e.printStackTrace();
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }

        return url;
    }

    /**
     * Return a list of {@link Publication} objects that has been built up from
     * parsing a JSON response.
     */
    public static List <Publication> extractFeatureFromJson(String publicationJson) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(publicationJson)) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Publication publicationArray [];

        publicationArray =  gson.fromJson(publicationJson, Publication[].class);

        List<Publication> publications = new ArrayList<>(Arrays.asList(publicationArray));


        // Return the list of publications
        return publications;
    }
}
