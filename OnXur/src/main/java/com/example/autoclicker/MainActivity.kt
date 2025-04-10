package com.example.autoclicker

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var isClicking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputX = findViewById<EditText>(R.id.inputX)
        val inputY = findViewById<EditText>(R.id.inputY)
        val inputDelay = findViewById<EditText>(R.id.inputDelay)
        val btnStart = findViewById<Button>(R.id.btnStart)

        btnStart.setOnClickListener {
            if (!isClicking) {
                val x = inputX.text.toString().toIntOrNull() ?: return@setOnClickListener
                val y = inputY.text.toString().toIntOrNull() ?: return@setOnClickListener
                val delay = inputDelay.text.toString().toLongOrNull() ?: return@setOnClickListener

                handler = Handler(mainLooper)
                runnable = object : Runnable {
                    override fun run() {
                        ClickAccessibilityService.performClick(x, y)
                        handler?.postDelayed(this, delay)
                    }
                }
                handler?.post(runnable!!)
                btnStart.text = "Durdur"
                isClicking = true
            } else {
                handler?.removeCallbacks(runnable!!)
                btnStart.text = "Başlat"
                isClicking = false
            }
        }
    }
}
