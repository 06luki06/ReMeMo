package com.example.rememo.games.helperClasses

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.games.gamechoices.LvlDesign
import com.example.rememo.games.memorylvls.*
import com.example.rememo.games.motivitylvls.*
import com.example.rememo.games.reactionlvls.*

class GameChoiceHelper(context : Context) : AppCompatActivity() {
    private val con = context
    private var lvlDesign = LvlDesign()
    private var contextHelper = ContextHelper(con)
    private val dialogHelper = DialogHelper(con)
    private lateinit var mp : MediaPlayer
    private var gameFinal : String = ""

    fun retrieveSharedPreferences(lvls : ArrayList<Button>, game : String) {
        mp = MediaPlayer.create(con, R.raw.clapping)
        gameFinal = game

        var preferences: SharedPreferences? = null
        when (game) {
            "memory" -> preferences = con.getSharedPreferences("Levels_Memory", 0)
            "reaction" -> preferences = con.getSharedPreferences("Levels_Reaction", 0)
            "motivity" -> preferences = con.getSharedPreferences("Levels_Motivity", 0)
        }

        if (preferences != null) {
            if (preferences.getString("lvl_1_checked", "false").equals("false")) {
                lvlDesign.changeBackgroundToActive(lvls[0])
                activateLvl(1, lvls[0])
            }

            if (preferences.getString("lvl_1_checked", "false").equals("true")) {
                lvlDesign.lvlMastered(lvls[0])
                lvlDesign.changeBackgroundToActive(lvls[1])
                activateLvl(1, lvls[0])
                activateLvl(2, lvls[1])
            }

            if (preferences.getString("lvl_2_checked", "false").equals("true")) {
                lvlDesign.lvlMastered(lvls[1])
                lvlDesign.changeBackgroundToActive(lvls[2])
                activateLvl(3, lvls[2])
            }

            if (preferences.getString("lvl_3_checked", "false").equals("true")) {
                lvlDesign.lvlMastered(lvls[2])
                lvlDesign.changeBackgroundToActive(lvls[3])
                activateLvl(4, lvls[3])
            }

            if (preferences.getString("lvl_4_checked", "false").equals("true")) {
                lvlDesign.lvlMastered(lvls[3])
                lvlDesign.changeBackgroundToActive(lvls[4])
                activateLvl(5, lvls[4])
            }

            if (preferences.getString("lvl_5_checked", "false").equals("true")) {
                lvlDesign.lvlMastered(lvls[4])
                activateLvl(5, lvls[4])
                dialogHelper.levelsCompleted("Good job!", "good job guy", "play levels again")
                mp.start()
            }
        }
    }

    private fun activateLvl(lvlNumbers : Int, lvl : Button){
        lvl.setOnClickListener{goToLvls(lvlNumbers)}
    }

    private fun goToLvls(lvl : Int){
        var c : Class<*>? = null
        when(gameFinal){
            "memory" -> {
                when (lvl) {
                    1 -> c = MemoryLvl1::class.java
                    2 -> c = MemoryLvl2::class.java
                    3 -> c = MemoryLvl3::class.java
                    4 -> c = MemoryLvl4::class.java
                    5 -> c = MemoryLvl5::class.java
                }
            }
            "reaction" -> {
                when (lvl) {
                    1 -> c = ReactionLvl1::class.java
                    2 -> c = ReactionLvl2::class.java
                    3 -> c = ReactionLvl3::class.java
                    4 -> c = ReactionLvl4::class.java
                    5 -> c = ReactionLvl5::class.java
                }
            }
            "motivity" -> {
                when (lvl) {
                    1 -> c = Motivity_lvl1::class.java
                    2 -> c = Motivity_lvl2::class.java
                    3 -> c = Motivity_lvl3::class.java
                    4 -> c = Motivity_lvl4::class.java
                    5 -> c = Motivity_lvl5::class.java
                }
            }
        }

        if (c != null) {
            contextHelper.startIntent(c, false, flag = false)
        }
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }
}