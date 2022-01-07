package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameMotivityBinding
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.GameChoiceHelper
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.highscore.MotivityHighscore
import com.example.rememo.highscore.ReactionHighscore

class MotivityGame : AppCompatActivity(){

    private lateinit var bindingMotivityGame : GameMotivityBinding
    private var contextHelper = ContextHelper(this)
    private var gameChoice = GameChoiceHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityGame = GameMotivityBinding.inflate(layoutInflater)
        setContentView(bindingMotivityGame.root)

        bindingMotivityGame.iBHowToPlayMotivity.setOnClickListener{goToHowToPlayMemory()}
        bindingMotivityGame.iBHighscoreLinkLvlsMotivity.setOnClickListener {
            contextHelper.startIntent(MotivityHighscore::class.java,false,flag=false)
        }

        val lvl1 : Button = bindingMotivityGame.btMotivityLv1
        val lvl2 : Button = bindingMotivityGame.btMotivityLv2
        val lvl3 : Button = bindingMotivityGame.btMotivityLv3
        val lvl4 : Button = bindingMotivityGame.btMotivityLv4
        val lvl5 : Button = bindingMotivityGame.btMotivityLv5

        val lvls : ArrayList<Button> = arrayListOf(lvl1, lvl2, lvl3, lvl4, lvl5)
        for(i in lvls){
            i.setBackgroundColor(Color.RED)
        }
        gameChoice.retrieveSharedPreferences(lvls, "motivity")
    }

    private fun goToHowToPlayMemory() {
        contextHelper.startIntent(HowToPlayMotivity::class.java, false, flag = false)
    }
}