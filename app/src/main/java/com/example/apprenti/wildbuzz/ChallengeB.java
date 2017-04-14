package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChallengeB extends AppCompatActivity {
    Button buttonParticipationWalk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengeb);

        buttonParticipationWalk=(Button)findViewById(R.id.buttonParticipationWalk);
        buttonParticipationWalk.setOnClickListener(new View.OnClickListener() {
//intent bidon vers profile
            public void onClick(View v) {
                Intent goLogin = new Intent(ChallengeB.this, ChallengeBSecondePage.class);
                startActivity(goLogin);
            }
        });
    }
}
