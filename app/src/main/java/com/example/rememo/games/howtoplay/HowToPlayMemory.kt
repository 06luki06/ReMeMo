package com.example.rememo.games.howtoplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.HowtoplayMemoryBinding

class HowToPlayMemory : AppCompatActivity(){

    private lateinit var bindingHowToPlayMemory : HowtoplayMemoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHowToPlayMemory = HowtoplayMemoryBinding.inflate(layoutInflater)
        setContentView(bindingHowToPlayMemory.root)
    }
}