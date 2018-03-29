package com.example.travisho.fill_blank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.travisho.fill_blank.Lesson1.BasicsActivity
import com.example.travisho.fill_blank.Lesson2.LoginActivity
import com.example.travisho.fill_blank.Lesson3.LoginActivity2
import com.example.travisho.fill_blank.Lesson4.ColorSwitchActivity

class MainActivity : AppCompatActivity(), LessonListFragment.OnLessonListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLessonListFragmentInteraction(position: Int) {
        when(position) {
            0 -> startActivity(Intent(this, BasicsActivity::class.java))
            1 -> startActivity(Intent(this, LoginActivity::class.java))
            2 -> startActivity(Intent(this, LoginActivity2::class.java))
            3 -> startActivity(Intent(this, ColorSwitchActivity::class.java))
        }
    }
}
