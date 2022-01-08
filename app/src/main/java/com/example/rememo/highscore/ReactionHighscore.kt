package com.example.rememo.highscore

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryHighscoreBinding
import com.example.rememo.databinding.ReactionHighscoreBinding
import com.example.rememo.games.helperClasses.Highscorehelper

class ReactionHighscore: AppCompatActivity() {

    private lateinit var bindingReactionHighscore: ReactionHighscoreBinding
    private val highscorehelper = Highscorehelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionHighscore = ReactionHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingReactionHighscore.root)

        val prefs : SharedPreferences = getSharedPreferences("Levels_Reaction",0)

        val llLvl1 : LinearLayout = bindingReactionHighscore.llReactionLvl1
        val llLvl2 : LinearLayout = bindingReactionHighscore.llReactionLvl2
        val llLvl3 : LinearLayout = bindingReactionHighscore.llReactionLvl3
        val llLvl4 : LinearLayout = bindingReactionHighscore.llReactionLvl4
        val llLvl5 : LinearLayout = bindingReactionHighscore.llReactionLvl5

        val llArray : ArrayList<LinearLayout> = arrayListOf(llLvl1,llLvl2,llLvl3,llLvl4,llLvl5)
        for(i in llArray){
            i.setBackgroundColor(Color.parseColor("#FF3030"))
        }

        val ivLvl1: ImageView = bindingReactionHighscore.ivReactionHighscoreLv1
        val ivLvl2: ImageView = bindingReactionHighscore.ivReactionHighscoreLv2
        val ivLvl3: ImageView = bindingReactionHighscore.ivReactionHighscoreLv3
        val ivLvl4: ImageView = bindingReactionHighscore.ivReactionHighscoreLv4
        val ivLvl5: ImageView = bindingReactionHighscore.ivReactionHighscoreLv5

        val ivArray : ArrayList<ImageView> = arrayListOf(ivLvl1,ivLvl2,ivLvl3,ivLvl4,ivLvl5)

        highscorehelper.retrieveSharedPreferences(llArray,ivArray,"reaction")

    }



}


