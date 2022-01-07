package com.example.rememo.games.helperClasses

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.games.gamechoices.LvlDesign

class Highscorehelper(context: Context): AppCompatActivity() {

private val con= context
    private val lvd = LvlDesign()

    fun retrieveSharedPreferences(lvls:  ArrayList<LinearLayout>,lvlsPic: ArrayList<ImageView>,game : String) {

        var preferences: SharedPreferences? = null
        when (game) {
            "memory" -> preferences = con.getSharedPreferences("Levels_Memory", 0)
            "reaction" -> preferences = con.getSharedPreferences("Levels_Reaction", 0)
            "motivity" -> preferences = con.getSharedPreferences("Levels_Motivity", 0)
        }

        if (preferences != null) {

            if (preferences.getString("lvl_1_checked", "false").equals("true")) {
                    lvd.lvlpassed(lvls[0],lvlsPic[0])
            }

            if (preferences.getString("lvl_2_checked", "false").equals("true")) {
                lvd.lvlpassed(lvls[1],lvlsPic[1])
            }

            if (preferences.getString("lvl_3_checked", "false").equals("true")) {
                lvd.lvlpassed(lvls[2],lvlsPic[2])
            }

            if (preferences.getString("lvl_4_checked", "false").equals("true")) {
                lvd.lvlpassed(lvls[3],lvlsPic[3])
            }

            if (preferences.getString("lvl_5_checked", "false").equals("true")) {
                lvd.lvlpassed(lvls[4],lvlsPic[4])
            }
        }
    }



}