package com.example.rememo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.rememo.databinding.ActivityMainBinding
import com.example.rememo.games.GameChoice
import com.example.rememo.games.helperClasses.ContextHelper

class StartScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animationFading : Animation
    private val contextHelper = ContextHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBGoToPlayChoice.setOnClickListener{goToGames()}
        animationFading = AnimationUtils.loadAnimation(this, R.anim.faden)
        binding.homeSite.startAnimation(animationFading)
        //fasdfasdf
        //rrrrrr
    }

    private fun goToGames() {
        contextHelper.startIntent(GameChoice::class.java, true, flag = false)
    }
}