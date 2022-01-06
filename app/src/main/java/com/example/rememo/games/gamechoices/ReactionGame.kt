package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameReactionBinding
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.GameChoiceHelper
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.highscore.ReactionHighscore

class ReactionGame : AppCompatActivity(){

    private lateinit var bindingReactionGame : GameReactionBinding
    private var contextHelper = ContextHelper(this)
    private var gameChoice = GameChoiceHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionGame = GameReactionBinding.inflate(layoutInflater)
        setContentView(bindingReactionGame.root)

        bindingReactionGame.iBHowToPlayReaction.setOnClickListener{goToHowToPlayMemory()}
        bindingReactionGame.iBHighscoreLinkLvlsReaction.setOnClickListener {
            contextHelper.startIntent(ReactionHighscore::class.java,false,flag=false)
        }

        val lvl1 : Button = bindingReactionGame.btReactionLv1
        val lvl2 : Button = bindingReactionGame.btReactionLv2
        val lvl3 : Button = bindingReactionGame.btReactionLv3
        val lvl4 : Button = bindingReactionGame.btReactionLv4
        val lvl5 : Button = bindingReactionGame.btReactionLv5

        val lvls : ArrayList<Button> = arrayListOf(lvl1, lvl2, lvl3, lvl4, lvl5)
        for(i in lvls){
            i.setBackgroundColor(Color.RED)
        }

        gameChoice.retrieveSharedPreferences(lvls, "reaction")
    }

    private fun goToHowToPlayMemory() {
        contextHelper.startIntent(HowToPlayReaction::class.java, false, flag = false)
    }
}