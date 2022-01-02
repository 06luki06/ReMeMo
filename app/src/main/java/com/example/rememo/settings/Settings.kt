package com.example.rememo.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.SettingsBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.rememo.StartScreen
import com.example.rememo.R
import java.util.*

class Settings : AppCompatActivity(){

    private lateinit var bindingSettings : SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSettings = SettingsBinding.inflate(layoutInflater)
        setContentView(bindingSettings.root)
        var prefs: SharedPreferences = getSharedPreferences("Soundsettings", 0)

        bindingSettings.btSettingsSupport.setOnClickListener { goToSupport() }
        bindingSettings.btSettingCredits.setOnClickListener { goToCredits() }
        bindingSettings.btVolumeUp.setOnClickListener { setVolumeUp(prefs) }
        bindingSettings.btVolumeDown.setOnClickListener { setVolumeDown(prefs) }
        bindingSettings.btSettingsReset.setOnClickListener { resetTheGame() }
        val iv_german : ImageView = bindingSettings.iVSettingsGerman
        val iv_english : ImageView = bindingSettings.iVSettingsEnglish

        checkLanguage(iv_german, iv_english)
        showPicture(prefs)
    }

    private fun goToSupport() {
        val intent: Intent = Intent(this, Support::class.java)
        startIntent(intent)
    }

    private fun goToCredits() {
        val intent: Intent = Intent(this, Credits::class.java)
        startIntent(intent)
    }

    private fun setVolumeDown(prefs : SharedPreferences){
        if(prefs.getInt("Sound", -1) == 0){
        }else{
            var sound : Int = prefs.getInt("Sound", -1)
            sound--
            writeIntoSharedPrefs(sound, prefs)
        }
    }

    private fun setVolumeUp(prefs : SharedPreferences){
        var sound : Int = prefs.getInt("Sound", -1)

        if(prefs.getInt("Sound", -1) == 3){
        }else{
            when(prefs.getInt("Sound", -1)){
                -1 -> sound += 2
                else -> sound++
            }
            writeIntoSharedPrefs(sound, prefs)
        }
    }

    private fun writeIntoSharedPrefs(sound : Int, prefs : SharedPreferences){
        prefs
            .edit()
            .putInt("Sound", sound)
            .apply()

        showPicture(prefs)
    }


    private fun showPicture(prefs: SharedPreferences){
        val soundNumber : Int = prefs.getInt("Sound", -1)
        val soundDisplay = bindingSettings.picVolume

        when(soundNumber){
            -1 -> {
                soundDisplay.setImageResource(R.drawable.aus)
                prefs
                    .edit()
                    .putInt("soundMin", 0)
                    .putInt("soundMax", 0)
                    .apply()}

            0 -> {
                soundDisplay.setImageResource(R.drawable.aus)
                prefs
                    .edit()
                    .putInt("soundMin", 0)
                    .putInt("soundMax", 0)
                    .apply()}

            1 -> {
                soundDisplay.setImageResource(R.drawable.leise)
                prefs
                    .edit()
                    .putInt("soundMin", 0)
                    .putInt("soundMax", 33)
                    .apply()}

            2 -> {
                soundDisplay.setImageResource(R.drawable.mittel)
                prefs
                    .edit()
                    .putInt("soundMin", 33)
                    .putInt("soundMax", 66)
                    .apply()}

            3 -> {
                soundDisplay.setImageResource(R.drawable.laut)
                prefs
                    .edit()
                    .putInt("soundMin", 66)
                    .putInt("soundMax", 100)
                    .apply()}
        }
    }

    private fun resetTheGame(){
        val memorySP : SharedPreferences = getSharedPreferences("Levels_Memory", 0)
        val intent : Intent = Intent(this, StartScreen::class.java)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Hawara")
        builder.setMessage("Wüsst wiakli ois leschn?")
        builder.setPositiveButton("LÖSCHE ALLES"){_, _ ->
            memorySP
                .edit()
                .clear()
                .apply()

            finish()
            startActivity(intent)
        }.show()

        builder.setNegativeButton("Ne doch nicht"){_, _ ->
            onBackPressed()
        }.show()
    }

    private fun startIntent(intent: Intent){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLanguage(german : ImageView, english : ImageView){
        val lang = Locale.getDefault().language

        if(lang == "de"){
            german.setBackgroundColor(Color.GRAY)
            english.setBackgroundColor(Color.WHITE)
        }

        if(lang == "en"){
            english.setBackgroundColor(Color.GRAY)
            german.setBackgroundColor(Color.WHITE)
        }
    }
}