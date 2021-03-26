package com.tsi84.testkotlin.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.tsi84.testkotlin.R

class MusicService: Service() {
    private val TAG = MusicService::class.java.simpleName
    private var mPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        // mp3 sample url
        // https://file-examples-com.github.io/uploads/2017/11/file_example_MP3_700KB.mp3
        mPlayer = MediaPlayer.create(this, R.raw.sample)
        mPlayer!!.isLooping = true
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        if (mPlayer != null) {
            Log.d(TAG, "Music stopped..")
            mPlayer!!.stop()
        }
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mPlayer != null) {
            Log.d(TAG, "onStartCommand - Music start!!")
            mPlayer!!.start()
        }
        return super.onStartCommand(intent, flags, startId)
    }
}