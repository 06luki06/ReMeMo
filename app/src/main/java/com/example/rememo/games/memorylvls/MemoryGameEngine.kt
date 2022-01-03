package com.example.rememo.games.memorylvls

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.pauseScreens.Pause

class MemoryGameEngine(context: Context) : AppCompatActivity() {
    private var con = context
    private var contextHelper = ContextHelper(con)
    private var result: String = ""
    private var resultInput : String = ""
    private lateinit var mp : MediaPlayer

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

    fun compareResults(writeIntoSP : String, c : Class<*>, title : String){
        if(result == resultInput){
            mp.start()
            val builder = AlertDialog.Builder(con)
            builder.setTitle(title)
            builder.setMessage("Great, you have nailed it!")
            builder.setNeutralButton("Back To Games"){_, _ ->
                contextHelper.startIntent(MemoryGame::class.java, true, flag = false)
                writeIntoSharedPrefs(writeIntoSP)
            }.show()

        }else{
            val builder = AlertDialog.Builder(con)
            builder.setTitle(title)
            builder.setMessage("You are a noob, try again")
            builder.setNeutralButton("Retry"){_, _ ->
                contextHelper.startIntent(c, true, flag = false)
            }.show()
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

    fun deactivate(buttonArray : ArrayList<Button>){
        for (i in 0 until buttonArray.size ) {
            buttonArray[i].setOnClickListener {}
        }
    }

    fun goToPause() {
        contextHelper.gameIntent("memory", Pause::class.java)
    }
}