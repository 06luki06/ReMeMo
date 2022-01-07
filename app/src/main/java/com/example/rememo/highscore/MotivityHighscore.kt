package com.example.rememo.highscore

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryHighscoreBinding
import com.example.rememo.databinding.MotivityHighscoreBinding
import com.example.rememo.games.helperClasses.Highscorehelper

class MotivityHighscore : AppCompatActivity(){

    private lateinit var bindingMotivityHighscore: MotivityHighscoreBinding
    private val highscorehelper = Highscorehelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityHighscore = MotivityHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingMotivityHighscore.root)

        val prefs : SharedPreferences = getSharedPreferences("Levels_Motivity",0)

        val llLvl1 : LinearLayout = bindingMotivityHighscore.llMotivityLvl1
        val llLvl2 : LinearLayout = bindingMotivityHighscore.llMotivityLvl2
        val llLvl3 : LinearLayout = bindingMotivityHighscore.llMotivityLvl3
        val llLvl4 : LinearLayout = bindingMotivityHighscore.llMotivityLvl4
        val llLvl5 : LinearLayout = bindingMotivityHighscore.llMotivityLvl5

        val llArray : ArrayList<LinearLayout> = arrayListOf(llLvl1,llLvl2,llLvl3,llLvl4,llLvl5)
        for(i in llArray){
            i.setBackgroundColor(Color.RED)
        }

        val ivLvl1: ImageView = bindingMotivityHighscore.ivMotivityHighscoreLv1
        val ivLvl2: ImageView = bindingMotivityHighscore.ivMotivityHighscoreLv2
        val ivLvl3: ImageView = bindingMotivityHighscore.ivMotivityHighscoreLv3
        val ivLvl4: ImageView = bindingMotivityHighscore.ivMotivityHighscoreLv4
        val ivLvl5: ImageView = bindingMotivityHighscore.ivMotivityHighscoreLv5

        val ivArray : ArrayList<ImageView> = arrayListOf(ivLvl1,ivLvl2,ivLvl3,ivLvl4,ivLvl5)

        highscorehelper.retrieveSharedPreferences(llArray,ivArray,"motivity")

    }

}