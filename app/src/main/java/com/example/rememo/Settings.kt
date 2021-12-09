package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.SettingsBinding

class Settings : AppCompatActivity(){

    private lateinit var bindingSettings : SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSettings = SettingsBinding.inflate(layoutInflater)
        setContentView(bindingSettings.root)
    }
}