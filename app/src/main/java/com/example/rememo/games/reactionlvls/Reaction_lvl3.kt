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
import com.example.rememo.databinding.ReactionLvl2Binding
import com.example.rememo.databinding.ReactionLvl3Binding
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.games.pauseScreens.Pause
import java.util.*
import kotlin.math.roundToInt

class Reaction_lvl3 : AppCompatActivity(), View.OnClickListener, Runnable {

    private lateinit var bindingReaction_lvl3 : ReactionLvl3Binding
    private var gameAlreadyStarted : Boolean = false
    private var flys : Int = 0
    private var flys_to_hit : Int = 20
    private var catchedFlys : Int = 0
    private var time : Int = 0
    private var scale : Float = 0F
    private var randdomNumberGenerator : Random = Random()
    private lateinit var gameboard : ViewGroup
    private val HIGHEST_AGE : Long = 1500
    private val handler : Handler = Handler()
    private lateinit var clapping : MediaPlayer
    private lateinit var sum : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReaction_lvl3= ReactionLvl3Binding.inflate(layoutInflater)
        setContentView((bindingReaction_lvl3.root))

        bindingReaction_lvl3.iBPauseScreen.setOnClickListener{goToPause()}
        scale = resources.displayMetrics.density
        startGame()
        gameboard = bindingReaction_lvl3.FLGameboard
        sum = MediaPlayer.create(this, R.raw.summen)
        clapping = MediaPlayer.create(this, R.raw.clapping)
    }

    private fun goToPause(){

        val intent: Intent = Intent(this, Pause::class.java)

        val game : String = "reaction"
        intent.putExtra("game", game)
        startActivity(intent)
    }

    private fun startGame(){
        gameAlreadyStarted = true
        flys = 20
        catchedFlys = 0
        time = 30
        updateScreen()
        handler.postDelayed(this, 1000)
    }

    private fun updateScreen(){
        val tV_flies_left : TextView = bindingReaction_lvl3.tVFlysLeft
        tV_flies_left.setText(flys_to_hit.toString())

        val tv_catched_flys : TextView = bindingReaction_lvl3.tVCatchedFlies
        tv_catched_flys.setText(catchedFlys.toString())

        val tVTimeLeft : TextView = bindingReaction_lvl3.tVTimeLeft
        tVTimeLeft.setText(time.toString())

        val flys_left_bar : FrameLayout = bindingReaction_lvl3.FLCatchedFlys
        val time_left_bar : FrameLayout = bindingReaction_lvl3.FLTimeLeft

        val lpCatched : ViewGroup.LayoutParams = flys_left_bar.layoutParams
        lpCatched.width = Math.round(scale * 300 * Math.min(catchedFlys, flys) / flys)

        val lpTime : ViewGroup.LayoutParams = time_left_bar.layoutParams
        lpTime.width = Math.round(scale * time * 300 / 30)
    }

    private fun countdownTIme(){
        time--
        val randomNumber : Float = randdomNumberGenerator.nextFloat()
        val propability : Double = flys * 2.0 / 60
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
                clapping.start()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Level 1")
                builder.setMessage("Great, you have nailed it")
                builder.setNeutralButton("go back to the levels"){dialog, which ->
                    val intent : Intent = Intent(this, ReactionGame::class.java)
                    startIntent(intent)
                }.show()
                writeIntoSharedPrefs("lvl_3_checked")
            }
        }
    }

    private fun showFlys(){
        sum.seekTo(0)
        sum.start()
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
                sum.pause()
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
            val intent : Intent = Intent(this, Reaction_lvl3::class.java)
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
        sum.pause()
    }

    override fun run(){
        countdownTIme()
    }

    private fun writeIntoSharedPrefs(lvl: String){
        val prefs : SharedPreferences = getSharedPreferences("Levels_Reaction", 0)
        prefs
            .edit()
            .putString(lvl, "true")
            .apply()
    }

    override fun onDestroy(){
        sum.release()
        clapping.release()
        super.onDestroy()
    }

}
