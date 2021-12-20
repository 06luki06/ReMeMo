package com.example.rememo.games

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.databinding.GameMemoryBinding
import com.example.rememo.games.memorylvls.*

class MemoryGame : AppCompatActivity(){

    private lateinit var bindingMemoryGame : GameMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryGame = GameMemoryBinding.inflate(layoutInflater)
        setContentView(bindingMemoryGame.root)

        bindingMemoryGame.iBHowToPlayMemory.setOnClickListener{goToHowToPlayReaction()}
        bindingMemoryGame.btMemoryLv1.setOnClickListener{goToMemoryLvls(1)}
        bindingMemoryGame.btMemoryLv2.setOnClickListener{goToMemoryLvls(2)}
        bindingMemoryGame.btMemoryLv3.setOnClickListener{goToMemoryLvls(3)}
        bindingMemoryGame.btMemoryLv4.setOnClickListener{goToMemoryLvls(4)}
        bindingMemoryGame.btMemoryLv5.setOnClickListener{goToMemoryLvls(5)}
    }

    private fun goToHowToPlayReaction() {

        val intent : Intent = Intent(this, HowToPlayMemory::class.java)
        startIntent(intent)
    }

    private fun goToMemoryLvls(lvl : Int) {
        var intent: Intent? = null

        when (lvl) {
            1 -> intent = Intent(this, Memory_lvl1::class.java)
            2 -> intent = Intent(this, Memory_lvl2::class.java)
            3 -> intent = Intent(this, Memory_lvl3::class.java)
            4 -> intent = Intent(this, Memory_lvl4::class.java)
            5 -> intent = Intent(this, Memory_lvl5::class.java)
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