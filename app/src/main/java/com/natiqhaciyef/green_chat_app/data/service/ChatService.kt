package com.natiqhaciyef.greenchatapp.data.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ChatService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    val TAG = "Custom service"

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getStringExtra("EXTRA_DATA")
        data?.let {
            //
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service killed...")
    }
}