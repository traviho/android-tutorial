package com.example.travisho.complete.Lesson3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.travisho.complete.MainApplication
import com.example.travisho.complete.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity2 : AppCompatActivity() {

    private fun loginPostRequest(username: String, password: String) {
        val SERVER_URL = (applicationContext as MainApplication).SERVER_URL
        val route = "/api/login"
        val postRequest = JsonObjectRequest(Request.Method.POST, SERVER_URL + route,
                JSONObject().put("username", username).put("password", password),
                Response.Listener<JSONObject> { response ->
                    Log.d("Response", response.toString())
                },
                Response.ErrorListener { error ->
                    Log.d("Error.Response", error.toString())
                }
        )
        (applicationContext as MainApplication).queue?.add(postRequest)
    }

    private fun onClickLoginButton() {
        val username = username_login.text.toString()
        val password = password_login.text.toString()
        if (username.isBlank() || password.isBlank()) {
            Toast.makeText(this, "missing a field!", Toast.LENGTH_SHORT).show()
        } else {
            loginPostRequest(username, password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if ((applicationContext as MainApplication).queue == null) {
            (applicationContext as MainApplication).queue = Volley.newRequestQueue(this)
        }
        login_button.setOnClickListener({
            onClickLoginButton()
        })
    }
}