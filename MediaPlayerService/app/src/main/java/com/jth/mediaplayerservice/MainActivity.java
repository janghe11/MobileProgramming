package com.jth.mediaplayerservice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static final String MEDIA_FILE = "com.jth.listview.send";
    static final String LOG_TAG = "JthLogTag";

    ListView listView;
    String[] fileNames = new File("/storage/sdcard/Music/").list();
    String MUSIC_DIR = "/storage/sdcard/Music/";
    Intent mp3Intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MediaPlayerService_2반_장태희");

        mp3Intent = new Intent(this,MyMusicService.class);

        try {
            listView = (ListView) findViewById(R.id.listView1);
            Arrays.sort(fileNames);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_single_choice, fileNames);
            listView.setAdapter(adapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        } catch (Exception e) {
            Log.d(LOG_TAG, "fileNames location error : " + e.toString());
            Toast.makeText(this, "음악 파일 경로 오류 입니다. 경로를 /storage/sdcard/Music/ 으로 재설정 하여 주십시오. 현재 경로는 " + MUSIC_DIR + "입니다."
                    , Toast.LENGTH_SHORT).show();
        }
    }

    public void mp3Play(View v) {
        try {
            mp3Intent.putExtra(MEDIA_FILE, MUSIC_DIR + fileNames[listView.getCheckedItemPosition()]);
            startService(mp3Intent);
            Log.d(LOG_TAG, "start mp3Intent service.");
        } catch (Exception e) {
            Log.d(LOG_TAG, "mp3Play button exception : " + e.toString());
            Toast.makeText(this, "노래를 먼저 선택해주세요", Toast.LENGTH_SHORT).show();
        }
    }

    public void mp3Stop (View v) {
        try {
            stopService(mp3Intent);
            Log.d(LOG_TAG, "call to stop mp3Intent service");
        } catch (Exception e) {
            Log.d(LOG_TAG, "mp3Stop button exception : " + e.toString());
            Toast.makeText(this, "노래가 이미 정지되었습니다. 다른 노래를 선택 해 주세요", Toast.LENGTH_SHORT).show();
        }
    }
}
