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
import com.example.rememo.databinding.MemoryLvl4Binding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.pauseScreens.Pause
import kotlin.concurrent.thread

class Memory_lvl4 : AppCompatActivity(){

    private lateinit var bindingMemorylvl4 : MemoryLvl4Binding
    private lateinit var mp : MediaPlayer
    private val gameEngine = MemoryGameEngine()

    private lateinit var  buttonArray: ArrayList<Button>
    private val buttonChoice = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")

    private lateinit var countDownTimer: CountDownTimer
    private val countDown: Long = 4000
    private val interval: Long = 1000

    private var result: String = ""
    private var resultInput = ""
    private val howMuch = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl4 = MemoryLvl4Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl4.root)

        mp = MediaPlayer.create(this, R.raw.clapping)
        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        gameEngine.setSound(prefs, mp)

        result = gameEngine.saveAsString(buttonChoice, howMuch)

        bindingMemorylvl4.iBPauseScreen.setOnClickListener{goToPause()}

        val button_1 = bindingMemorylvl4.btMemoryLvl41
        val button_2 = bindingMemorylvl4.btMemoryLvl42
        val button_3 = bindingMemorylvl4.btMemoryLvl43
        val button_4 = bindingMemorylvl4.btMemoryLvl44
        val button_5 = bindingMemorylvl4.btMemoryLvl45
        val button_6 = bindingMemorylvl4.btMemoryLvl46
        val button_7 = bindingMemorylvl4.btMemoryLvl47
        val button_8 = bindingMemorylvl4.btMemoryLvl48
        buttonArray = arrayListOf<Button>(button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8)

        countDown(bindingMemorylvl4, countDown)
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

    fun countDown(bindingMemorylvl4: MemoryLvl4Binding, countDownTimerLength: Long) {
        bindingMemorylvl4.tVMemoryLv4Numbers.text = result
        countDownTimer = object : CountDownTimer(countDownTimerLength, interval) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                bindingMemorylvl4.tVMemoryLv4Numbers.setText(R.string.memory_its_your_turn)
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
            builder.setTitle("Level 4")
            builder.setMessage("Great, you have nailed it!")
            builder.setNeutralButton("Back To Games"){dialog, which ->
                val intent : Intent = Intent(this, MemoryGame::class.java)
                writeIntoSharedPrefs("lvl_4_checked")
                startIntent(intent)
            }.show()
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 4")
            builder.setMessage("You are a noob, try again")
            builder.setNeutralButton("Retry"){dialog, which ->
                val intent : Intent = Intent(this, Memory_lvl4::class.java)
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