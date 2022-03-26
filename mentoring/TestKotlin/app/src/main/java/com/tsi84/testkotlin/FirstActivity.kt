package com.tsi84.testkotlin

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import com.tsi84.testkotlin.model.ConstVariables

class FirstActivity : BaseActivity(), View.OnClickListener {
    var mBtnStart: Button? = null
    var mBtnClose: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        mBtnStart = findViewById(R.id.btn_start)
        mBtnStart?.setOnClickListener(this)
        // same code
//        if (mBtnStart != null) {
//            mBtnStart!!.setOnClickListener(this)
//        }
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_start -> {
                showLoadingDialog()
                Handler().postDelayed(mRunnableHideDialog, ConstVariables.VALUE_OF_MAX_LOADING.toLong())
            }
            R.id.btn_close -> finish()
        }
    }

    var mRunnableHideDialog = Runnable { hideLoadingDialog() }

    companion object {
        private val TAG = FirstActivity::class.java.simpleName
    }
}