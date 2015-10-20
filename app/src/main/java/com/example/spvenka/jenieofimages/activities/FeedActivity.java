package com.example.spvenka.jenieofimages.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.spvenka.jenieofimages.R;
import com.example.spvenka.jenieofimages.adapters.EndlessScrollListener;
import com.example.spvenka.jenieofimages.adapters.ImageAdapter;
import com.example.spvenka.jenieofimages.models.GImage;
import com.example.spvenka.jenieofimages.models.GImageResponse;
import com.example.spvenka.jenieofimages.net.ImagesFetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FeedActivity extends AppCompatActivity {

    private List<GImage> images;
    private ImagesFetcher imagesFetcher = new ImagesFetcher();
    public ObjectMapper objectMapper = new ObjectMapper();
    private ImageAdapter imageAdapter;
    private String currQuery = null;
    private Boolean isNewQuery = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        images = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, images);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(imageAdapter);
        gridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                Log.i("feed", "Calling fetch due to scroll");
                fetchImages(currQuery, page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent fullScreenImageIntent = new Intent(getApplicationContext(), FullScreenImageActivity.class);
                GImage gImage = images.get(position);
                fullScreenImageIntent.putExtra("url", gImage.imageUrl);
                startActivity(fullScreenImageIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currQuery = query;
                fetchImages(query, 0);
                isNewQuery = true;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void fetchImages(String query, int page) {
        if (isNewQuery) {
            images.clear();
            isNewQuery = false;
        }

        imagesFetcher.getImages(query, page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    imageAdapter.notifyDataSetChanged();
                    GImageResponse gImageResponse = objectMapper.readValue(response.toString(), GImageResponse.class);
                    for (int i=0; i < gImageResponse.responseData.results.size(); i++) {
                        GImage gImage = gImageResponse.responseData.results.get(i);
                        images.add(gImage);
                        imageAdapter.notifyDataSetChanged();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
