package com.example.gerardodwyer.ps4collection;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button disclaimer;
    private TextView mTextView;
    private ListView listView;
    private RequestQueue mQueue;
    private ArrayList<Games> gameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.openDoc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        configureButton();







//        mTextView = findViewById(R.id.textView_result);
        Button buttonParse = findViewById(R.id.button_parse);

        listView = findViewById(R.id.listView_result);

//        boxart = findViewById(R.id.list_thumb);


        mQueue = Volley.newRequestQueue(this);



        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsonParse();


            }
        });


    }
public void openActivity2(){

    startActivity(new Intent(MainActivity.this, Activity2.class));
}

    private void configureButton() {

        Button disclaimer= (Button)findViewById(R.id.disclaimerScreen);
        disclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Disclaimer.class));

            }
        });
    }

    private void jsonParse() {
        String url = "https://pastebin.com/raw/q3BxPULM/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Game");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject games = jsonArray.getJSONObject(i);

                                String id = games.getString("id");
                                String gameTitle = games.getString("GameTitle");
                                String releaseDate = games.optString("ReleaseDate");
                                String thumb = games.optString("thumb");

                                if (releaseDate == "")
                                {
                                    releaseDate = "N/A";
                                }
                                if (thumb == "")
                                {
                                    thumb = "N/A";
                                }

                                thumb = "http://thegamesdb.net/banners/" + thumb;


                                Games game = new Games(id, gameTitle, releaseDate, thumb);
                                gameList.add(game);

                                populateListView();
//                                boxart = (ImageView)findViewById(R.id.list_thumb);
//                                loadImageFromUrl(imageURLStart);

                            }
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

        mQueue.add(request);
    }

    private void populateListView() {

        CustomAdapter myCustomAdapter = new CustomAdapter(MainActivity.this, gameList);
        listView.setAdapter(myCustomAdapter);

//        openActivity2();
    }

//    private void loadImageFromUrl(String imageURL) {
//        Picasso.with(this).load(imageURL).placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_launcher)
//                .into (boxart, new com.squareup.picasso.Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
//    }


}

