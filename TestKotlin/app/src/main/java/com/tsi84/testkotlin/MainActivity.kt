package com.tsi84.testkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : BaseActivity(), View.OnClickListener {
    var mBtnFirst: Button? = null
    var mBtnSecond: Button? = null
    var mBtnThird: Button? = null
    var mBtnClose: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnFirst = findViewById(R.id.btn_first)
        mBtnFirst?.setOnClickListener(this)
        mBtnSecond = findViewById(R.id.btn_second)
        mBtnSecond?.setOnClickListener(this)
        mBtnThird = findViewById(R.id.btn_third)
        mBtnThird?.setOnClickListener(this)
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener(this)

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

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_first -> {
                Log.d(TAG, "## onClick ## first")
                onClickedActivity(FirstActivity::class.java)
            }
            R.id.btn_second -> {
                Log.d(TAG, "## onClick ## second")
                //                onSecondClicked();
                onClickedActivity(SecondActivity::class.java)
            }
            R.id.btn_third -> {
                Log.d(TAG, "## onClick ## third")
                onClickedActivity(ThirdActivity::class.java)
            }
            R.id.btn_close -> {
                Log.d(TAG, "## onClick ## close")
                finish()
            }
        }
    }

    private fun onClickedActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    private fun onFirstClicked() {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    private fun onSecondClicked() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}