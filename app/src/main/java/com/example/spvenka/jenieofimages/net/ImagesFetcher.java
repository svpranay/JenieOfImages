package com.example.spvenka.jenieofimages.net;

import android.content.Context;
import android.util.Log;

import com.example.spvenka.jenieofimages.R;
import com.example.spvenka.jenieofimages.util.ParamManager;
import com.example.spvenka.jenieofimages.util.Params;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ImagesFetcher {

    private static final String API_BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";
    private int SIZE = 8;
    private AsyncHttpClient client;


    public ImagesFetcher() {
        this.client = new AsyncHttpClient();
    }

    /**
     * as_sitesearch=photobucket.com.
     * imgc=gray color
     * imgcolor=black
     * imgsz=icon small|medium|large|xlarge
     * imgtype=face  photo lineart clipart
     * @param context
     * @param query
     * @param size
     * @param page
     * @return
     * @throws Exception
     */
    private String getApiUrl(Context context, String query, int size, int page) throws Exception {
        Map<Integer, String> params = ParamManager.getParams(context);
        Log.i("fetcher", params.toString());
        String queryParams = "";
        if (params.containsKey(R.array.color_array)) {
            queryParams = queryParams + "&imgcolor=" + params.get(R.array.color_array);
        }
        if (params.containsKey(R.array.size_array)) {
            queryParams = queryParams + "&imgsz=" + params.get(R.array.size_array);
        }
        if (params.containsKey(R.array.type_array)) {
            queryParams = queryParams + "&imgtype=" + params.get(R.array.type_array);
        }
        if (params.containsKey(R.id.etSite)) {
            queryParams = queryParams + "&ac_sitesearch=" + params.get(R.id.etSite);
        }
        return API_BASE_URL + "?v=1.0&q=" + URLEncoder.encode(query, "utf-8") + "&rsz=" + size + "&start=" + page + queryParams;
    }

    /**
     * Fetch images from google api.
     * https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=monkey&rsz=8
     * @param query
     */
    public void getImages(Context context, final String query, int page, JsonHttpResponseHandler jsonHttpResponseHandler) {
        try {
            String url = getApiUrl(context, query, SIZE, page);
            Log.i("url", url);
            client.get(url, jsonHttpResponseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
