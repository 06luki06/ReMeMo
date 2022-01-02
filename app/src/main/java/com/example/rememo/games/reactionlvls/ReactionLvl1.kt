package com.example.rememo.games.reactionlvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.ReactionLvl1Binding
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.games.pauseScreens.Pause
import java.util.*
import kotlin.math.roundToInt

class ReactionLvl1 : AppCompatActivity(), View.OnClickListener, Runnable {

    private lateinit var bindingReactionLvl1 : ReactionLvl1Binding
    private lateinit var gameboard : ViewGroup
    private val handler : Handler = Handler()

    private var gameAlreadyStarted : Boolean = false
    private var isPaused : Boolean = false

    private var flies : Int = 10
    private var fliesToHit : Int = 10
    private var caughtFlies : Int = 0
    private val highestAge : Long = 2000

    private var time : Int = 60
    private var scale : Float = 0F
    private var randomNumberGenerator : Random = Random()

    private lateinit var clapping : MediaPlayer
    private lateinit var sum : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionLvl1= ReactionLvl1Binding.inflate(layoutInflater)
        setContentView((bindingReactionLvl1.root))
        bindingReactionLvl1.iBPauseScreen.setOnClickListener{goToPause()}

        scale = resources.displayMetrics.density
        gameboard = bindingReactionLvl1.FLGameboard

        sum = MediaPlayer.create(this, R.raw.summen)
        clapping = MediaPlayer.create(this, R.raw.clapping)

        fullScreen()
        startGame()
    }

    private fun goToPause(){
        val intent = Intent(this, Pause::class.java)
        val game = "reaction"
        intent.putExtra("game", game)
        startActivity(intent)
        hasBeenPaused()
    }

    private fun hasBeenPaused(){
        isPaused = true
    }

    private fun startGame(){
        gameAlreadyStarted = true
        caughtFlies = 0
        updateScreen()
        handler.postDelayed(this, 1000)
    }

    private fun updateScreen(){
        val tvFliesLeft : TextView = bindingReactionLvl1.tVFlysLeft
        tvFliesLeft.text = fliesToHit.toString()

        val tvCaughtFlies : TextView = bindingReactionLvl1.tVCatchedFlies
        tvCaughtFlies.text = caughtFlies.toString()

        val tVTimeLeft : TextView = bindingReactionLvl1.tVTimeLeft
        tVTimeLeft.text = time.toString()

        val barFliesLeft : FrameLayout = bindingReactionLvl1.FLCatchedFlys
        val barTimeLeft : FrameLayout = bindingReactionLvl1.FLTimeLeft

        val lpCaught : ViewGroup.LayoutParams = barFliesLeft.layoutParams
        lpCaught.width = (scale * 300 * caughtFlies.coerceAtMost(flies) / flies).roundToInt()

        val lpTime : ViewGroup.LayoutParams = barTimeLeft.layoutParams
        lpTime.width = (scale * time * 300 / 60).roundToInt()
    }

    private fun countdownTIme(){
        time--
        val randomNumber : Float = randomNumberGenerator.nextFloat()
        val probability : Double = flies * 1.5 / 60

        if(probability > 1){
            showFlies()
            if(randomNumber < probability - 1){
                showFlies()
            }
        }else{
            if(randomNumber < probability){
                showFlies()
            }
        }

        letFliesDisappear()
        updateScreen()

        if(!failedLevel()){
            if(!checkIfLevelPassed()){
                handler.postDelayed(this, 1000)
            }else{
                levelPassed()
            }
        }
    }

    private fun showFlies(){
        sum.start()
        val gameboardWidth : Int = gameboard.width
        val gameboardHeight : Int = gameboard.height

        val flyWidth : Int = (scale * 50).roundToInt()
        val flyHeight : Int = (scale * 53).roundToInt()

        val leftCoordinate : Int = randomNumberGenerator.nextInt(gameboardWidth - flyWidth)
        val topCoordinate : Int = randomNumberGenerator.nextInt(gameboardHeight - flyHeight)

        val fly = ImageView(this)
        fly.setImageResource(R.drawable.fliege)
        fly.setOnClickListener {onClick(fly)}

        val layoutParams : FrameLayout.LayoutParams = FrameLayout.LayoutParams(flyWidth, flyHeight)
        layoutParams.leftMargin = leftCoordinate
        layoutParams.topMargin = topCoordinate
        layoutParams.gravity = Gravity.TOP + Gravity.START

        gameboard.addView(fly, layoutParams)
        fly.setTag(R.id.birthdate, Date())
    }

    private fun letFliesDisappear(){
        var number = 0
        while (number < gameboard.childCount) {
            val fly  = gameboard.getChildAt(number)
            val birthdate = fly.getTag(R.id.birthdate) as Date
            val alter = Date().time - birthdate.time

            if (alter > highestAge) {
                gameboard.removeView(fly)
            } else {
                number++
            }
        }
    }

    private fun failedLevel() : Boolean{
        return if(time == 0 && caughtFlies < flies){
            gameOverScreen()
            true
        }else{
            false
        }
    }

    private fun checkIfLevelPassed() : Boolean{
        return caughtFlies >= flies
    }

    private fun levelPassed(){
        clapping.start()
        writeIntoSharedPrefs()

        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.bt_lv1)
        builder.setMessage("Great, you have nailed it")
        builder.setNeutralButton("go back to the levels"){_, _ ->
            val intent = Intent(this, ReactionGame::class.java)
            startIntent(intent)
        }.show()
    }

    private fun gameOverScreen(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.bt_lv1)
        builder.setMessage("Oops, you did a poor job")
        builder.setNeutralButton("Try Again"){_, _ ->
            val intent = Intent(this, ReactionLvl1::class.java)
            startIntent(intent)
        }.show()
    }

    private fun writeIntoSharedPrefs(){
        val lvl = "lvl_1_checked"
        val prefs : SharedPreferences = getSharedPreferences("Levels_Reaction", 0)
        prefs
            .edit()
            .putString(lvl, "true")
            .apply()
    }

    private fun fullScreen() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    private fun startIntent(intent: Intent){
        try {
            finish()
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(v: View?) {
        caughtFlies++
        fliesToHit--
        updateScreen()
        gameboard.removeView(v)
        sum.pause()
    }

    override fun run(){
        countdownTIme()
    }

    override fun onDestroy(){
        sum.release()
        clapping.release()
        super.onDestroy()
    }

    override fun onBackPressed() {
        isPaused = false
        super.onBackPressed()
    }
}
