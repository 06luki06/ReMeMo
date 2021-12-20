package com.example.rememo.games.memorylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryLvl1Binding
import com.example.rememo.databinding.MemoryLvl5Binding
import com.example.rememo.games.pauseScreens.Pause

class Memory_lvl5 : AppCompatActivity(){

    private lateinit var bindingMemorylvl5 : MemoryLvl5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl5 = MemoryLvl5Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl5.root)

        bindingMemorylvl5.iBPauseScreen.setOnClickListener{goToPause()}

    }

    private fun goToPause(){

        val intent: Intent = Intent(this, Pause::class.java)

        val game : String = "memory"
        intent.putExtra("game", game)

        try {
            startActivity(intent)

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}