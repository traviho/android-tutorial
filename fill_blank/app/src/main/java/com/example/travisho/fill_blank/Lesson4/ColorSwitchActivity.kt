package com.example.travisho.fill_blank.Lesson4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_color_switch.*
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.travisho.fill_blank.MainApplication
import com.example.travisho.fill_blank.R
import io.socket.client.IO
import java.net.URISyntaxException
import java.util.Random

class ColorSwitchActivity : AppCompatActivity() {

    var colors:Array<Int>? = null
    val colorTexts:Array<String> = arrayOf("Red", "Orange", "Yellow", "Green", "Blue")
    val MAX_TIME:Long = 10000 // milliseconds
    var colorIndex:Int? = null
    var textIndex:Int? = null
    var score: Int = 0
    var disabled: Boolean = false
    val random = Random()

    private fun connectSocket() {
        val SERVER_URL = (applicationContext as MainApplication).SERVER_URL
        if ((applicationContext as MainApplication).mSocket == null) {
            try {
                (applicationContext as MainApplication).mSocket = IO.socket(SERVER_URL).connect()
            } catch (e: URISyntaxException) {
                Log.d("myTag", e.toString())
            }
        }

        if ((applicationContext as MainApplication).mSocket != null && !((applicationContext as MainApplication).mSocket!!.hasListeners("start")) ) {
            (applicationContext as MainApplication).mSocket?.on("start") { args ->
                runOnUiThread { startCountdown() }
            }
        }
    }

    private fun scorePostRequest(score: String) {
        val SERVER_URL = (applicationContext as MainApplication).SERVER_URL
        val route = "/api/score"
        /* val postRequest = JsonObjectRequest(Request.Method.POST, SERVER_URL + route,
                JSONObject().put("score", score),
                Response.Listener<JSONObject> { response ->
                    Log.d("Response", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("Error.Response", error.toString())
                }
        )
        (applicationContext as MainApplication).queue?.add(postRequest) */
    }

    private fun getRandom(from: Int, to: Int) : Int {
        return random.nextInt(to + 1 - from) + from
    }

    private fun setUI() {
        color_switch_main_layout?.setBackgroundColor(colors!![colorIndex!!])
        color_switch_color_text?.text = colorTexts[textIndex!!]
    }

    private fun setNewRandom() {
        colorIndex = getRandom(0, colors!!.size - 1)
        when (getRandom(0, 1)) {
            0 -> textIndex = colorIndex
            1 -> {
                var nRand:Int = getRandom(0, colors!!.size - 2)
                if (nRand == colorIndex) nRand++
                textIndex = nRand
            }
        }
        setUI()
    }

    private fun evaluateScore(clickedYes: Boolean) {
        if (!disabled) {
            if ((colorIndex == textIndex && clickedYes == true) ||
                    (colorIndex != textIndex && clickedYes == false)) {
                score++
                color_switch_points_text.text = score.toString()
                setNewRandom()
            } else {
                disabled = true
                color_switch_color_text?.text = "You lost!"
            }
        }
    }

    private fun startGame() {
        setNewRandom()
        disabled = false
        score = 0
        color_switch_time_bar.progressDrawable = ContextCompat.getDrawable(applicationContext, R.drawable.progress_white)
        color_switch_yes_btn?.setOnClickListener {
            evaluateScore(true)
        }
        color_switch_no_btn?.setOnClickListener {
            evaluateScore(false)
        }
        color_switch_time_bar.max = MAX_TIME.toInt()
        object : CountDownTimer(MAX_TIME, 10) {

            override fun onTick(millisUntilFinished: Long) {
                color_switch_time_bar.progress = millisUntilFinished.toInt()
                if (millisUntilFinished < .333 * MAX_TIME) {
                    color_switch_time_bar.progressDrawable = ContextCompat.getDrawable(applicationContext, R.drawable.progress_red)
                }
            }

            override fun onFinish() {
                color_switch_time_bar.progress = 0
                disabled = true
                color_switch_color_text.text = "Finished!"
                scorePostRequest(score.toString())
            }
        }.start()
    }

    private fun startCountdown() {
        object : CountDownTimer(4100, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val minusOne:Int = (millisUntilFinished - 1000).toInt()
                color_switch_color_text.text = "" + minusOne / 1000
                if (minusOne <= 1000) {
                    color_switch_color_text.text = "GO!"
                }
            }

            override fun onFinish() {
                startGame()
            }
        }.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_switch)
        supportActionBar!!.hide()
        colors = arrayOf(ContextCompat.getColor(applicationContext, R.color.arena_red),
                ContextCompat.getColor(applicationContext, R.color.arena_orange),
                ContextCompat.getColor(applicationContext, R.color.arena_yellow),
                ContextCompat.getColor(applicationContext, R.color.arena_green),
                ContextCompat.getColor(applicationContext, R.color.arena_blue))
        connectSocket()
        /*
        if ((applicationContext as MainApplication).queue == null) {
            (applicationContext as MainApplication).queue = Volley.newRequestQueue(this)
        }
        */
    }

    override fun onStop() {
        super.onStop()
        (applicationContext as MainApplication).mSocket?.off()
    }
}
