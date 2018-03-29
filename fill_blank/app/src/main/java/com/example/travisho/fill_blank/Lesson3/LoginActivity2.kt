package com.example.travisho.fill_blank.Lesson3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travisho.fill_blank.MainApplication
import com.example.travisho.fill_blank.R
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity2 : AppCompatActivity() {

    private fun loginPostRequest(username: String, password: String) {
        val SERVER_URL = (applicationContext as MainApplication).SERVER_URL
        val route = "/api/login"
        /*val postRequest = JsonObjectRequest(Request.Method.POST, SERVER_URL + route,
                JSONObject().put("username", username).put("password", password),
                Response.Listener<JSONObject> { response ->
                    Log.d("Response", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("Error.Response", error.toString())
                }
        )
        (applicationContext as MainApplication).queue?.add(postRequest) */

        /* Do a one time request here */
    }

    private fun onClickLoginButton() {
        /* Make it grab from EditTexts */
        val username = ""
        val password = ""
        if (username.isBlank() || password.isBlank()) {
            Toast.makeText(this, "missing a field!", Toast.LENGTH_SHORT).show()
        } else {
            loginPostRequest(username, password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        /*if ((applicationContext as MainApplication).queue == null) {
            (applicationContext as MainApplication).queue = Volley.newRequestQueue(this)
        }*/
        login_button.setOnClickListener({
            onClickLoginButton()
        })
    }
}