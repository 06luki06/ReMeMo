package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl3Binding
import kotlin.concurrent.thread

class MemoryLvl3 : AppCompatActivity(){

    private lateinit var bindingMemorylvl3 : MemoryLvl3Binding
    private val gameEngine = MemoryGameEngine(this)

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 6000
    private val interval: Long = 1000

    private var result: String = ""
    private val howMuch = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl3 = MemoryLvl3Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl3.root)

        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl3.iBPauseScreen.setOnClickListener{gameEngine.goToPause(this.javaClass)}
        val title : String = getString(R.string.bt_lv3)

        val button1 = bindingMemorylvl3.btMemoryLvl31
        val button2 = bindingMemorylvl3.btMemoryLvl32
        val button3 = bindingMemorylvl3.btMemoryLvl33
        val button4 = bindingMemorylvl3.btMemoryLvl34
        val button5 = bindingMemorylvl3.btMemoryLvl35
        val button6 = bindingMemorylvl3.btMemoryLvl36
        val button7 = bindingMemorylvl3.btMemoryLvl37
        val button8 = bindingMemorylvl3.btMemoryLvl38
        buttonArray = arrayListOf(button1, button2, button3, button4, button5, button6, button7, button8)

        countDown(bindingMemorylvl3)
        thread { Thread.sleep(countDown)
            gameEngine.init(buttonArray, howMuch, "lvl_3_checked", MemoryLvl3::class.java, title)
        }
    }

    private fun countDown(bindingMemorylvl3: MemoryLvl3Binding) {
        bindingMemorylvl3.tVMemoryLv3Numbers.text = result
        countDownTimer = object : CountDownTimer(countDown, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl3.tVMemoryLv3Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}