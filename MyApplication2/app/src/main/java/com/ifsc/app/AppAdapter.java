package com.ifsc.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {
    public AppAdapter(MainActivity mainActivity, int item_lista, List<ApplicationInfo> packageInfoList) {
        super(mainActivity,item_lista, packageInfoList);
    }

    @NonNull
        @Override
        public View getView (int position, @Nullable View convetView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista, parent, false);
        ApplicationInfo applicationInfo = getItem(position);
        TextView textViewAppName = view.findViewById(R.id.nomeApp);
        ImageView imageViewAppIncon = view.findViewById(R.id.inconeApp);

        imageViewAppIncon.setImageDrawable(applicationInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(applicationInfo.loadLabel(getContext().getPackageManager()));

        return view;
    };
}
