package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.CreditsBinding

class Credits : AppCompatActivity(){

    private lateinit var bindingCredits : CreditsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCredits = CreditsBinding.inflate(layoutInflater)
        setContentView(bindingCredits.root)
    }
}
