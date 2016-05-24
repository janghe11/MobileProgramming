package com.jth.mediaplayerservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by jang on 16. 5. 20.
 */
public class MyMusicService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(MainActivity.LOG_TAG, "onBind intent");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(MainActivity.LOG_TAG, "onCreate called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.LOG_TAG, "Destroy app(onStopService called)");
        if(mp != null) {
            releaseMP();
            Log.d(MainActivity.LOG_TAG, "Disabled mediaplayer resource");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mp == null) {
            String mediaFile = intent.getStringExtra(MainActivity.MEDIA_FILE);
            Log.d(MainActivity.LOG_TAG, mediaFile);
            // Start to create MediaPlayer
            mp = new MediaPlayer();
            // When music playing completed
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!mp.isPlaying()) {
                        Toast.makeText(MyMusicService.this, "재생이 끝났습니다.", Toast.LENGTH_SHORT).show();
                        releaseMP();
                    }
                }
            });
            mp.reset();
            try {
                // Start to play music
                mp.setDataSource(mediaFile);
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                Log.d(MainActivity.LOG_TAG, e.toString());
            }
        } else {
            Toast.makeText(MyMusicService.this, "음악이 재생중입니다. 정지 후 재생 버튼을 눌러 주세요.", Toast.LENGTH_SHORT).show();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void releaseMP() {
        mp.stop();
        mp.release();
        mp = null;
        Log.d("Main", "미디어 플레이어를 해제했습니다.");
    }
}
