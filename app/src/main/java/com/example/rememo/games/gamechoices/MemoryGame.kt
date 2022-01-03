package com.example.rememo.games.gamechoices

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.games.howtoplay.HowToPlayMemory
import com.example.rememo.databinding.GameMemoryBinding
import com.example.rememo.games.helperClasses.ContextHelper
import com.example.rememo.games.helperClasses.GameChoiceHelper

class MemoryGame : AppCompatActivity(){

    private lateinit var bindingMemoryGame : GameMemoryBinding
    private var contextHelper = ContextHelper(this)
    private var gameChoice = GameChoiceHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryGame = GameMemoryBinding.inflate(layoutInflater)
        setContentView(bindingMemoryGame.root)

        bindingMemoryGame.iBHowToPlayMemory.setOnClickListener{goToHowToPlayMemory()}

        val lvl1 : Button = bindingMemoryGame.btMemoryLv1
        val lvl2 : Button = bindingMemoryGame.btMemoryLv2
        val lvl3 : Button = bindingMemoryGame.btMemoryLv3
        val lvl4 : Button = bindingMemoryGame.btMemoryLv4
        val lvl5 : Button = bindingMemoryGame.btMemoryLv5

        val lvls : ArrayList<Button> = arrayListOf(lvl1, lvl2, lvl3, lvl4, lvl5)
        for(i in lvls){
            i.setBackgroundColor(Color.RED)
        }

        gameChoice.retrieveSharedPreferences(lvls, "memory")
    }

    private fun goToHowToPlayMemory() {
        contextHelper.startIntent(HowToPlayMemory::class.java, false, flag = false)
    }
}