package com.example.rememo.games.memorylvls

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.DialogHelper
import com.example.rememo.games.pauseScreens.Pause

class MemoryGameEngine(context: Context) : AppCompatActivity() {
    private var con = context
    private var contextHelper = ContextHelper(con)
    private var result: String = ""
    private var resultInput : String = ""
    private lateinit var mp : MediaPlayer
    private val dialogHelper = DialogHelper(con)

    private fun selectRandomButtons(buttonchoice: Array<String>, howMuch : Int): ArrayList<String> {
        var randomNumber: Int
        val buttons = ArrayList<String>()

        for (i in 0 until howMuch) {
            randomNumber = (buttonchoice.indices).random()
            buttons.add(buttonchoice[randomNumber])
        }
        return buttons
    }

    fun saveAsString(buttonChoice: Array<String>, howMuch: Int): String {
        val arrayList: ArrayList<String> = selectRandomButtons(buttonChoice, howMuch)

        for (i in 0 until arrayList.size ) {
            result += arrayList[i]
        }
        return result
    }

    fun setSound(prefs : SharedPreferences){
        mp = MediaPlayer.create(con, R.raw.clapping)
        val min : Int = prefs.getInt("soundMin", 0)
        val max : Int = prefs.getInt("soundMax", 0)

        mp.setVolume(min.toFloat(), max.toFloat())
    }

    private fun compareResults(writeIntoSP : String, c : Class<*>, title : String){
        if(result == resultInput){
            mp.start()
            writeIntoSharedPrefs(writeIntoSP)
            dialogHelper.levelPassed(title, "You passed this level", "go back to the game choice", MemoryGame::class.java)
        }else{
            dialogHelper.levelFailed(title, "You're noob try again", "Retry", c)
        }
    }

    private fun writeIntoSharedPrefs(lvl : String){
        val prefs : SharedPreferences = con.getSharedPreferences("Levels_Memory", 0)
        prefs
            .edit()
            .putString(lvl, "true")
            .apply()
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }

    fun init (buttonArray: ArrayList<Button>, howMuch : Int, lvl : String, c : Class<*>, title : String){
        for (i in 0 until buttonArray.size ) {
            buttonArray[i].setOnClickListener {setClickListener(i, buttonArray, howMuch, lvl, c, title)}
        }
    }

    private fun setClickListener(i : Int, buttonArray: ArrayList<Button>, howMuch: Int, lvl : String, c : Class<*>, title : String){
        resultInput += buttonArray[i].text
        if(resultInput.length == howMuch){
            compareResults(lvl, c, title)
            deactivate(buttonArray)
        }
    }

    private fun deactivate(buttonArray : ArrayList<Button>){
        for (i in 0 until buttonArray.size ) {
            buttonArray[i].setOnClickListener {}
        }
    }

    fun goToPause(c : Class<*>) {
        contextHelper.gameIntent("memory", Pause::class.java, c)
    }


    fun fullScreen(window : Window) {
        val win = window
        val decorView = win.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }


}