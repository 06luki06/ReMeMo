package com.example.rememo.games.memorylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.MemoryLvl5Binding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.pauseScreens.Pause
import kotlin.concurrent.thread

class Memory_lvl5 : AppCompatActivity(){

    private lateinit var bindingMemorylvl5 : MemoryLvl5Binding
    private lateinit var mp : MediaPlayer
    private val gameEngine = Memory_game_engine()
    lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

    private lateinit var countDownTimer: CountDownTimer
    private val interval: Long = 1000
    private val countDown: Long = 5000

    private var result: String = ""
    private var resultInput = ""
    private val howMuch = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl5 = MemoryLvl5Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl5.root)

        mp = MediaPlayer.create(this, R.raw.clapping)
        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs, mp)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl5.iBPauseScreen.setOnClickListener{goToPause()}

        val button_1 = bindingMemorylvl5.btMemoryLvl51
        val button_2 = bindingMemorylvl5.btMemoryLvl52
        val button_3 = bindingMemorylvl5.btMemoryLvl53
        val button_4 = bindingMemorylvl5.btMemoryLvl54
        val button_5 = bindingMemorylvl5.btMemoryLvl55
        val button_6 = bindingMemorylvl5.btMemoryLvl56
        val button_7 = bindingMemorylvl5.btMemoryLvl57
        val button_8 = bindingMemorylvl5.btMemoryLvl58
        val button_9 = bindingMemorylvl5.btMemoryLvl59
        buttonArray = arrayListOf<Button>(button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9)

        countDown(bindingMemorylvl5, countDown)
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

    fun countDown(bindingMemorylvl5: MemoryLvl5Binding, countDownTimerLength: Long) {
        bindingMemorylvl5.tVMemoryLv5Numbers.text = result
        countDownTimer = object : CountDownTimer(countDownTimerLength, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl5.tVMemoryLv5Numbers.setText(R.string.memory_its_your_turn)
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
        if(result.equals(resultInput)){
            mp.start()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 5")
            builder.setMessage("Great, you have nailed it!")
            builder.setNeutralButton("Back To Games"){dialog, which ->
                val intent : Intent = Intent(this, MemoryGame::class.java)
                writeIntoSharedPrefs("lvl_5_checked")
                startIntent(intent)
            }.show()
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 5")
            builder.setMessage("You are a noob, try again")
            builder.setNeutralButton("Retry"){dialog, which ->
                val intent : Intent = Intent(this, Memory_lvl5::class.java)
                startIntent(intent)
            }.show()
        }
    }

    fun writeIntoSharedPrefs(lvl: String){
        val prefs : SharedPreferences = getSharedPreferences("Levels_Memory", 0)
        prefs
            .edit()
            .putString(lvl, "true")
            .apply()
    }

    fun startIntent(intent : Intent){
        try {
            finish()
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }
}