package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LvlDesign : AppCompatActivity() {

    fun changeBackgroundToActive(lvl : Button){
        lvl.setBackgroundColor(Color.YELLOW)
    }

    fun lvlMastered(lvl : Button){
        lvl.setBackgroundColor(Color.GREEN)
    }
}