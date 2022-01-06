package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameReactionBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast

class ReactionGame : AppCompatActivity(){

    private lateinit var bindingReactionGame : GameReactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionGame = GameReactionBinding.inflate(layoutInflater)
        setContentView(bindingReactionGame.root)
        
        bindingReactionGame.btHowToPlayReaction.setOnClickListener{goToHowToPlayReaction()}
    }
    
    private fun goToHowToPlayReaction() {
   
           val intent: Intent = Intent(this, HowToPlayReaction::class.java)
   
           try {
               startActivity(intent)
           } catch (e: ActivityNotFoundException) {
               Toast.makeText(
                   applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
           }
       }
}