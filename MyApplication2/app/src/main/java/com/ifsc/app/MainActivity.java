package com.ifsc.app;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        //Intent Implícita
        buttonImplicita.setOnClickListener(v->{

            try{
                //Copia o PDF de res/raw para o cahce do app
                File pastaCache = new File(getCacheDir(), "pdfs");
                if(!pastaCache.exists()){
                    pastaCache.mkdirs();
                }
                File arquivoPdf = new File(pastaCache, "intent_implicita.pdf");

                if (!arquivoPdf.exists()) {
                    InputStream input = getResources().openRawResource(R.raw.intent_implicita);
                    OutputStream output = new FileOutputStream(arquivoPdf);

                    byte[] buffer = new byte[1024];
                    int tamanhoLido;
                    while ((tamanhoLido = input.read(buffer)) != -1) {
                        output.write(buffer, 0, tamanhoLido);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }

                //Gera uma URI segura via FileProvider
                Uri uri = FileProvider.getUriForFile(
                        this,
                        getPackageName() + ".provider",
                        arquivoPdf
                );

                //Cria a Intent Implícita
                Intent abrirPdf = new Intent(Intent.ACTION_VIEW);
                abrirPdf.setDataAndType(uri, "application/pdf");
                abrirPdf.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                //Apresenta o seletor de apps
                Intent chooser = Intent.createChooser(abrirPdf, "Abrir PDF com...");
                startActivity(chooser);

            } catch (IOException e) {
                Toast.makeText(this, "Erro ao ler o PDF.", Toast.LENGTH_SHORT).show();
            } catch (ActivityNotFoundException e){
                Toast.makeText(this, "Nenhum app encontrado para abrir o PDF.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}