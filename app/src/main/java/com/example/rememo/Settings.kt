package com.example.rememo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.SettingsBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast

class Settings : AppCompatActivity(){

    private lateinit var bindingSettings : SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSettings = SettingsBinding.inflate(layoutInflater)
        setContentView(bindingSettings.root)

        bindingSettings.btSettingsSupport.setOnClickListener{goToSupport()}
        bindingSettings.btSettingCredits.setOnClickListener{goToCredits()}

    }

    private fun goToSupport() {

        val intent: Intent = Intent(this, Support::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }


    private fun goToCredits() {

        val intent: Intent = Intent(this, Credits::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}