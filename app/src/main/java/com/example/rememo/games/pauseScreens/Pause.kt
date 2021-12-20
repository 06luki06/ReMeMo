package com.example.rememo.games.pauseScreens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.PauseScreenBinding
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.howtoplay.HowToPlayReaction

class Pause : AppCompatActivity(){

    private lateinit var bindingPause : PauseScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingPause = PauseScreenBinding.inflate(layoutInflater)
        setContentView(bindingPause.root)

        val game : String? = intent.getStringExtra("game")

        bindingPause.iBGoToHowToPlay.setOnClickListener{ goToHowToPlay(game)}
    }

    private fun goToHowToPlay(game: String?) {

        var intent : Intent? = null
        if(game == "motivity"){
            intent = Intent(this, HowToPlayMotivity::class.java)
        } else if(game == "memory"){
            intent = Intent(this, HowToPlayMemory::class.java)
        } else if(game == "reaction"){
            intent = Intent(this, HowToPlayReaction::class.java)
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}