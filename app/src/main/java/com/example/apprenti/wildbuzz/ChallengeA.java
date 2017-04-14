package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChallengeA extends AppCompatActivity implements View.OnClickListener {

    Button buttonParticipationA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengea);


        buttonParticipationA = (Button) findViewById(R.id.buttonParticipationEDT);
        buttonParticipationA.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        startActivity (new Intent(ChallengeA.this,ChallengeAplay.class));
    }
}
