package com.example.gerardodwyer.ps4collection;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {


    private ListView listView;
    private RequestQueue mQueue;
    private ArrayList<Games> gameList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mQueue = Volley.newRequestQueue(this);
        listView = findViewById(R.id.listView_res);

        gameList.clear();

        jsonParseGames();

        ArrayAdapter<Games> arrayAdapter = new ArrayAdapter<Games>(Activity2.this,
                android.R.layout.simple_list_item_1, gameList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Games gameObj = (Games) listView.getAdapter().getItem(i);

                String[] gameData = new String[4];
                gameData[0] = gameObj.getId();
                gameData[1] = gameObj.getGameTitle();
                gameData[2] = gameObj.getReleaseDate();
                gameData[3] = gameObj.getThumb();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Games List/"+ gameData[0]);

                myRef.setValue(gameObj);

                Toast.makeText(Activity2.this,"Game Added to List", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void jsonParseGames() {
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

                                if (releaseDate == "") {
                                    releaseDate = "N/A";
                                }
                                if (thumb == "") {
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

        CustomAdapter myCustomAdapter = new CustomAdapter(Activity2.this, gameList);
        listView.setAdapter(myCustomAdapter);
    }

}


