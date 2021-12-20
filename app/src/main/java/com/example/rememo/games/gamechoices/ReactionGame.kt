package com.example.rememo.games.gamechoices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameReactionBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.games.memorylvls.*
import com.example.rememo.games.reactionlvls.*

class ReactionGame : AppCompatActivity(){

    private lateinit var bindingReactionGame : GameReactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionGame = GameReactionBinding.inflate(layoutInflater)
        setContentView(bindingReactionGame.root)

        bindingReactionGame.iBHowToPlayReaction.setOnClickListener{goToHowToPlayReaction()}
        bindingReactionGame.btReactionLv1.setOnClickListener{goToLvls(1)}
        bindingReactionGame.btReactionLv2.setOnClickListener{goToLvls(2)}
        bindingReactionGame.btReactionLv3.setOnClickListener{goToLvls(3)}
        bindingReactionGame.btReactionLv4.setOnClickListener{goToLvls(4)}
        bindingReactionGame.btReactionLv5.setOnClickListener{goToLvls(5)}
    }

    private fun goToHowToPlayReaction() {

        val intent : Intent = Intent(this, HowToPlayReaction::class.java)
        startIntent(intent)
    }

    private fun goToLvls(lvl : Int) {
        var intent: Intent? = null

        when (lvl) {
            1 -> intent = Intent(this, Reaction_lvl1::class.java)
            2 -> intent = Intent(this, Reaction_lvl2::class.java)
            3 -> intent = Intent(this, Reaction_lvl3::class.java)
            4 -> intent = Intent(this, Reaction_lvl4::class.java)
            5 -> intent = Intent(this, Reaction_lvl5::class.java)
            else -> { // Note the block
                print("this level does not exist")
            }
        }
        startIntent(intent)
    }

    private fun startIntent(intent : Intent?){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}