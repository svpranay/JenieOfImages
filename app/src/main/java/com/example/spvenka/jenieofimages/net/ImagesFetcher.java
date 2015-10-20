package com.example.spvenka.jenieofimages.net;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class ImagesFetcher {

    private static final String API_BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";
    private int SIZE = 8;
    private AsyncHttpClient client;


    public ImagesFetcher() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String query, int size, int page) throws Exception {
        return API_BASE_URL + "?v=1.0&q= " + URLEncoder.encode(query, "utf-8") + "&rsz=" + size + "&start=" + page;
    }

    /**
     * Fetch images from google api.
     * https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=monkey&rsz=8
     * @param query
     */
    public void getImages(final String query, int page, JsonHttpResponseHandler jsonHttpResponseHandler) {
        try {
            String url = getApiUrl(query, SIZE, page);
            Log.i("url", url);
            client.get(url, jsonHttpResponseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
