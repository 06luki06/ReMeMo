package com.example.rememo.games

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GamesBinding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.gamechoices.MotivityGame
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.highscore.HighscoreGames
import com.example.rememo.settings.Settings

class GameChoice : AppCompatActivity(){

    private lateinit var bindingGames : GamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGames = GamesBinding.inflate(layoutInflater)
        setContentView(bindingGames.root)

        bindingGames.iBHighscoreLink.setOnClickListener{goToHighscoreGames()}
        bindingGames.iBSettingsLink.setOnClickListener{goToSettings()}
        bindingGames.btGoToMemory.setOnClickListener{goToMemory()}
        bindingGames.btGoToMotivity.setOnClickListener{goToMotivity()}
        bindingGames.btGoToReaction.setOnClickListener{goToReaction()}
    }

    private fun goToHighscoreGames(){
        val intent = Intent(this, HighscoreGames::class.java)
        startIntent(intent)
    }

    private fun goToSettings(){
        val intent = Intent(this, Settings::class.java)
        startIntent(intent)
    }

    private fun goToMemory(){
        val intent = Intent(this, MemoryGame::class.java)
        startIntent(intent)
    }

    private fun goToMotivity(){
        val intent = Intent(this, MotivityGame::class.java)
        startIntent(intent)
    }

    private fun goToReaction(){
        val intent = Intent(this, ReactionGame::class.java)
        startIntent(intent)
    }

    private fun startIntent(intent: Intent){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}