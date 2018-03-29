package com.example.travisho.complete.Lesson1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.travisho.complete.MainActivity
import com.example.travisho.complete.R
import kotlinx.android.synthetic.main.activity_basics.*

class BasicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basics)

        /**/
        set_text_button.setOnClickListener { hello_text.text = "Hello World" }

        /**/
        back_button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
