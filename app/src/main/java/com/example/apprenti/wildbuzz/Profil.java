package com.example.apprenti.wildbuzz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity implements View.OnClickListener {

    //Création de l'objet
    private FirebaseAuth firebaseAuth;

    private TextView textViewEmail;
    private Button buttonLogout;
    private Button buttonListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //On initialise l'authentification de firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //si l'utilisateur n'est pas loggé
        //l'utilisateur en cours sera null
        if (firebaseAuth.getCurrentUser() == null) {
            //ferme cette activity
            finish();
            //renvoie à la login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        else {

            //récupère utilisateur en cours
            FirebaseUser user = firebaseAuth.getCurrentUser();


            textViewEmail = (TextView) findViewById(R.id.textViewEmail);
            buttonLogout = (Button) findViewById(R.id.buttonDisconnect);
            buttonListe = (Button) findViewById(R.id.buttonListe);


            textViewEmail.setText("Bienvenue "+user.getEmail());

            //On relie le listener au bouton
            buttonLogout.setOnClickListener(this);
            buttonListe.setOnClickListener(this);

        }}

    @Override
    public void onClick(View view) {

        if(view == buttonLogout){

            firebaseAuth.signOut();
            //l'activité est fermée
            finish();
            //On est renvoyé à login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonListe){
            startActivity(new Intent(this, List.class));
        }
    }

}