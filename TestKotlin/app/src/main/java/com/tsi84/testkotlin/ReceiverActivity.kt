package com.tsi84.testkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class ReceiverActivity: BaseActivity() {
    private val TAG = ReceiverActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        registerReceiver()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        unRegisterReceiver()
        super.onDestroy()
    }

    private fun registerReceiver() {
        Log.d(TAG, "registerReceiver")
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(mNetworkReceiver, intentFilter)
    }

    private fun unRegisterReceiver() {
        Log.d(TAG, "unRegisterReceiver")
        mNetworkReceiver?.let { unregisterReceiver(it) }
    }

    // network on/off check BroadcastReceiver
    private val mNetworkReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            Log.d(TAG, "onReceive action: $action")
            if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
                )
                if (!noConnectivity) {
                    Log.d(TAG, "connected")
                    Toast.makeText(applicationContext, "wifi on", Toast.LENGTH_LONG).show()
                } else {
                    Log.d(TAG, "disconnected")
                    Toast.makeText(applicationContext, "wifi off", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}