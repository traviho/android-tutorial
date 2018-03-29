package com.example.travisho.fill_blank

import android.app.Application

class MainApplication: Application() {
    var mSocket:io.socket.client.Socket? = null
    // var queue: RequestQueue? = null
    val SERVER_URL = "http://eb-node-express-socket-dev.us-east-1.elasticbeanstalk.com"
}