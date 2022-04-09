package com.tsi84.testkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.airbnb.lottie.LottieAnimationView
import com.tsi84.testkotlin.service.MusicService

class ServiceActivity: BaseActivity(), View.OnClickListener {
    private val TAG = ServiceActivity::class.java.simpleName

    var mBtnServiceStart: Button? = null
    var mBtnServiceStop: Button? = null
    var mBtnClose: Button? = null
    var mViewAnim: LottieAnimationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        mBtnServiceStart = findViewById(R.id.btn_service_start)
        mBtnServiceStart?.setOnClickListener(this)
        mBtnServiceStop = findViewById(R.id.btn_service_stop)
        mBtnServiceStop?.setOnClickListener(this)
        mBtnClose = findViewById(R.id.btn_close)
        mBtnClose?.setOnClickListener(this)
        mViewAnim = findViewById(R.id.image_anim)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_service_start -> startMusicService()
            R.id.btn_service_stop -> stopMusicService()
            R.id.btn_close -> finish()
        }
    }

    private fun startMusicService() {
        // start MusicService
        val intent = Intent(this, MusicService::class.java)
        startService(intent)
        // lottie play animation
        mViewAnim?.playAnimation()
    }

    private fun stopMusicService() {
        // stop MusicService
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
        // lottie pause animation
        mViewAnim?.pauseAnimation()
    }
}