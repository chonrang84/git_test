package com.tsi84.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    Button mBtnFirst;
    Button mBtnSecond;
    Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnFirst = findViewById(R.id.btn_first);
        mBtnFirst.setOnClickListener(this);

        mBtnSecond = findViewById(R.id.btn_second);
        mBtnSecond.setOnClickListener(this);

        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);

        /*
        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "## onClick ## close");
                finish();
            }
        });
        */
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first:
                Log.d(TAG, "## onClick ## first");
                onFirstClicked();
                break;

            case R.id.btn_second:
                Log.d(TAG, "## onClick ## second");
                onSecondClicked();
                break;

            case R.id.btn_close:
                Log.d(TAG, "## onClick ## close");
                finish();
                break;
        }
    }

    private void onFirstClicked() {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    private void onSecondClicked() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}