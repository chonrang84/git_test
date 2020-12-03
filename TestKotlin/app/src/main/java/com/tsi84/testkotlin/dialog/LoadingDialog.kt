package com.tsi84.testkotlin.dialog

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import com.tsi84.testkotlin.R

class LoadingDialog @JvmOverloads constructor(context: Context?, message: String? = null) : Dialog(context!!) {
    private val mTextMessage: TextView?
    fun setTextMessage(message: String?) {
        if (mTextMessage != null) {
            mTextMessage.text = message
        }
    }

    init {

        // set property
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // layout
        setContentView(R.layout.loading_dialog)
        mTextMessage = findViewById<View>(R.id.progress_text) as TextView
        message?.let { setTextMessage(it) }
    }
}