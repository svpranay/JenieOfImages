package com.example.spvenka.jenieofimages.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.spvenka.jenieofimages.R;
import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        ImageView imageView = (ImageView) findViewById(R.id.ivFullScreenImage);
        Intent fullScreenImageIntent = getIntent();
        String url = fullScreenImageIntent.getStringExtra("url");
        Picasso.with(this).load(Uri.parse(url)).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
