package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity

class Memory_game_engine : AppCompatActivity() {

    private var result: String = ""

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

    fun setSound(prefs : SharedPreferences, mp : MediaPlayer){
        val min : Int = prefs.getInt("soundMin", 0)
        val max : Int = prefs.getInt("soundMax", 0)

        mp.setVolume(min.toFloat(), max.toFloat())
    }
}