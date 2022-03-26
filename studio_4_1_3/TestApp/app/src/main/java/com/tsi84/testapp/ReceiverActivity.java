package com.tsi84.testapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ReceiverActivity extends BaseActivity {

    private static final String TAG = ReceiverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        registerReceiver();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        unRegisterReceiver();
        super.onDestroy();
    }

    private void registerReceiver() {
        Log.d(TAG, "registerReceiver");
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkReceiver, intentFilter);
    }

    private void unRegisterReceiver() {
        Log.d(TAG, "unRegisterReceiver");
        if (mNetworkReceiver != null) {
            unregisterReceiver(mNetworkReceiver);
        }
    }

    // network on/off check BroadcastReceiver
    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive action: " + action);

            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                boolean noConnectivity = intent.getBooleanExtra(
                        ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
                if (!noConnectivity) {
                    Log.d(TAG, "connected");
                    Toast.makeText(getApplicationContext(), "wifi on", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "disconnected");
                    Toast.makeText(getApplicationContext(), "wifi off", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
}
