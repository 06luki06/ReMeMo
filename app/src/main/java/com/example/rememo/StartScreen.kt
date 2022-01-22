package com.example.rememo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.Toast
import com.example.rememo.databinding.ActivityMainBinding
import com.example.rememo.games.GameChoice
import com.example.rememo.games.helperClasses.ContextHelper

class StartScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var animationFading : Animation
    private val contextHelper = ContextHelper(this)
    private lateinit var enterName : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        animationFading = AnimationUtils.loadAnimation(this, R.anim.faden)
        binding.homeSite.startAnimation(animationFading)
        enterName = findViewById(R.id.PTEnterName)

        binding.iBGoToPlayChoice.setOnClickListener {

            if (enterName.text.toString() == "") {
                Toast.makeText(
                    this,
                    "Enter name" + enterName.text.toString(),
                    Toast.LENGTH_LONG
                ).show()

            } else {
                finish()
                contextHelper.goToGames(enterName.text.toString())
            }
        }
    }
}