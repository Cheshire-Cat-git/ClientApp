package com.example.client_app.utils

object Constants {
    const val SERVICE_PACKAGE = "com.example.service_app"
    
    // Commands TO Service
    const val ACTION_START_SERVICE = "com.example.service_app.START"
    const val ACTION_STOP_SERVICE = "com.example.service_app.STOP"
    const val ACTION_TOGGLE_READING = "com.example.service_app.TOGGLE_READING"
    const val ACTION_GET_STATUS = "com.example.service_app.GET_STATUS"

    // Updates FROM Service
    const val ACTION_HEX_CODE_RECEIVED = "com.example.service_app.HEX_CODE"
    const val ACTION_STATUS_UPDATED = "com.example.service_app.STATUS_UPDATE"

    // Extra keys
    const val EXTRA_HEX_CODE = "extra_hex_code"
    const val EXTRA_STATUS = "extra_status"
}
