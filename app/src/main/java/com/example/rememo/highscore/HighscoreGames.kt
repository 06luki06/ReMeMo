package com.example.rememo.highscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.HighscoreGamesBinding
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.DialogHelper

class HighscoreGames : AppCompatActivity(){

    private lateinit var bindingHighscoreGames : HighscoreGamesBinding
    private var contextHelper = ContextHelper(this)
    private var dialogHelper = DialogHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHighscoreGames = HighscoreGamesBinding.inflate(layoutInflater)
        setContentView(bindingHighscoreGames.root)

        bindingHighscoreGames.btHighscoreMemory.setOnClickListener {
            contextHelper.startIntent(MemoryHighscore::class.java,false,flag=false)
        }
        bindingHighscoreGames.btHigscoreReaction.setOnClickListener{
            contextHelper.startIntent(ReactionHighscore::class.java,false,flag=false)
        }
        bindingHighscoreGames.btHighscoreMotivity.setOnClickListener {
            dialogHelper.notYetImplemented("Go Back")
        }
    }
}