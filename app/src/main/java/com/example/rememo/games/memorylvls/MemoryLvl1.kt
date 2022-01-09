package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl1Binding
import kotlin.concurrent.thread

class MemoryLvl1 : AppCompatActivity(){

    private lateinit var bindingMemorylvl1 : MemoryLvl1Binding
    private val gameEngine = MemoryGameEngine(this)

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 4000
    private val interval: Long = 1000

    private var result: String = ""
    private val howMuch = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl1 = MemoryLvl1Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl1.root)

        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs)
        gameEngine.fullScreen(window)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl1.iBPauseScreen.setOnClickListener{gameEngine.goToPause(this.javaClass)}
        val title : String = getString(R.string.bt_lv1)

        val button1 = bindingMemorylvl1.btMemoryLv111
        val button2 = bindingMemorylvl1.btMemoryLv112
        val button3 = bindingMemorylvl1.btMemoryLv113
        val button4 = bindingMemorylvl1.btMemoryLv114
        buttonArray = arrayListOf(button1, button2, button3, button4)

        countDown(bindingMemorylvl1)
        thread { Thread.sleep(countDown)
            gameEngine.init(buttonArray, howMuch, "lvl_1_checked", MemoryLvl1::class.java, title)
        }
    }

    private fun countDown(bindingMemorylvl1: MemoryLvl1Binding) {
        bindingMemorylvl1.tVMemoryLv1Numbers.text = result
        countDownTimer = object : CountDownTimer(countDown, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl1.tVMemoryLv1Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}