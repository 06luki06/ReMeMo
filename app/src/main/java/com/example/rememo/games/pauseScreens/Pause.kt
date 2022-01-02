package com.example.rememo.games.pauseScreens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.PauseScreenBinding
import com.example.rememo.games.GameChoice
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.games.memorylvls.*
import com.example.rememo.settings.Settings

class Pause : AppCompatActivity(){

    private lateinit var bindingPause : PauseScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPause = PauseScreenBinding.inflate(layoutInflater)
        setContentView(bindingPause.root)

        val game : String? = intent.getStringExtra("game")

        bindingPause.iBGoToHowToPlay.setOnClickListener{ goToHowToPlay(game)}
        bindingPause.iBPauseSettings.setOnClickListener{goToSettings()}
        bindingPause.iBGoToLevels.setOnClickListener{goToGameChoice()}
        bindingPause.iBResume.setOnClickListener{returnToGame()}
        bindingPause.iBRestart.setOnClickListener{restartGame()}
    }

    private fun goToHowToPlay(game: String?) {

        when (game) {
            "memory" -> intent = Intent(this, HowToPlayMemory::class.java)
            "motivity" -> intent = Intent(this, HowToPlayMotivity::class.java)
            "reaction" -> intent = Intent(this, HowToPlayReaction::class.java)
            else -> {
                print("this game does not exist")
            }
        }
        startActivity(intent)
    }

    private fun goToSettings(){
        startActivity(Intent(this, Settings::class.java))
    }

    private fun goToGameChoice(){
        startIntent(Intent(this, GameChoice::class.java))
    }

    private fun returnToGame(){
        onBackPressed()
    }

    private fun startIntent(intent: Intent){
        try {
            finish()
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun restartGame(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Level neustarten")
        builder.setMessage("Willst du wirklich das Level neustarten?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext, "UNDER CONSTRUCTION", Toast.LENGTH_LONG).show()
        }.show()

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            onBackPressed()
        }.show()
    }
}