package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameMemoryBinding

class MemoryGame : AppCompatActivity(){

    private lateinit var bindingMemoryGame : GameMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryGame = GameMemoryBinding.inflate(layoutInflater)
        setContentView(bindingMemoryGame.root)
    }
}