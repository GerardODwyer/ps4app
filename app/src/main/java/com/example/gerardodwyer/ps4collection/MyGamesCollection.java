package com.example.gerardodwyer.ps4collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyGamesCollection extends AppCompatActivity {

    private ListView listView;
    private DatabaseReference database;
    private ArrayList<Games> gamesCollection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games_collection);

        gamesCollection.clear();

        listView = findViewById(R.id.listView_myList1);

        database = FirebaseDatabase.getInstance().getReference();   //From Firebase Documentaion
        database.child("Games List").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

//                        GenericTypeIndicator<HashMap<String, Games>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Games>>() {};      //Code to Read From database cause activity to crash
//                        Map<String, Games> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
//                        gamesCollection = new ArrayList(objectHashMap.values());


                        populateListView(gamesCollection);




                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MyGamesCollection.this, "Failed to load Games List.",
                                Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void populateListView(ArrayList gamesList) {

        CustomAdapter myCustomAdapter = new CustomAdapter(MyGamesCollection.this, gamesList);
        listView.setAdapter(myCustomAdapter);
    }
}
