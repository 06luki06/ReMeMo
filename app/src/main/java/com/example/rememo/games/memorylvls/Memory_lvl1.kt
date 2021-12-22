package com.example.rememo.games.memorylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl1Binding
import com.example.rememo.games.pauseScreens.Pause
import kotlin.concurrent.thread

class Memory_lvl1 : AppCompatActivity() {

    private lateinit var bindingMemorylvl1: MemoryLvl1Binding
    lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4")
    private val countDown: Long = 4000
    private var result: String = ""
    val gameEngine = Memory_game_engine()
    private var resultInput = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl1 = MemoryLvl1Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl1.root)

        result = gameEngine.saveAsString(buttonChoice)

        val intent: Intent = Intent(this, Pause::class.java)
        bindingMemorylvl1.iBPauseScreen.setOnClickListener { goToPause(intent) }

        val button_1 = bindingMemorylvl1.btMemoryLv111
        val button_2 = bindingMemorylvl1.btMemoryLv112
        val button_3 = bindingMemorylvl1.btMemoryLv113
        val button_4 = bindingMemorylvl1.btMemoryLv114

        buttonArray = arrayListOf<Button>(button_1, button_2, button_3, button_4)
        //init(buttonArray)
        // val inputResult= selectInput(buttonArray)
        gameEngine.countDown(bindingMemorylvl1, countDown)

        thread { Thread.sleep(countDown)
            init()
        }
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

    fun init (){
        for (i in 0 until buttonArray.size ) {
            buttonArray[i].setOnClickListener {setClickListener(i)}
        }
    }

    fun deaktivate(){
        for (i in 0 until buttonArray.size ) {
            buttonArray[i].setOnClickListener { null }
        }
    }

    fun setClickListener(i:Int){
        var tvResult = bindingMemorylvl1.tVResult.text.toString()+buttonArray[i].text
        bindingMemorylvl1.tVResult.text = tvResult
        if(tvResult.length==4){
            deaktivate()
        }
    }
}
