package com.ifsc.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);//Constraint, Button e Text?

        Button buttonExplicita = findViewById(R.id.buttonExplicita);
        Button buttonImplicita = findViewById(R.id.buttonImplicita);

        //Intent Explícita
        buttonExplicita.setOnClickListener(v->{
            Intent exemploExplicita = new Intent(this,IntentExplicita.class);
            exemploExplicita.putExtra("origem", "MainActivity");
            startActivity(exemploExplicita);
        });

    }
}