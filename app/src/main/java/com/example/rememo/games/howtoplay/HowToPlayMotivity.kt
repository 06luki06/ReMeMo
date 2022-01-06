package com.example.rememo.games.howtoplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.HowtoplayMotivityBinding

class HowToPlayMotivity : AppCompatActivity(){

    private lateinit var bindingHowToPlayMotivity : HowtoplayMotivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHowToPlayMotivity = HowtoplayMotivityBinding.inflate(layoutInflater)
        setContentView(bindingHowToPlayMotivity.root)
    }
}