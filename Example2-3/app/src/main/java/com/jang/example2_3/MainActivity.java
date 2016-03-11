package com.jang.example2_3;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button googleBtn = (Button)findViewById(R.id.googleBtn);
        googleBtn.setBackgroundColor(Color.GRAY);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(mIntent);
            }
        });

        Button emrCallBtn = (Button)findViewById(R.id.emrCallBtn);
        emrCallBtn.setBackgroundColor(Color.GREEN);
        emrCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/119"));
                startActivity(mIntent);
            }
        });

        Button opengrBtn = (Button)findViewById(R.id.opengrBtn);
        opengrBtn.setBackgroundColor(Color.RED);
        opengrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mIntent);
            }
        });

        Button exitBtn = (Button)findViewById(R.id.exitBtn);
        exitBtn.setBackgroundColor(Color.YELLOW);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
