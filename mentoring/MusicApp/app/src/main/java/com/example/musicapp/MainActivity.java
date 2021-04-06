package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();

    Button mBtnStart = null;
    Button mBtnStop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStop = findViewById(R.id.btn_stop);

        mBtnStart.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("MainActivity", "onClick");

        switch (view.getId()) {
            case R.id.btn_start:
                startMusicService();
                break;

            case R.id.btn_stop:
                stopMusicService();
                break;
        }
    }

    private void startMusicService() {
        Log.d(TAG, "startMusicService");
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    private void stopMusicService() {
        Log.d(TAG, "stopMusicService");
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }
}