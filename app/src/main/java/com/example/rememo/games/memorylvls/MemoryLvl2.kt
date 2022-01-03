package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl2Binding
import kotlin.concurrent.thread

class MemoryLvl2 : AppCompatActivity(){

    private lateinit var bindingMemorylvl2 : MemoryLvl2Binding
    private val gameEngine = MemoryGameEngine(this)

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 4000
    private val interval: Long = 1000

    private var result: String = ""
    private val howMuch = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl2 = MemoryLvl2Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl2.root)

        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl2.iBPauseScreen.setOnClickListener{gameEngine.goToPause()}
        val title : String = getString(R.string.bt_lv2)

        val button1 = bindingMemorylvl2.btMemoryLv121
        val button2 = bindingMemorylvl2.btMemoryLv122
        val button3 = bindingMemorylvl2.btMemoryLv123
        val button4 = bindingMemorylvl2.btMemoryLv124
        val button5 = bindingMemorylvl2.btMemoryLv125
        val button6 = bindingMemorylvl2.btMemoryLv126
        buttonArray = arrayListOf(button1, button2, button3, button4, button5, button6)

        countDown(bindingMemorylvl2)
        thread { Thread.sleep(countDown)
            gameEngine.init(buttonArray, howMuch, "lvl_2_checked", MemoryLvl2::class.java, title)
        }
    }

    private fun countDown(bindingMemorylvl2: MemoryLvl2Binding) {
        bindingMemorylvl2.tVMemoryLv2Numbers.text = result
        countDownTimer = object : CountDownTimer(countDown, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl2.tVMemoryLv2Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}