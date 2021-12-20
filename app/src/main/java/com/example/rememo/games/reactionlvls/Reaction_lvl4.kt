package com.example.rememo.games.reactionlvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.ReactionLvl1Binding
import com.example.rememo.databinding.ReactionLvl4Binding
import com.example.rememo.games.pauseScreens.Pause

class Reaction_lvl4 : AppCompatActivity(){

    private lateinit var bindingReaction_lvl4 : ReactionLvl4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReaction_lvl4= ReactionLvl4Binding.inflate(layoutInflater)
        setContentView(bindingReaction_lvl4.root)

        bindingReaction_lvl4.iBPauseScreen.setOnClickListener{goToPause()}

    }

    private fun goToPause(){

        val intent: Intent = Intent(this, Pause::class.java)

        val game : String = "reaction"
        intent.putExtra("game", game)

        try {
            startActivity(intent)

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}