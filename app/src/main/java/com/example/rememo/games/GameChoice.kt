package com.example.rememo.games

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GamesBinding
import com.example.rememo.highscore.HighscoreGames
import com.example.rememo.settings.Settings

class GameChoice : AppCompatActivity() {

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

    fun goToHighscoreGames(){

        val intent: Intent = Intent(this, HighscoreGames::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    fun goToSettings(){

        val intent: Intent = Intent(this, Settings::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    fun goToMemory(){

        val intent: Intent = Intent(this, MemoryGame::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    fun goToMotivity(){

        val intent: Intent = Intent(this, MotivityGame::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    fun goToReaction(){

        val intent: Intent = Intent(this, ReactionGame::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}