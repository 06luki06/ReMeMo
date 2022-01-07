package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R

class LvlDesign : AppCompatActivity() {



    fun changeBackgroundToActive(lvl : Button){
        lvl.setBackgroundColor(Color.YELLOW)
    }

    fun lvlMastered(lvl : Button){
        lvl.setBackgroundColor(Color.GREEN)
    }

    fun lvlpassed(llLvl: LinearLayout,imlvl:ImageView){
        llLvl.setBackgroundColor(Color.GREEN)
        imlvl.setImageResource(R.drawable.withhook)
    }
}