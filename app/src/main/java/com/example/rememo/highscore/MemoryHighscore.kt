package com.example.rememo.highscore

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryHighscoreBinding
import com.example.rememo.games.helperClasses.Highscorehelper

class MemoryHighscore: AppCompatActivity() {

    private lateinit var bindingMemoryHighscore: MemoryHighscoreBinding

    private val highscorehelper = Highscorehelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryHighscore = MemoryHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingMemoryHighscore.root)

        val llLvl1 : LinearLayout = bindingMemoryHighscore.llMemoryhsLvl1
        val llLvl2 : LinearLayout = bindingMemoryHighscore.llMemoryhsLvl2
        val llLvl3 : LinearLayout = bindingMemoryHighscore.llMemoryhsLvl3
        val llLvl4 : LinearLayout = bindingMemoryHighscore.llMemoryhsLvl4
        val llLvl5 : LinearLayout = bindingMemoryHighscore.llMemoryhsLvl5

        val llArray : ArrayList<LinearLayout> = arrayListOf(llLvl1,llLvl2,llLvl3,llLvl4,llLvl5)
        for(i in llArray){
            i.setBackgroundColor(Color.parseColor("#FF3030"))
        }

        val ivLvl1:ImageView = bindingMemoryHighscore.ivMemoryHighscoreLv1
        val ivLvl2:ImageView = bindingMemoryHighscore.ivMemoryHighscoreLv2
        val ivLvl3:ImageView = bindingMemoryHighscore.ivMemoryHighscoreLv3
        val ivLvl4:ImageView = bindingMemoryHighscore.ivMemoryHighscoreLv4
        val ivLvl5:ImageView = bindingMemoryHighscore.ivMemoryHighscoreLv5

        val ivArray : ArrayList<ImageView> = arrayListOf(ivLvl1,ivLvl2,ivLvl3,ivLvl4,ivLvl5)

        highscorehelper.retrieveSharedPreferences(llArray,ivArray,"memory")
    }
}