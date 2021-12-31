package com.example.rememo.games.reactionlvls

import android.content.ActivityNotFoundException
import android.content.Intent
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

class Reaction_lvl1 : AppCompatActivity(), View.OnClickListener, Runnable {

    private lateinit var bindingReaction_lvl1 : ReactionLvl1Binding
    private var gameAlreadyStarted : Boolean = false
    private var flys : Int = 0
    private var flys_to_hit : Int = 10
    private var catchedFlys : Int = 0
    private var time : Int = 0
    private var scale : Float = 0F
    private var randdomNumberGenerator : Random = Random()
    private lateinit var gameboard : ViewGroup
    private val HIGHEST_AGE : Long = 2000
    private val handler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReaction_lvl1= ReactionLvl1Binding.inflate(layoutInflater)
        setContentView((bindingReaction_lvl1.root))
        bindingReaction_lvl1.iBPauseScreen.setOnClickListener{goToPause()}
        scale = resources.displayMetrics.density
        startGame()
        gameboard = bindingReaction_lvl1.FLGameboard
    }

    private fun goToPause(){

        val intent: Intent = Intent(this, Pause::class.java)

        val game : String = "reaction"
        intent.putExtra("game", game)
        startActivity(intent)
    }

    private fun startGame(){
        gameAlreadyStarted = true
        flys = 10
        catchedFlys = 0
        time = 60
        updateScreen()
        handler.postDelayed(this, 1000)
    }

    private fun updateScreen(){
        val tV_flies_left : TextView = bindingReaction_lvl1.tVFlysLeft
        tV_flies_left.setText(flys_to_hit.toString())

        val tv_catched_flys : TextView = bindingReaction_lvl1.tVCatchedFlies
        tv_catched_flys.setText(catchedFlys.toString())

        val tVTimeLeft : TextView = bindingReaction_lvl1.tVTimeLeft
        tVTimeLeft.setText(time.toString())

        val flys_left_bar : FrameLayout = bindingReaction_lvl1.FLCatchedFlys
        val time_left_bar : FrameLayout = bindingReaction_lvl1.FLTtimeLeft

        val lpCatched : ViewGroup.LayoutParams = flys_left_bar.layoutParams
        lpCatched.width = Math.round(scale * 300 * Math.min(catchedFlys, flys) / flys)

        val lpTime : ViewGroup.LayoutParams = time_left_bar.layoutParams
        lpTime.width = Math.round(scale * time * 300 / 60)
    }

    private fun countdownTIme(){
        time--
        val randomNumber : Float = randdomNumberGenerator.nextFloat()
        val propability : Double = flys * 1.5 / 60
        if(propability > 1){
            showFlys()
            if(randomNumber < propability - 1){
                showFlys()
            }
        }else{
            if(randomNumber < propability){
                showFlys()
            }
        }
        letFlysDisappear()
        updateScreen()
        if(!failedLevel()){
            if(!checkIfLevelPassed()){
                handler.postDelayed(this, 1000)
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Level 1")
                builder.setMessage("Great, you have nailed it")
                builder.setNeutralButton("go back to the levels"){dialog, which ->
                    val intent : Intent = Intent(this, ReactionGame::class.java)
                    startIntent(intent)
                }.show()
            }
        }
    }

    private fun showFlys(){
        var gameboard_width : Int = gameboard.width
        var gameboard_height : Int = gameboard.height

        var fly_width : Int = (scale * 50).roundToInt()
        var fly_height : Int = (scale * 53).roundToInt()

        var leftCoordinate : Int = randdomNumberGenerator.nextInt(gameboard_width - fly_width)
        var topCoordinate : Int = randdomNumberGenerator.nextInt(gameboard_height - fly_height)

        var fly : ImageView = ImageView(this)
        fly.setImageResource(R.drawable.fliege)
        fly.setOnClickListener {onClick(fly)}
        var layoutParams : FrameLayout.LayoutParams = FrameLayout.LayoutParams(fly_width, fly_height)
        layoutParams.leftMargin = leftCoordinate
        layoutParams.topMargin = topCoordinate
        layoutParams.gravity = Gravity.TOP + Gravity.LEFT

        gameboard.addView(fly, layoutParams)
        fly.setTag(R.id.birthdate, Date())
    }

    private fun letFlysDisappear(){
        var number : Int = 0
        while (number < gameboard.childCount) {
            val fly  = gameboard.getChildAt(number)
            val birthdate = fly.getTag(R.id.birthdate) as Date
            val alter = Date().time - birthdate.time
            if (alter > HIGHEST_AGE) {
                gameboard.removeView(fly)
            } else {
                number++
            }
        }
    }

    private fun failedLevel() : Boolean{
        if(time == 0 && catchedFlys < flys){
            gameOverScreen()
            return true
        }else{
            return false
        }
    }

    private fun checkIfLevelPassed() : Boolean{
        return catchedFlys >= flys
    }

    private fun gameOverScreen(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Level 1")
        builder.setMessage("Oops, you did a poor job")
        builder.setNeutralButton("Try Again"){dialog, which ->
            val intent : Intent = Intent(this, Reaction_lvl1::class.java)
            startIntent(intent)
        }.show()
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
        catchedFlys++
        flys_to_hit--
        updateScreen()
        gameboard.removeView(v)
    }

    override fun run(){
        countdownTIme()
    }

}
