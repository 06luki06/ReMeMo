package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameMotivityBinding

class MotivityGame: AppCompatActivity() {

    private lateinit var bindingMotivityGame: GameMotivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityGame = GameMotivityBinding.inflate(layoutInflater)
        setContentView(bindingMotivityGame.root)
    }
}
