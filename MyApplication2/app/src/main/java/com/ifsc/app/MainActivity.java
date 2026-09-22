package com.ifsc.app;

import android.content.ActivityNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.content.Intent;



public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<PackageInfo> installedApps;
    List<ResolveInfo> appsListIntentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);//Constraint, Button e Text?

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.ifsc.app.R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PackageManager packageManager = getPackageManager();
        installedApps = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUCHER);
        appsListIntentMain = packageManager.queryIntentActivities(intent, 0);



        AppAdapter adapter = new AppAdapter(this,
                R.layout.item_lista,
                installedApps);
        lv = findViewById(R.id.listView_apps);
        lv.setAdapter(adapter);
    }
}