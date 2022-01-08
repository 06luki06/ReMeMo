package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R

class LvlDesign : AppCompatActivity() {



    fun changeBackgroundToActive(lvl : Button){
        lvl.setBackgroundColor(Color.parseColor("#FFD700")) // Yellow
    }

    fun lvlMastered(lvl : Button){
        lvl.setBackgroundColor(Color.parseColor("#CAFF70")) //Green
    }

    fun lvlpassed(llLvl: LinearLayout,imlvl:ImageView){
        llLvl.setBackgroundColor(Color.parseColor("#CAFF70"))
        imlvl.setImageResource(R.drawable.withhook)
    }
}