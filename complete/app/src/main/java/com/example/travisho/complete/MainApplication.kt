package com.example.travisho.complete

import android.app.Application
import com.android.volley.RequestQueue

class MainApplication: Application() {
    var mSocket:io.socket.client.Socket? = null
    var queue: RequestQueue? = null
    val SERVER_URL = "http://eb-node-express-socket-dev.us-east-1.elasticbeanstalk.com"
}