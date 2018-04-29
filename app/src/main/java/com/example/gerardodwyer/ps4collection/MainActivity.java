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
//main class initialisess home screen and buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        disclaimerButton();
        showGamesButton();
        searchGamesButton();
        top10Button();
        showMyGames();
        openDocButton();
        bestGameButton();
        shareButton();
        //openDocButton();   -Load in OpenDoc Method (onCreate)

//      boxart = findViewById(R.id.list_thumb);

    }

    private void showGamesButton() {

        Button showGamesButton = (Button)findViewById(R.id.showGamesButton);
        showGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Activity2.class));

            }
        });
    }

    private void disclaimerButton() {

        Button disclaimer= (Button)findViewById(R.id.disclaimerButton);
        disclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Disclaimer.class));

            }
        });
    }

    private void searchGamesButton() {

        Button disclaimer= (Button)findViewById(R.id.searchGamesButton);
        disclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

            }
        });
    }

    private void top10Button() {
        Button top10 = (Button)findViewById(R.id.top10Button);
        top10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Top10Activity.class));

            }
        });
    }
    private void bestGameButton() {
        Button bestgame = (Button)findViewById(R.id.bestGameButton);
        bestgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BestGameActivity.class));

            }
        });
  }


    //----Method For OpenDoc Button

   private void openDocButton() {

       Button openDocButton = (Button)findViewById(R.id.openDocButton);
       openDocButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MoreDetailsActivity.class));

            }
        });
    }
//share button impliment at a later date
private void shareButton() {

    Button shareButton = (Button)findViewById(R.id.ShareActivity);
    shareButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, ShareActivity.class));

        }
    });
}

    private void  showMyGames() {

        Button  showMyGames = (Button)findViewById(R.id.showMyGames);
        showMyGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyCollectionActivity.class));

            }
        });
    }




    //---Picasso Image Loader


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

