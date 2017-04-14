package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();





        final Button buttonCompte = (Button) findViewById(R.id.buttonCompte);
        buttonCompte.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent goLogin = new Intent(MainActivity.this, Profil.class);
                startActivity(goLogin);
            }
        });


    }
}

