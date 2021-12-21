package com.example.rememo.games.memorylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryLvl1Binding
import com.example.rememo.games.pauseScreens.Pause

class Memory_lvl1 : AppCompatActivity() {

    private lateinit var bindingMemorylvl1: MemoryLvl1Binding
    private val buttonChoice = arrayOf("1", "2", "3", "4")
    private val countDown: Long = 4000
    private var result: String = ""
    val gameEngine = Memory_game_engine()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl1 = MemoryLvl1Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl1.root)

        result = gameEngine.saveAsString(buttonChoice)
        gameEngine.countDown(bindingMemorylvl1, countDown)
        val intent: Intent = Intent(this, Pause::class.java)
        bindingMemorylvl1.iBPauseScreen.setOnClickListener { goToPause(intent) }
    }

    fun goToPause(intent: Intent) {

        val game: String = "memory"
        intent.putExtra("game", game)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG
            ).show()
        }
    }
}