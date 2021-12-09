package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.HighscoreGamesBinding

class HighscoreGames : AppCompatActivity(){

    private lateinit var bindingHighscoreGames : HighscoreGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHighscoreGames = HighscoreGamesBinding.inflate(layoutInflater)
        setContentView(bindingHighscoreGames.root)
    }
}