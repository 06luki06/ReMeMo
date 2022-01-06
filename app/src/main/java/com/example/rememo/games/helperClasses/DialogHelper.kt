package com.example.rememo.games.helperClasses

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.R

class DialogHelper(context : Context) : AppCompatActivity(){
    private val con = context

    private val contextHelper = ContextHelper(con)

    fun levelsCompleted(title : String, message : String, button : String){
        val builder = AlertDialog.Builder(con)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNeutralButton(button) { dialog, _ ->
            dialog.dismiss()
        }.show()
    }

    fun levelPassed(title : String, message : String, button : String, c : Class<*>){
        val builder = AlertDialog.Builder(con)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(button){_, _ ->
            contextHelper.startIntent(c, true, flag = false)
        }.show()
    }

    fun levelFailed(title : String, message : String, button : String, c : Class<*>){
        val builder = AlertDialog.Builder(con)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(button){_, _ ->
            contextHelper.startIntent(c, true, flag = true)
        }.show()
    }

    fun restartLevel(title : String, message : String, yes : String, no : String, c : Class<*>){
        val builder = AlertDialog.Builder(con)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(yes) { _, _ ->
            contextHelper.startIntent(c, false, flag = true)
        }.show()

        builder.setNegativeButton(no) { _, _->
            onBackPressed()
        }.show()
    }
}