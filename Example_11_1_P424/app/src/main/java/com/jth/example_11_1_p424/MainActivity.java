package com.jth.example_11_1_p424;

import android.media.MediaPlayer;
import android.provider.LiveFolders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView list;
    final String[] mid = {"히어로즈", "24시", "로스트", "로스트룸", "스몰빌", "탐정몽크",
            "빅뱅이론", "프렌즈", "덱스터", "글리", "가쉽걸", "테이큰", "슈퍼내추럴", "브이"};
    String[] fileNames = new File("/sdcard/Music/").list();
    String MUSIC_DIR = "/sdcard/Music/";

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰 테스트");

        list = (ListView)findViewById(R.id.listView1);
        Arrays.sort(fileNames);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, fileNames);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, mid[position], Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    public void mp3Play(View v) {
        if(mp == null) {
            mp = new MediaPlayer();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //mp.stop();
                    mp.release();
                    mp = null;
                }
            });
            mp.reset();
            try{
                mp.setDataSource(MUSIC_DIR + fileNames[list.getCheckedItemPosition()]);
                mp.prepare();
                mp.start();
            } catch (IOException e) {

            }
        }
    }

    public void mp3Stop(View v) {
        if(mp != null && mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mp.stop();
        mp.release();
        mp = null;
    }

    /*
    public void selectMid(View v) {
        Toast.makeText(this, fileNames[list.getCheckedItemPosition()], Toast.LENGTH_SHORT).show();
    }
    */
}
