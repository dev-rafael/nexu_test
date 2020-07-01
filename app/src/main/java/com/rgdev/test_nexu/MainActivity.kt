package com.rgdev.test_nexu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*textiew_text.setOnClickListener {
            textiew_text.animate().apply {
                duration = 1000
                rotationBy(200f)
            }.withEndAction {
                textiew_text.animate().apply {
                    duration = 600
                    rotationBy(-200f)
                }
            }
        }*/
    }
}
