package com.example.apprenti.wildbuzz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChallengeBResults extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private ArrayList<Double>mTownResults = new ArrayList<>();

    private ListView mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_bresults);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Walk");
        mResults = (ListView)findViewById(R.id.ListViewResults);

        final ArrayAdapter<Double> arrayAdapter = new ArrayAdapter<Double>(this, android.R.layout.simple_list_item_2, mTownResults);
        mResults.setAdapter(arrayAdapter);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Double value = dataSnapshot.getValue(Double.class);
                mTownResults.add(value);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
