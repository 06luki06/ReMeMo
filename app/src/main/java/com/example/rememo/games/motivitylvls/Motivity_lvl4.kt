package com.example.rememo.games.motivitylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MotivityLvl4Binding
import com.example.rememo.games.pauseScreens.Pause

class Motivity_lvl4 : AppCompatActivity(){

    private lateinit var bindingMotivityLvl4 : MotivityLvl4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityLvl4 = MotivityLvl4Binding.inflate(layoutInflater)
        setContentView(bindingMotivityLvl4.root)

        bindingMotivityLvl4.iBPauseScreen.setOnClickListener{goToPause()}
    }
    //TODO: has to be implemented

    private fun goToPause(){

        val intent: Intent = Intent(this, Pause::class.java)

        val game : String = "motivity"
        intent.putExtra("game", game)
        
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}