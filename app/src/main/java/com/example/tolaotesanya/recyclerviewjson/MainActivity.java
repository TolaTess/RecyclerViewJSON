package com.example.tolaotesanya.recyclerviewjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClicklistener {

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESC = "desc";

    private static final String TAG = "MainActivity";

    private  RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<ListItem> listItemList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        listItemList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }


    private void parseJSON()
    {
        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject vehVendorAvails = jsonArray.getJSONObject(i);

                                String name = vehVendorAvails.getString("user");
                                String imageUrl = vehVendorAvails.getString("webformatURL");
                                String desc = vehVendorAvails.getString("tags");

                                listItemList.add(new ListItem(imageUrl, name, desc));
                            }

                            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, listItemList);
                            recyclerView.setAdapter(recyclerViewAdapter);
                            recyclerViewAdapter.setmOnItemClicklistener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {

        Intent galleryIntent = new Intent(this, GalleryActivity.class);
        ListItem clickedItem = listItemList.get(position);

        galleryIntent.putExtra(EXTRA_URL, clickedItem.getImageURL());
        galleryIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        galleryIntent.putExtra(EXTRA_DESC, clickedItem.getDesc());

        startActivity(galleryIntent);
    }

}
