package com.example.admin.customeviewsapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CustomCircle circle;
    private CustomLayoutManager clm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle = findViewById( R.id.ctmCircle );
        clm = findViewById( R.id.cstmLayout );

        Button loginButton = clm.getButton();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButtonPressed();
            }
        });
    }

    public void changeRadius(View view) {
        Log.d(TAG, "changeRadius: ");
        circle.setRadius( 50 );
    }

    private void loginButtonPressed() {
        Toast.makeText(this, "Logged in.", Toast.LENGTH_SHORT).show();
    }
}
