package com.ahmedmamdouh13.duration.view.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    private var FLAGS = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            // Set the content to appear under the system bars so that the
            // content doesn't resize when the system bars hide and show.
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            // Hide the nav bar and status bar
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
    internal lateinit var timeZone: String
    abstract val getContentView: Int

    lateinit var   spEdit:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()
        setContentView(getContentView)
        sharedPref()
        onViewCreated(savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            fullScreen()
        } else {
            // When PopupMenu appears, the current Activity looses the focus
            setFlagsOnThePeekView() // Hijack to the current peek view, apply the Flags on it
        }
    }

    @SuppressLint("PrivateApi")
    fun setFlagsOnThePeekView() {
        try {
            val wmgClass = Class.forName("android.view.WindowManagerGlobal")
            val wmgInstance = wmgClass.getMethod("getInstance").invoke(null)
            val viewsField = wmgClass.getDeclaredField("mViews")
            viewsField.isAccessible = true

            val views = viewsField.get(wmgInstance) as ArrayList<View>
            // When the popup appears, its decorView is the peek of the stack aka last item
            views.last().apply {
                systemUiVisibility = FLAGS
                setOnSystemUiVisibilityChangeListener {
                    systemUiVisibility = FLAGS
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun fullScreen() {
        window.decorView.systemUiVisibility = FLAGS
    }

    private fun sharedPref() {
        spEdit =  this.getSharedPreferences("Duration", Context.MODE_PRIVATE)
        timeZone = (getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso
        timeZone = spEdit.getString("timezone",timeZone)
        println(timeZone)
    }

    abstract fun onViewCreated(savedInstanceState: Bundle?)
    fun displayMsg(msg: String,rootView: View){
        Snackbar.make(rootView,msg,Snackbar.LENGTH_LONG).show()
    }
}