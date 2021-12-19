package com.example.androidservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.start_service).setOnClickListener {
            startService()
        }

        findViewById<Button>(R.id.stop_service).setOnClickListener {
            stopService()
        }

    }

    fun startService() {
        startService(
            Intent().apply {
                `package` = packageName
                action = "SERVICO_TEST"
            }
        )

    }

    fun stopService() {
        stopService(
            Intent().apply {
                `package` = packageName
                action = "SERVICO_TEST"
            }
        )
    }
}