package com.example.cusormoving

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var pointerInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pointerInfoTextView = findViewById(R.id.pointerInfoTextView)

        // Set up touch event listener
        val rootView = findViewById<View>(android.R.id.content)
        rootView.setOnTouchListener { _, event ->
            handleTouchEvent(event)
            true
        }
    }

    private fun handleTouchEvent(event: MotionEvent) {
        val pointerInfo = StringBuilder()
        for (i in 0 until event.pointerCount) {
            pointerInfo.append("Pointer ${event.getPointerId(i)}: X=${event.getX(i)}, Y=${event.getY(i)}\n")
        }

        // Update the UI with pointer information
        lifecycleScope.launch(Dispatchers.Main) {
            pointerInfoTextView.text = pointerInfo.toString()
        }
    }
}
