package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class List extends AppCompatActivity implements View.OnClickListener {


    private ImageButton buttonChallengeA;
    private ImageButton buttonChallengeB;
    private ImageButton buttonChallengeC;
    private BottomNavigationView bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        bottomBar = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        buttonChallengeA=(ImageButton)findViewById(R.id.buttonChallengeA);
        buttonChallengeB=(ImageButton)findViewById(R.id.buttonChallengeB);
        buttonChallengeC=(ImageButton)findViewById(R.id.buttonChallengeC);
        buttonChallengeA.setOnClickListener(this);
        buttonChallengeB.setOnClickListener(this);
        buttonChallengeC.setOnClickListener(this);
        bottomBar= (BottomNavigationView)findViewById(R.id.bottomNavigation);

        bottomBar.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.profil:
                                Intent navToProfil = new Intent(List.this, Profil.class);
                                List.this.startActivity(navToProfil);
                                break;

                            case R.id.ranking:
                                Intent navToRanking = new Intent(List.this, Rank.class);
                                List.this.startActivity(navToRanking);
                                break;
                        }
                        return true;
                    }
                });
    }


    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonChallengeA:
                Intent buttonA = new Intent(List.this, ChallengeA.class);
                List.this.startActivity(buttonA);
                break;

            case R.id.buttonChallengeB:
                Intent buttonB = new Intent(List.this, ChallengeB.class);
                List.this.startActivity(buttonB);
                break;

            case R.id.buttonChallengeC:
                Intent buttonC = new Intent(List.this, ChallengeC.class);
                List.this.startActivity(buttonC);
                break;

            default:
                break;
        }
    }
    }

