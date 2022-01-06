package com.example.rememo.highscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryHighscoreBinding

class MemoryHighscore: AppCompatActivity() {

    private lateinit var bindingMemoryHighscore: MemoryHighscoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemoryHighscore = MemoryHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingMemoryHighscore.root)

}}