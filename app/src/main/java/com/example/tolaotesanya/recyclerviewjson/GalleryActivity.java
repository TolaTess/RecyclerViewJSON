package com.example.tolaotesanya.recyclerviewjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import static com.example.tolaotesanya.recyclerviewjson.MainActivity.EXTRA_URL;
import static com.example.tolaotesanya.recyclerviewjson.MainActivity.EXTRA_NAME;
import static com.example.tolaotesanya.recyclerviewjson.MainActivity.EXTRA_DESC;

/**
 * Created by tolaotesanya on 27/12/2018.
 */

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started. ");

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String desc = intent.getStringExtra(EXTRA_DESC);

        ImageView imageView = findViewById(R.id.image1);
        TextView textViewCreator = findViewById(R.id.image_name2);
        TextView textViewLikes = findViewById(R.id.image_description);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewCreator.setText("Name: " + name);
        textViewLikes.setText("Likes: " + desc);

    }
}
