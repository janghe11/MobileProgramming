package com.jck.mps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    ListView list;
    String MUSIC_DIR = "/storage/sdcard/Music/";
    String[] fileNames = new File(MUSIC_DIR).list();
    Intent MPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2012041033 전찬규");

        MPS = new Intent(this,MService.class);


        list = (ListView) findViewById(R.id.list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, fileNames);
            list.setAdapter(adapter);
            list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }


    public void Go(View v) {
        try {
            MPS.putExtra("jck", MUSIC_DIR + fileNames[list.getCheckedItemPosition()]);
            startService(MPS);

        } catch (Exception e) {

            Toast.makeText(this, "노래를 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Stop (View v) {
        try {
            stopService(MPS);

        } catch (Exception e) {

            Toast.makeText(this, "노래가 이미 정지되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
