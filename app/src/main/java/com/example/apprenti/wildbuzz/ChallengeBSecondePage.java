package com.example.apprenti.wildbuzz;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChallengeBSecondePage extends AppCompatActivity implements SensorEventListener {
    TextView textViewSteps;
    TextView textViewCount;
    SensorManager sensorManager;
    boolean running = false;
    Button buttonResults;

    StepsCounter mStepsCounter;
    private DatabaseReference mDatabase;


    private FirebaseAuth mAuth;
    private String displayName;
    private String idUser;
    private String uploadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_bseconde_page);

        buttonResults = (Button)findViewById(R.id.buttonResults);
        buttonResults.setOnClickListener(new View.OnClickListener() {
            //intent bidon vers profile
            public void onClick(View v) {
                Intent goResults = new Intent(ChallengeBSecondePage.this, ChallengeBResults.class);
                startActivity(goResults);
            }
        });


        textViewCount = (TextView)findViewById(R.id.textViewCount);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        mStepsCounter = new StepsCounter(0);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        displayName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference("Walk");
        uploadId = mDatabase.push().getKey();
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null){
            sensorManager.registerListener(this,countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running){
            textViewCount.setText(String.valueOf(event.values[0]));

            mStepsCounter.steps += event.values[0];

            if (mStepsCounter.steps>= 25) {
                mDatabase.child(displayName).setValue(mStepsCounter.steps);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



}