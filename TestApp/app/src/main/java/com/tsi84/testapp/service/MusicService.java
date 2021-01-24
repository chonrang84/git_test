package com.tsi84.testapp.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.tsi84.testapp.R;

public class MusicService extends Service {
    private static final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        // mp3 sample url
        // https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_700KB.mp3
        mPlayer = MediaPlayer.create(this, R.raw.sample);
        mPlayer.setLooping(true);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (mPlayer != null) {
            Log.d(TAG, "Music stopped..");
            mPlayer.stop();
        }

        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer != null) {
            Log.d(TAG, "onStartCommand - Music start!!");
            mPlayer.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
