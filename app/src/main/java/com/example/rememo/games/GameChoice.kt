package com.example.rememo.games

import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.GamesBinding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.DialogHelper
import com.example.rememo.highscore.HighscoreGames
import com.example.rememo.settings.Settings

class GameChoice : AppCompatActivity(){

    private lateinit var bindingGames : GamesBinding
    private var dialogHelper = DialogHelper(this)
    private var contextHelper = ContextHelper(this)
    private lateinit var namePlayer : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGames = GamesBinding.inflate(layoutInflater)
        setContentView(bindingGames.root)

        bindingGames.iBHighscoreLink.setOnClickListener {
            contextHelper.startIntent(
                HighscoreGames::class.java,
                false,
                flag = false
            )
        }
        bindingGames.iBSettingsLink.setOnClickListener {
            contextHelper.startIntent(
                Settings::class.java,
                false,
                flag = false
            )
        }
        bindingGames.btGoToMemory.setOnClickListener {
            contextHelper.startIntent(
                MemoryGame::class.java,
                false,
                flag = false
            )
        }
        bindingGames.btGoToMotivity.setOnClickListener { dialogHelper.notYetImplemented("Go Back") }
        bindingGames.btGoToReaction.setOnClickListener {
            contextHelper.startIntent(ReactionGame::class.java, false, flag = false
            )
        }

        namePlayer = findViewById(R.id.tVNameOfPlayer)

        val name: String? = intent.getStringExtra("NameOfPlayer")

        if (name == null) {
            namePlayer.visibility = INVISIBLE
        } else {
            namePlayer.text = "Hi $name"
        }
    }
}