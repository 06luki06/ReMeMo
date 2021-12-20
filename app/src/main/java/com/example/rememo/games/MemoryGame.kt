package com.example.rememo.games

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.databinding.GameMemoryBinding
import com.example.rememo.games.memorylvls.Memory_lvl1

class MemoryGame : AppCompatActivity(){

    private lateinit var bindingMemoryGame : GameMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryGame = GameMemoryBinding.inflate(layoutInflater)
        setContentView(bindingMemoryGame.root)

        bindingMemoryGame.iBHowToPlayMemory.setOnClickListener{goToHowToPlayReaction()}
        bindingMemoryGame.btMemoryLv1.setOnClickListener{goToMemoryLvl1()}
    }

    private fun goToHowToPlayReaction() {

        val intent : Intent = Intent(this, HowToPlayMemory::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToMemoryLvl1() {

        val intent : Intent = Intent(this, Memory_lvl1::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}