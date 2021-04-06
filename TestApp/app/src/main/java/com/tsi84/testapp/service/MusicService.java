package com.tsi84.testapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.tsi84.testapp.R;

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mPlayer;    // sample play for MediaPlayer

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
        mPlayer.setLooping(true);   // looping
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (mPlayer != null) {
            Log.d(TAG, "Music stopped..");
            mPlayer.stop();     // player stop
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer != null) {
            Log.d(TAG, "onStartCommand - Music start!!");
            mPlayer.start();    // player start
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
