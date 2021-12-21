package com.example.rememo.games.memorylvls

import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl1Binding

class Memory_game_engine : AppCompatActivity() {
    private lateinit var countDownTimer : CountDownTimer
    private val interval : Long = 1000
    private var result : String = ""

    private fun selectRandomButtons(buttonchoice : Array<String>): ArrayList<String> {
        var randomNumber : Int

        val buttons = ArrayList<String>()

        for(i in 0 .. 4){
            randomNumber = (0 .. 3).random()
            buttons.add(buttonchoice[randomNumber])
        }
        return buttons
    }

    fun saveAsString(buttonChoice: Array<String>) : String{
        val arrayList : ArrayList<String> = selectRandomButtons(buttonChoice)

        for(i in 0 until arrayList.size - 1){
            result += arrayList[i]
        }
        return result
    }

    fun countDown(bindingMemorylvl1 : MemoryLvl1Binding, countDownTimerLength : Long){
        bindingMemorylvl1.tVMemoryLv1Numbers.text = result
        countDownTimer = object : CountDownTimer(countDownTimerLength, interval){
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl1.tVMemoryLv1Numbers.setText(R.string.memory_its_your_turn)
            }
        }.start()
    }
}