package com.example.androidservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastTest : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("Script", "onReceive")
        //context?.startService(Intent("SERVICO_TEST"))
    }
}