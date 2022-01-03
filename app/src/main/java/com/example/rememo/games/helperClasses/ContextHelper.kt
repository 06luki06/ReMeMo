package com.example.rememo.games.helperClasses

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContextHelper(context : Context) : AppCompatActivity(){
    private val con = context

    fun startIntent(c : Class<*>, finish : Boolean, flag : Boolean?){
        val intent = Intent(con, c)

        if(finish){
            finish()
        }

        if(flag == true){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        powerIntent(intent)
    }

    fun gameIntent(game : String, c : Class<*>){
        val intent = Intent(con, c)
        intent.putExtra("game", game)
        powerIntent(intent)
    }

    private fun powerIntent(intent : Intent){
        try {
            con.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                con, "Aktivität konnte nicht weitergegeben werden", Toast.LENGTH_LONG).show()
        }
    }
}