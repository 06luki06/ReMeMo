package com.example.rememo.games.gamechoices

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.databinding.GameMemoryBinding
import com.example.rememo.games.memorylvls.*

class MemoryGame : AppCompatActivity(){

    private lateinit var bindingMemoryGame : GameMemoryBinding
    var numbers : ArrayList<Int> = arrayListOf(1)
    var lvls_for_activate : MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryGame = GameMemoryBinding.inflate(layoutInflater)
        setContentView(bindingMemoryGame.root)

        bindingMemoryGame.iBHowToPlayMemory.setOnClickListener{goToHowToPlayMemory()}

        retrieveSharedPreferences()

    }

    private fun goToHowToPlayMemory() {

        intent = Intent(this, HowToPlayMemory::class.java)
        startIntent(intent)
    }

    private fun goToLvls(lvl : Int){
        var intent: Intent? = null

        when (lvl) {
            1 -> intent = Intent(this, Memory_lvl1::class.java)
            2 -> intent = Intent(this, Memory_lvl2::class.java)
            3 -> intent = Intent(this, Memory_lvl3::class.java)
            4 -> intent = Intent(this, Memory_lvl4::class.java)
            5 -> intent = Intent(this, Memory_lvl5::class.java)
            else -> {
                print("this level does not exist")
            }
        }
        startIntent(intent)
    }

    private fun startIntent(intent : Intent?){
        try {
            finish()
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    fun retrieveSharedPreferences(){
        val preferences: SharedPreferences = getSharedPreferences("Levels_Memory", 0)

        val lvl1 : Button = bindingMemoryGame.btMemoryLv1
        val lvl2 : Button = bindingMemoryGame.btMemoryLv2
        val lvl3 : Button = bindingMemoryGame.btMemoryLv3
        val lvl4 : Button = bindingMemoryGame.btMemoryLv4
        val lvl5 : Button = bindingMemoryGame.btMemoryLv5


        val lvls : ArrayList<Button> = arrayListOf(lvl1, lvl2, lvl3, lvl4, lvl5)
        for(i in lvls){
            i.setBackgroundColor(Color.RED)
        }

        if(preferences.getString("lvl_1_checked", "false").equals("false")) {
            changeBackgroundToActive(lvl1)

            activateLvl(1, lvl1)
        }

        if(preferences.getString("lvl_1_checked", "false").equals("true")){
            lvlMastered(lvl1)
            changeBackgroundToActive(lvl2)
            activateLvl(1, lvl1)
            activateLvl(2, lvl2)
        }

        if(preferences.getString("lvl_2_checked", "false").equals("true")){
            lvlMastered(lvl2)
            changeBackgroundToActive(lvl3)
            activateLvl(3, lvl3)
        }

        if(preferences.getString("lvl_3_checked", "false").equals("true")){
            lvlMastered(lvl3)
            changeBackgroundToActive(lvl4)
            activateLvl(4, lvl4)
        }

        if(preferences.getString("lvl_4_checked", "false").equals("true")){
            lvlMastered(lvl4)
            changeBackgroundToActive(lvl5)
            activateLvl(5, lvl5)
        }

        if(preferences.getString("lvl_5_checked", "false").equals("true")){
            lvlMastered(lvl5)
            activateLvl(5, lvl5)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Everything done!")
            builder.setMessage("Great, you have passed all leveles. Good job!!")
            builder.setPositiveButton("Play Levels again"){dialog, which ->
                dialog.dismiss()
            }.show()
        }
    }

    private fun activateLvl(lvlNumbers : Int, lvl : Button){
        lvl.setOnClickListener{goToLvls(lvlNumbers)}
    }

    private fun changeBackgroundToActive(lvl : Button){
        lvl.setBackgroundColor(Color.YELLOW)
    }

    private fun lvlMastered(lvl : Button){
        lvl.setBackgroundColor(Color.GREEN)
    }
}