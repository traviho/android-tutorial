package com.example.travisho.complete

import android.app.Application
import com.android.volley.RequestQueue

class MainApplication: Application() {
    var mSocket:io.socket.client.Socket? = null
    var queue: RequestQueue? = null
    val SERVER_URL = "http://172.16.87.61:3000"
}