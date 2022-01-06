package com.example.rememo.highscore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.ReactionHighscoreBinding

class ReactionHighscore: AppCompatActivity() {

    private lateinit var bindingReactionHighscore: ReactionHighscoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingReactionHighscore = ReactionHighscoreBinding.inflate(layoutInflater)
        setContentView(bindingReactionHighscore.root)

}}