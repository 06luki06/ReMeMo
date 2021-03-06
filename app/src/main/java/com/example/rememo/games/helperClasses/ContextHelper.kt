package com.example.rememo.games.helperClasses

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R
import com.example.rememo.games.GameChoice

class ContextHelper(context : Context) : AppCompatActivity(){
    private val con = context

    fun startIntent(c : Class<*>, finish : Boolean, flag : Boolean?){
        val intent = Intent(con, c)

        if(flag == true){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        powerIntent(intent,finish)
    }

    fun gameIntent(game : String, c : Class<*>, c2 : Class<*>?){
        val intent = Intent(con, c)
        intent.putExtra("game", game)
        if(c2 != null){
            intent.putExtra("level", c2)
        }
        powerIntent(intent,finish = false)
    }

    fun goToGames(name : String){
        val intent = Intent(con, GameChoice::class.java)
        intent.putExtra("NameOfPlayer", name)
        powerIntent(intent, true)
    }

    private fun powerIntent(intent : Intent, finish: Boolean){
        try {
            if(finish){
               this.finish()
            }
            con.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                con, getText(R.string.activityNotFound), Toast.LENGTH_LONG).show()
        }
    }
}