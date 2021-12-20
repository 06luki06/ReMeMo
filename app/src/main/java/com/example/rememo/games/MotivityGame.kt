package com.example.rememo.games

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameMotivityBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.memorylvls.Memory_lvl1
import com.example.rememo.games.motivitylvls.Motivity_lvl1

class MotivityGame: AppCompatActivity() {

    private lateinit var bindingMotivityGame: GameMotivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityGame = GameMotivityBinding.inflate(layoutInflater)
        setContentView(bindingMotivityGame.root)


        bindingMotivityGame.iBHowToPlayMotivity.setOnClickListener{goToHowToPlayMotivity()}
        bindingMotivityGame.btMotivityLv1.setOnClickListener{goToMemoryLvl1()}
    }

    private fun goToHowToPlayMotivity() {

        val intent: Intent = Intent(this, HowToPlayMotivity::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToMemoryLvl1() {

        val intent : Intent = Intent(this, Motivity_lvl1::class.java)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }

}
