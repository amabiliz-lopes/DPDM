package com.ifsc.app;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IntentExplicita extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_explicita);

        String origem = getIntent().getStringExtra("origem");

        TextView textOrigem = findViewById(R.id.textOrigem);
        textOrigem.setText("Dado recebid via Intent: origem = " + origem);

        FloatingActionButton buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(v-> finish());
    }
}
