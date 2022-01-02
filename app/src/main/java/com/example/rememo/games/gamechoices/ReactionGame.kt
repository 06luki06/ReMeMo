package com.example.rememo.games.gamechoices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameReactionBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.rememo.R
import com.example.rememo.games.howtoplay.HowToPlayReaction
import com.example.rememo.games.reactionlvls.*

class ReactionGame : AppCompatActivity(){

    private lateinit var bindingReactionGame : GameReactionBinding
    private var lvlDesign = LvlDesign()
    private lateinit var mp : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionGame = GameReactionBinding.inflate(layoutInflater)
        setContentView(bindingReactionGame.root)
        mp = MediaPlayer.create(this, R.raw.clapping)

        bindingReactionGame.iBHowToPlayReaction.setOnClickListener{goToHowToPlayReaction()}

        retrieveSharedPreferences()
    }

    private fun goToHowToPlayReaction() {
        val intent = Intent(this, HowToPlayReaction::class.java)
        startIntent(intent)
    }

    private fun goToLvls(lvl : Int) {
        var intent: Intent? = null

        when (lvl) {
            1 -> intent = Intent(this, ReactionLvl1::class.java)
            2 -> intent = Intent(this, ReactionLvl2::class.java)
            3 -> intent = Intent(this, ReactionLvl3::class.java)
            4 -> intent = Intent(this, ReactionLvl4::class.java)
            5 -> intent = Intent(this, ReactionLvl5::class.java)
        }
        startIntent(intent)
    }

    private fun startIntent(intent : Intent?){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val txt : String = e.stackTraceToString()
            Toast.makeText(
                applicationContext, txt, Toast.LENGTH_LONG).show()
        }
    }

    private fun retrieveSharedPreferences(){
        val preferences: SharedPreferences = getSharedPreferences("Levels_Reaction", 0)

        val lvl1 : Button = bindingReactionGame.btReactionLv1
        val lvl2 : Button = bindingReactionGame.btReactionLv2
        val lvl3 : Button = bindingReactionGame.btReactionLv3
        val lvl4 : Button = bindingReactionGame.btReactionLv4
        val lvl5 : Button = bindingReactionGame.btReactionLv5

        val lvls : ArrayList<Button> = arrayListOf(lvl1, lvl2, lvl3, lvl4, lvl5)
        for(i in lvls){
            i.setBackgroundColor(Color.RED)
        }

        if(preferences.getString("lvl_1_checked", "false").equals("false")) {
            lvlDesign.changeBackgroundToActive(lvl1)
            activateLvl(1, lvl1)
        }

        if(preferences.getString("lvl_1_checked", "false").equals("true")){
            lvlDesign.lvlMastered(lvl1)
            lvlDesign.changeBackgroundToActive(lvl2)
            activateLvl(1, lvl1)
            activateLvl(2, lvl2)
        }

        if(preferences.getString("lvl_2_checked", "false").equals("true")){
            lvlDesign.lvlMastered(lvl2)
            lvlDesign.changeBackgroundToActive(lvl3)
            activateLvl(3, lvl3)
        }

        if(preferences.getString("lvl_3_checked", "false").equals("true")){
            lvlDesign.lvlMastered(lvl3)
            lvlDesign.changeBackgroundToActive(lvl4)
            activateLvl(4, lvl4)
        }

        if(preferences.getString("lvl_4_checked", "false").equals("true")){
            lvlDesign.lvlMastered(lvl4)
            lvlDesign.changeBackgroundToActive(lvl5)
            activateLvl(5, lvl5)
        }

        if(preferences.getString("lvl_5_checked", "false").equals("true")){
            lvlDesign.lvlMastered(lvl5)
            activateLvl(5, lvl5)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Everything done!")
            builder.setMessage("Great, you have passed all leveles. Good job!!")
            builder.setPositiveButton("Play Levels again"){dialog, _ ->
                dialog.dismiss()
            }.show()
            mp.start()
        }
    }

    private fun activateLvl(lvlNumbers : Int, lvl : Button){
        lvl.setOnClickListener{goToLvls(lvlNumbers)}
    }

    override fun onDestroy() {
        mp.release()
        super.onDestroy()
    }
}