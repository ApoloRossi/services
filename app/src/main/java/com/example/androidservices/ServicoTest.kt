package com.example.androidservices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.lang.Thread.sleep

class ServicoTest : Service() {

    val controller = Controller()

    var mAtivo = false
    var mCount = 0

    override fun onBind(intent: Intent?): IBinder = controller

    interface CountListener {
        fun getCount() : Int
    }

    var countListener = object : CountListener {
        override fun getCount() = mCount
    }

    inner class Controller : Binder() {
        fun getCountListener() : CountListener = countListener
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("Script", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Script", "onStartCommand")
        startThread()

        return super.onStartCommand(intent, flags, startId)
    }

    fun startThread() {
        mAtivo = true
        mCount = 0
        Thread {
            while(mAtivo && mCount < 1000) {
                sleep(1000)
                mCount++
                Log.i("Script", "COUNT$mCount")
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Script", "Destroy")
        mAtivo = false
    }
}