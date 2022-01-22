package com.example.rememo.games.pauseScreens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.PauseScreenBinding
import com.example.rememo.games.GameChoice
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.DialogHelper
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.settings.Settings

class Pause : AppCompatActivity(){

    private lateinit var bindingPause : PauseScreenBinding
    private var contextHelper = ContextHelper(this)
    private val dialogHelper = DialogHelper(this)
    private lateinit var level : Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPause = PauseScreenBinding.inflate(layoutInflater)
        setContentView(bindingPause.root)

        val game : String? = intent.getStringExtra("game")
        level = intent.getSerializableExtra("level") as Class<*>

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
        finish()
        contextHelper.startIntent(GameChoice::class.java, true, flag = true)
    }

    private fun returnToGame(){
        onBackPressed()
    }

    private fun restartGame(){
        dialogHelper.restartLevel("Restart","Really?",
            "jep", "nope", level)
    }
}