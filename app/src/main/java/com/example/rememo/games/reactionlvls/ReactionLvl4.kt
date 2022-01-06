package com.example.rememo.games.reactionlvls

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
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.databinding.ReactionLvl4Binding
import java.util.*
import kotlin.math.roundToInt

class ReactionLvl4 : AppCompatActivity(), View.OnClickListener, Runnable {

    private lateinit var bindingReactionLvl4 : ReactionLvl4Binding
    private lateinit var gameboard : ViewGroup
    private val handler : Handler = Handler()
    private val gameEngine = ReactionGameEngine(this)
    private lateinit var lvl : String

    private var gameAlreadyStarted : Boolean = false
    private var isPaused : Boolean = false

    private var flies : Int = 15
    private var fliesToHit : Int = 15
    private var caughtFlies : Int = 0
    private val highestAge : Long = 500

    private var time : Int = 30
    private var scale : Float = 0F
    private var randomNumberGenerator : Random = Random()

    private lateinit var sum : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionLvl4= ReactionLvl4Binding.inflate(layoutInflater)
        setContentView((bindingReactionLvl4.root))
        bindingReactionLvl4.iBPauseScreen.setOnClickListener{gameEngine.goToPause(this.javaClass)
        isPaused=true}

        scale = resources.displayMetrics.density
        gameboard = bindingReactionLvl4.FLGameboard
        lvl = getString(R.string.bt_lv4)

        sum = MediaPlayer.create(this, R.raw.summen)
        val prefs : SharedPreferences = getSharedPreferences("Soundsettings", 0)
        setSoundFly(prefs)
        gameEngine.fullScreen(window, prefs)
        gameEngine.initData(time, caughtFlies, fliesToHit)
        startGame()
    }

    private fun startGame(){
        gameAlreadyStarted = true
        caughtFlies = 0
        updateScreen()
        handler.postDelayed(this, 1000)
    }

    private fun updateScreen(){
        val tvFliesLeft : TextView = bindingReactionLvl4.tVFlysLeft
        tvFliesLeft.text = fliesToHit.toString()

        val tvCaughtFlies : TextView = bindingReactionLvl4.tVCatchedFlies
        tvCaughtFlies.text = caughtFlies.toString()

        val tVTimeLeft : TextView = bindingReactionLvl4.tVTimeLeft
        tVTimeLeft.text = time.toString()

        val barFliesLeft : FrameLayout = bindingReactionLvl4.FLCatchedFlys
        val barTimeLeft : FrameLayout = bindingReactionLvl4.FLTimeLeft

        val lpCaught : ViewGroup.LayoutParams = barFliesLeft.layoutParams
        lpCaught.width = (scale * 300 * caughtFlies.coerceAtMost(flies) / flies).roundToInt()

        val lpTime : ViewGroup.LayoutParams = barTimeLeft.layoutParams
        lpTime.width = (scale * time * 300 / 30).roundToInt()
    }

    private fun countdownTIme(){
        if(!isPaused) {
            time--
        }
        val randomNumber : Float = randomNumberGenerator.nextFloat()
        val probability : Double = flies * 2.0 / 30

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

        if(!gameEngine.failedLevel(time, caughtFlies, lvl, ReactionLvl4::class.java)){
            if(!gameEngine.checkIfLevelPassed(caughtFlies)){
                handler.postDelayed(this, 1000)
            }else{
                gameEngine.levelPassed(lvl, "lvl_4_checked")
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

    private fun setSoundFly(prefs : SharedPreferences){
        val min : Int = prefs.getInt("soundMin", 0)
        val max : Int = prefs.getInt("soundMax", 0)
        sum.setVolume(min.toFloat(), max.toFloat())
    }

    override fun onClick(v: View?) {
        isPaused=false
        caughtFlies++
        fliesToHit--
        updateScreen()
        gameboard.removeView(v)
        sum.pause()
    }

    override fun run(){
        countdownTIme()
    }

    override fun onBackPressed() {
        isPaused = false
        super.onBackPressed()
    }

    override fun onDestroy(){
        sum.release()
        super.onDestroy()
    }
}
