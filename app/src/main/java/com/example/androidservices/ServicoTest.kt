package com.example.androidservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ServicoTest : Service() {

    val threadList = mutableListOf<Worker>()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.i("Script", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("Script", "onStartCommand")

        threadList.add(
            Worker(startId).apply {
                start()
            }
        )

        //RETURN
        //START_NOT_STICKY -> Se o android precisou encerrar o processo de alguma forma, nao inicie novamente o servico
        //START_STICKY -> Se o android precisou encerrar o processo de alguma forma, inicie novamente o servico mas limpa o valor da intent
        //START_REDELIVER_INTENT -> Se o android precisou encerrar o processo de alguma forma, inicie novamente o servico recuperando o valor da intent

        return super.onStartCommand(intent, flags, startId)
    }


    inner class Worker(val startId : Int) : Thread() {
        var count : Int = 0
        var ativo = true

        override fun run() {

            while (ativo && count < 10) {
                try {
                    sleep(1000)
                } catch (e : InterruptedException) {
                    e.printStackTrace()
                }
                count++
                Log.i("Script", "COUNT$count")
            }

            stopSelf(startId)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        threadList.forEach { it.ativo = false }
    }
}