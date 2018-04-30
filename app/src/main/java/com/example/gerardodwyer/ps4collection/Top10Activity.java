package com.example.gerardodwyer.ps4collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
//smaller version of show all games based on the top ten current sellers
public class Top10Activity extends AppCompatActivity {

    private ListView listView;
    private RequestQueue mQueue;
    private ArrayList<Games> gameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);

        mQueue = Volley.newRequestQueue(this);
        listView = findViewById(R.id.listView_top10);

        jsonParseGames();
    }



    private void jsonParseGames() {
        String url = "https://pastebin.com/raw/3TFyCF4b";
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

        CustomAdapter myCustomAdapter = new CustomAdapter(Top10Activity.this, gameList);
        listView.setAdapter(myCustomAdapter);
    }

}
