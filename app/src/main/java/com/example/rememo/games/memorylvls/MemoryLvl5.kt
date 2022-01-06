package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl5Binding
import kotlin.concurrent.thread

class MemoryLvl5 : AppCompatActivity(){

    private lateinit var bindingMemorylvl5 : MemoryLvl5Binding
    private val gameEngine = MemoryGameEngine(this)

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 5000
    private val interval: Long = 1000

    private var result: String = ""
    private val howMuch = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl5 = MemoryLvl5Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl5.root)

        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl5.iBPauseScreen.setOnClickListener{gameEngine.goToPause(this.javaClass)}
        val title : String = getString(R.string.bt_lv5)

        val button1 = bindingMemorylvl5.btMemoryLvl51
        val button2 = bindingMemorylvl5.btMemoryLvl52
        val button3 = bindingMemorylvl5.btMemoryLvl53
        val button4 = bindingMemorylvl5.btMemoryLvl54
        val button5 = bindingMemorylvl5.btMemoryLvl55
        val button6 = bindingMemorylvl5.btMemoryLvl56
        val button7 = bindingMemorylvl5.btMemoryLvl57
        val button8 = bindingMemorylvl5.btMemoryLvl58
        val button9 = bindingMemorylvl5.btMemoryLvl59
        buttonArray = arrayListOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)

        countDown(bindingMemorylvl5)
        thread { Thread.sleep(countDown)
            gameEngine.init(buttonArray, howMuch, "lvl_5_checked", MemoryLvl5::class.java, title)
        }
    }

    private fun countDown(bindingMemorylvl5: MemoryLvl5Binding) {
        bindingMemorylvl5.tVMemoryLv5Numbers.text = result
        countDownTimer = object : CountDownTimer(countDown, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl5.tVMemoryLv5Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}