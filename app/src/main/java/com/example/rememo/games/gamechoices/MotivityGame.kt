package com.example.rememo.games.gamechoices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.GameMotivityBinding
import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import com.example.rememo.games.howtoplay.HowToPlayMotivity
import com.example.rememo.games.memorylvls.*
import com.example.rememo.games.motivitylvls.*

class MotivityGame: AppCompatActivity() {

    private lateinit var bindingMotivityGame: GameMotivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMotivityGame = GameMotivityBinding.inflate(layoutInflater)
        setContentView(bindingMotivityGame.root)
        
        bindingMotivityGame.iBHowToPlayMotivity.setOnClickListener{goToHowToPlayMotivity()}
        bindingMotivityGame.btMotivityLv1.setOnClickListener{goToLvls(1)}
        bindingMotivityGame.btMotivityLv2.setOnClickListener{goToLvls(2)}
        bindingMotivityGame.btMotivityLv3.setOnClickListener{goToLvls(3)}
        bindingMotivityGame.btMotivityLv4.setOnClickListener{goToLvls(4)}
        bindingMotivityGame.btMotivityLv5.setOnClickListener{goToLvls(5)}
    }

    private fun goToHowToPlayMotivity() {

        val intent : Intent = Intent(this, HowToPlayMotivity::class.java)
        startIntent(intent)
    }

    private fun goToLvls(lvl : Int) {
        var intent: Intent? = null

        when (lvl) {
            1 -> intent = Intent(this, Motivity_lvl1::class.java)
            2 -> intent = Intent(this, Motivity_lvl2::class.java)
            3 -> intent = Intent(this, Motivity_lvl3::class.java)
            4 -> intent = Intent(this, Motivity_lvl4::class.java)
            5 -> intent = Intent(this, Motivity_lvl5::class.java)
            else -> { // Note the block
                print("this level does not exist")
            }
        }
        startIntent(intent)
    }

    private fun startIntent(intent : Intent?){
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                applicationContext, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}
