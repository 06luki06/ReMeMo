package com.example.rememo.games.memorylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl1Binding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.pauseScreens.Pause
import kotlin.concurrent.thread

class Memory_lvl1 : AppCompatActivity() {

    private lateinit var bindingMemorylvl1: MemoryLvl1Binding
    lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4")
    private val countDown: Long = 4000
    private var result: String = ""
    private var resultInput = ""
    val gameEngine = Memory_game_engine()
    private lateinit var countDownTimer: CountDownTimer
    private val interval: Long = 1000
    private var timerEnd :Boolean = false
    private val howMuch = 4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl1 = MemoryLvl1Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl1.root)


        result = gameEngine.saveAsString(buttonChoice, howMuch)
        bindingMemorylvl1.iBPauseScreen.setOnClickListener { goToPause() }



        val button_1 = bindingMemorylvl1.btMemoryLv111
        val button_2 = bindingMemorylvl1.btMemoryLv112
        val button_3 = bindingMemorylvl1.btMemoryLv113
        val button_4 = bindingMemorylvl1.btMemoryLv114

        buttonArray = arrayListOf<Button>(button_1, button_2, button_3, button_4)
        countDown(bindingMemorylvl1, countDown)

        thread { Thread.sleep(countDown)
            init()
        }
    }

    fun goToPause() {
        val intent : Intent = Intent(this, Pause::class.java)
        val game: String = "memory"
        intent.putExtra("game", game)
        startIntent(intent)
    }

    fun countDown(bindingMemorylvl1: MemoryLvl1Binding, countDownTimerLength: Long) {
        timerEnd = false
        bindingMemorylvl1.tVMemoryLv1Numbers.text = result
        countDownTimer = object : CountDownTimer(countDownTimerLength, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl1.tVMemoryLv1Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
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
        resultInput += buttonArray[i].text
        if(resultInput.length == howMuch){
            compareResults()
            deaktivate()
        }
    }

    fun compareResults(){
        if(result == resultInput){

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 1")
            builder.setMessage("Great, you have nailed it!")
            builder.setNeutralButton("Back To Games"){dialog, which ->
                val intent : Intent = Intent(this, MemoryGame::class.java)
                intent.putExtra("memory_lv1_checked", "1")
                startActivity(intent)
            }.show()

        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 1")
            builder.setMessage("You are a noob, try again")
            builder.setNeutralButton("Retry"){dialog, which ->
                val intent : Intent = Intent(this, Memory_lvl1::class.java)
                startActivity(intent)
            }.show()
        }
    }

    fun startIntent(intent : Intent){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG
            ).show()
        }
    }
}