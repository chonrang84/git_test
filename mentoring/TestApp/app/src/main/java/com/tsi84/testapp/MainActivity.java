package com.tsi84.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    Button mBtnFirst;
    Button mBtnSecond;
    Button mBtnThird;
    Button mBtnService;
    Button mBtnReceiver;
    Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnFirst = findViewById(R.id.btn_first);
        mBtnFirst.setOnClickListener(this);

        mBtnSecond = findViewById(R.id.btn_second);
        mBtnSecond.setOnClickListener(this);

        mBtnThird = findViewById(R.id.btn_third);
        mBtnThird.setOnClickListener(this);

        mBtnService = findViewById(R.id.btn_service);
        mBtnService.setOnClickListener(this);

        mBtnReceiver = findViewById(R.id.btn_receiver);
        mBtnReceiver.setOnClickListener(this);

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
                onClickedActivity(FirstActivity.class);
                break;

            case R.id.btn_second:
                Log.d(TAG, "## onClick ## second");
//                onSecondClicked();
                onClickedActivity(SecondActivity.class);
                break;

            case R.id.btn_third:
                Log.d(TAG, "## onClick ## third");
                onClickedActivity(ThirdActivity.class);
                break;

            case R.id.btn_service:
                Log.d(TAG, "## onClick ## service");
                onClickedActivity(ServiceActivity.class);
                break;

            case R.id.btn_receiver:
                Log.d(TAG, "## onClick ## receiver");
                onClickedActivity(ReceiverActivity.class);
                break;

            case R.id.btn_close:
                Log.d(TAG, "## onClick ## close");
                finish();
                break;
        }
    }

    private void onClickedActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}