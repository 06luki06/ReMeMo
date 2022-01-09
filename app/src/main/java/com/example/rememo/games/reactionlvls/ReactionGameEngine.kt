package com.example.rememo.games.reactionlvls

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.pauseScreens.Pause
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import com.example.rememo.R
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.games.helperClasses.DialogHelper

class ReactionGameEngine(context : Context) : AppCompatActivity(){
    private val con = context
    private var contextHelper = ContextHelper(con)
    private var dialogHelper = DialogHelper(con)
    private lateinit var clapping : MediaPlayer
    private var timeTotal : Int = 0
    private var caughtFlies : Int = 0
    private var fliesToHit : Int = 0


    fun goToPause(c : Class<*>){
        contextHelper.gameIntent("reaction", Pause::class.java, c)
    }

    fun initData(t : Int , cF : Int, fTH : Int){
        timeTotal = t
        caughtFlies = cF
        fliesToHit = fTH
    }

    fun failedLevel(time : Int, caughtF : Int, titleHeader : String, c: Class<*>) : Boolean{
        return if(time == 0 && caughtF < fliesToHit){
            gameOverScreen(titleHeader, c)
            true
        }else{
            false
        }
    }

    fun checkIfLevelPassed(caughtFlies : Int) : Boolean{
        return caughtFlies >= fliesToHit
    }

    fun levelPassed(titleHeader : String, lvl : String){
        clapping.start()
        writeIntoSharedPrefs(lvl)

        val builder = AlertDialog.Builder(con)
        builder.setTitle(titleHeader)
        builder.setMessage("Great, you have nailed it")
        builder.setNeutralButton("go back to the levels"){_, _ ->
            contextHelper.startIntent(ReactionGame::class.java, true, flag = true)
        }.show()
    }

    private fun gameOverScreen(titleHeader: String, c : Class<*>){
        val builder = AlertDialog.Builder(con)
        builder.setTitle(titleHeader)
        builder.setMessage("Oops, you did a poor job")
        builder.setNeutralButton("Try Again"){_, _ ->
            contextHelper.startIntent(c, true, flag = true)
        }.show()
    }

    fun fullScreen(window : Window, prefs: SharedPreferences?) {
        clapping = MediaPlayer.create(con, R.raw.clapping)
        if (prefs != null) {
            setSound(prefs)
        }
        val win = window
        val decorView = win.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    private fun setSound(prefs : SharedPreferences){

        val min : Int = prefs.getInt("soundMin", 0)
        val max : Int = prefs.getInt("soundMax", 0)

        clapping.setVolume(min.toFloat(), max.toFloat())
    }


    private fun writeIntoSharedPrefs(lvl : String){
        val prefs : SharedPreferences = con.getSharedPreferences("Levels_Reaction", 0)
        prefs
            .edit()
            .putString(lvl, "true")
            .apply()
    }



    override fun onDestroy(){
        clapping.release()
        super.onDestroy()
    }


}