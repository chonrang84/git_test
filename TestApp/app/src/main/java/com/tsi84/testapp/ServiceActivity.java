package com.tsi84.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tsi84.testapp.service.MusicService;

public class ServiceActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ServiceActivity.class.getSimpleName();

    Button mBtnServiceStart;
    Button mBtnServiceStop;
    Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mBtnServiceStart = findViewById(R.id.btn_service_start);
        mBtnServiceStart.setOnClickListener(this);

        mBtnServiceStop = findViewById(R.id.btn_service_stop);
        mBtnServiceStop.setOnClickListener(this);

        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service_start:
                startMusicService();
                break;

            case R.id.btn_service_stop:
                stopMusicService();
                break;

            case R.id.btn_close:
                finish();
                break;
        }
    }

    private void startMusicService() {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
    }

    private void stopMusicService() {
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);
    }
}
