package com.example.rememo.games.memorylvls

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl4Binding
import kotlin.concurrent.thread

class MemoryLvl4 : AppCompatActivity(){

    private lateinit var bindingMemorylvl4 : MemoryLvl4Binding
    private val gameEngine = MemoryGameEngine(this)

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 4000
    private val interval: Long = 1000

    private var result: String = ""
    private val howMuch = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl4 = MemoryLvl4Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl4.root)

        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl4.iBPauseScreen.setOnClickListener{gameEngine.goToPause(this.javaClass)}
        val title : String = getString(R.string.bt_lv4)

        val button1 = bindingMemorylvl4.btMemoryLvl41
        val button2 = bindingMemorylvl4.btMemoryLvl42
        val button3 = bindingMemorylvl4.btMemoryLvl43
        val button4 = bindingMemorylvl4.btMemoryLvl44
        val button5 = bindingMemorylvl4.btMemoryLvl45
        val button6 = bindingMemorylvl4.btMemoryLvl46
        val button7 = bindingMemorylvl4.btMemoryLvl47
        val button8 = bindingMemorylvl4.btMemoryLvl48
        buttonArray = arrayListOf(button1, button2, button3, button4, button5, button6, button7, button8)

        countDown(bindingMemorylvl4)
        thread { Thread.sleep(countDown)
            gameEngine.init(buttonArray, howMuch, "lvl_4_checked", MemoryLvl4::class.java, title)
        }
    }

    private fun countDown(bindingMemorylvl4: MemoryLvl4Binding) {
        bindingMemorylvl4.tVMemoryLv4Numbers.text = result
        countDownTimer = object : CountDownTimer(countDown, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl4.tVMemoryLv4Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}