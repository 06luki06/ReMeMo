package com.example.rememo.highscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryHighscoreBinding
import com.example.rememo.databinding.MotivityHighscoreBinding

class MotivityHighscore : AppCompatActivity(){

    private lateinit var bindingMotivityHighscore: MotivityHighscoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityHighscore = MotivityHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingMotivityHighscore.root)

}}