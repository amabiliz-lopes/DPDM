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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);//Constraint, Button e Text?
        TextView tv = findViewById(R.id.text);

        tv.setText("-");

        Button button=findViewById(R.id.button);
        button.setText("Gerar Número");

        EditText edmin,edmax;
        edmin=findViewById(R.id.edMin);
        edmax=findViewById(R.id.edMax);

        button.setOnClickListener(v -> {

            String smin=edmin.getText().toString();
            String smax=edmax.getText().toString();

            if(smin.isEmpty()){
                edmin.setError("Informe um inteiro");
                return;
            }
            if(smax.isEmpty()){
                edmax.setError("Informe um número");
            }

            int min = Integer.parseInt(smin);
            int max = Integer.parseInt(smax);

            if(min>max){
                Toast.makeText(this, "Defina mínimo menor que máximo", Toast.LENGTH_SHORT).show();
                return;
            }

            Random random = new Random();
            int randonN = random.nextInt(min,max);
            tv.setText(Integer.toString(randonN));


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}