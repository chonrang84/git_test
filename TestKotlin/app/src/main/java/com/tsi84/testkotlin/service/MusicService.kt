package com.tsi84.testkotlin.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.tsi84.testkotlin.R

class MusicService: Service() {
    private val TAG = MusicService::class.java.simpleName
    private var mPlayer: MediaPlayer? = null    // sample play for MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        // Return the communication channel to the service.
        // May return null if clients can not bind to the service.
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

        // mp3 sample url
        // https://drive.google.com/file/d/1IDl3bUKMBOcpbvQu6I2g95iqnYyQ0qvf/view?usp=sharing
        mPlayer = MediaPlayer.create(this, R.raw.sample)    // play file: sample.mp3
        mPlayer!!.isLooping = true  // looping
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        if (mPlayer != null) {
            Log.d(TAG, "Music stopped..")
            mPlayer!!.stop()    // player stop
        }
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mPlayer != null) {
            Log.d(TAG, "onStartCommand - Music start!!")
            mPlayer!!.start()   // player start
        }
        return super.onStartCommand(intent, flags, startId)
    }
}