package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameReactionBinding

class ReactionGame : AppCompatActivity(){

    private lateinit var bindingReactionGame : GameReactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionGame = GameReactionBinding.inflate(layoutInflater)
        setContentView(bindingReactionGame.root)
    }
}