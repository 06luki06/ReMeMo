package com.example.rememo.games.reactionlvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.ReactionLvl1Binding
import com.example.rememo.games.gamechoices.MemoryGame
import com.example.rememo.games.gamechoices.ReactionGame
import com.example.rememo.games.pauseScreens.Pause
import java.util.*

class Reaction_lvl1 : AppCompatActivity(){

    private lateinit var bindingReaction_lvl1 : ReactionLvl1Binding
    private var gameAlreadyStarted : Boolean = false
    private var flys : Int = 0
    private var catchedFlys : Int = 0
    private var time : Int = 0
    private val scale = resources.displayMetrics.density
    private var randdomNumberGenerator : Random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReaction_lvl1= ReactionLvl1Binding.inflate(layoutInflater)
        setContentView((bindingReaction_lvl1.root))
        bindingReaction_lvl1.iBPauseScreen.setOnClickListener{goToPause()}

        startGame()
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
    }

    private fun updateScreen(){
        val tV_flies_left : TextView = bindingReaction_lvl1.tVFlysLeft
        tV_flies_left.setText(flys.toString())

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
        var randomNumber : Float = randdomNumberGenerator.nextFloat()
        var propability : Double = flys * 1.5
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
            checkIfLevelPassed()
        }
    }


    private fun showFlys(){

    }

    private fun letFlysDisappear(){

    }

    private fun failedLevel() : Boolean{
        if(time == 0 && catchedFlys < flys){
            gameOverScreen()
            return true
        }else{
            return false
        }
    }

    private fun checkIfLevelPassed(){
        if(time == 0 && catchedFlys >= flys){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Level 1")
            builder.setMessage("Great, you have nailed it")
            builder.setNeutralButton("go back to the levels"){dialog, which ->
                val intent : Intent = Intent(this, ReactionGame::class.java)
                startIntent(intent)
            }.show()
        }
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
}
