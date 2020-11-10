package com.tsi84.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = FirstActivity.class.getSimpleName();

    Button mBtnStart;
    Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mBtnStart = findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);

        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                showLoadingDialog();
                break;

            case R.id.btn_close:
                finish();
                break;
        }
    }
}
