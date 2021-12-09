package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.ActivityMainBinding
import com.example.rememo.databinding.GamesBinding

class GameChoice : AppCompatActivity() {

    private lateinit var bindingGames : GamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGames = GamesBinding.inflate(layoutInflater)
        setContentView(bindingGames.root)
    }
}