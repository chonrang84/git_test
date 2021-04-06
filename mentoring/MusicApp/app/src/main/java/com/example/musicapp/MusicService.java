package com.example.musicapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mPlayer = null; // sample play for MediaPlayer

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.
        // May return null if clients can not bind to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        // mp3 sample url
        // https://drive.google.com/file/d/1IDl3bUKMBOcpbvQu6I2g95iqnYyQ0qvf/view?usp=sharing
        mPlayer = MediaPlayer.create(this, R.raw.sample);   // play file: sample.mp3
        mPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        mPlayer.start(); // player start
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        mPlayer.stop(); // player stop
        super.onDestroy();
    }
}
