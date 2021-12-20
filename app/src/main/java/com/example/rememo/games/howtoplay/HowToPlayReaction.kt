package com.example.rememo.games.howtoplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.HowtoplayReactionBinding

class HowToPlayReaction : AppCompatActivity(){

    private lateinit var bindingHowToPlayReaction : HowtoplayReactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHowToPlayReaction= HowtoplayReactionBinding.inflate(layoutInflater)
        setContentView(bindingHowToPlayReaction.root)
    }
}