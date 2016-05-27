package com.jck.mps;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.IOException;

public class MService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "음악을 플레이합니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mp == null) {
            String mediaFile = intent.getStringExtra("jck");
            mp = new MediaPlayer();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (!mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = null;
                    }
                }
            });
            mp.reset();
            try {
                mp.setDataSource(mediaFile);
                mp.prepare();
                mp.start();
            } catch (IOException e) {
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }
}