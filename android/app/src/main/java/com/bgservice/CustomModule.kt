package com.bgservice

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class CustomModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val reactContext: ReactApplicationContext = reactContext

    override fun getName(): String {
        return "CustomModule"
    }

    @ReactMethod
    fun checkBridge() {
        Log.d("log","Hello World")
    }

    @ReactMethod
    fun startService() {
        val serviceIntent = Intent(reactContext, BackgroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            reactContext.startForegroundService(serviceIntent)
        } else {
            reactContext.startService(serviceIntent)
        }
    }

    @ReactMethod
    fun stopService() {
        val serviceIntent = Intent(reactContext, BackgroundService::class.java)
        // Ensure the service is properly stopped
        reactContext.stopService(serviceIntent)
    }

    @ReactMethod
    fun isBackgroundServiceRunning(promise: Promise) {
        try {
            val activityManager = reactApplicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)

            for (service in runningServices) {
                if (BackgroundService::class.java.name == service.service.className) {
                    promise.resolve(true)
                    return
                }
            }
            promise.resolve(false)
        } catch (e: Exception) {
            promise.reject("ERROR", e.message)
        }
    }
}
