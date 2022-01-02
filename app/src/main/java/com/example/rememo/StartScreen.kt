package com.example.rememo

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.rememo.databinding.ActivityMainBinding
import com.example.rememo.games.GameChoice

class StartScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animationFading : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iBGoToPlayChoice.setOnClickListener{goToGames()}
        animationFading = AnimationUtils.loadAnimation(this, R.anim.faden)
        binding.homeSite.startAnimation(animationFading)
    }

    private fun goToGames() {
        val intent = Intent(this, GameChoice::class.java)
        startIntent(intent)
    }

    private fun startIntent(intent: Intent){
        try {
            finish()
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}