package com.example.rememo.games.motivitylvls

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MotivityLvl1Binding
import com.example.rememo.databinding.MotivityLvl2Binding
import com.example.rememo.games.pauseScreens.Pause

class Motivity_lvl2 : AppCompatActivity(){

    private lateinit var bindingMotivity_lvl2 : MotivityLvl2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivity_lvl2= MotivityLvl2Binding.inflate(layoutInflater)
        setContentView(bindingMotivity_lvl2.root)

        bindingMotivity_lvl2.iBPauseScreen.setOnClickListener{goToPause()}
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