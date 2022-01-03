package com.example.rememo.games.pauseScreens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.PauseScreenBinding
import com.example.rememo.games.GameChoice
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.settings.Settings

class Pause : AppCompatActivity(){

    private lateinit var bindingPause : PauseScreenBinding
    private var contextHelper = ContextHelper(this)

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
        var c : Class<*>? = null
        when (game) {
            "memory" -> c = HowToPlayMemory::class.java
            "motivity" -> c = HowToPlayMotivity::class.java
            "reaction" -> c = HowToPlayReaction::class.java
        }
        if(c != null){
            contextHelper.startIntent(c, false, flag = false)
        }
    }

    private fun goToSettings(){
        contextHelper.startIntent(Settings::class.java, false, flag = false)
    }

    private fun goToGameChoice(){
        contextHelper.startIntent(GameChoice::class.java, true, flag = true)
    }

    private fun returnToGame(){
        onBackPressed()
    }

    private fun restartGame(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Level neustarten")
        builder.setMessage("Willst du wirklich das Level neustarten?")
        builder.setPositiveButton(R.string.yes) { _, _ ->
            Toast.makeText(applicationContext, "UNDER CONSTRUCTION", Toast.LENGTH_LONG).show()
        }.show()

        builder.setNegativeButton(R.string.no) {_, _->
            onBackPressed()
        }.show()
    }
}