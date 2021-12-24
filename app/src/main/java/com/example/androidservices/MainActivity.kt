package com.example.androidservices

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var controller: ServicoTest.CountListener? = null

    val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            controller = (service as ServicoTest.Controller).getCountListener()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindService(
            Intent("SERVICO_TEST").apply {
                `package` = packageName
            },
            connection,
            BIND_AUTO_CREATE
        )

        setupViews()

    }

    private fun setupViews() {
        findViewById<Button>(R.id.print_count).setOnClickListener {
            Toast.makeText(
                this,
                "Count ${controller?.getCount()}",
                Toast.LENGTH_SHORT
            ).show()
        }

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
                action = "SERVICO_TEST"
                `package` = packageName
            }
        )

    }

    fun stopService() {
        stopService(
            Intent().apply {
                action = "SERVICO_TEST"
                `package`= packageName
            }
        )
        unbindService(connection)
    }
}