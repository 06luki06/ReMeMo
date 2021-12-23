package com.example.rememo.games.memorylvls

import androidx.appcompat.app.AppCompatActivity

class Memory_game_engine : AppCompatActivity() {

    private var result: String = ""


    private fun selectRandomButtons(buttonchoice: Array<String>, howMuch : Int): ArrayList<String> {
        var randomNumber: Int

        val buttons = ArrayList<String>()

        for (i in 0..howMuch) {
            randomNumber = (0..buttons.size + 1).random()
            buttons.add(buttonchoice[randomNumber])
        }
        return buttons
    }

    fun saveAsString(buttonChoice: Array<String>, howMuch: Int): String {
        val arrayList: ArrayList<String> = selectRandomButtons(buttonChoice, howMuch)

        for (i in 0 until arrayList.size - 1) {
            result += arrayList[i]
        }
        return result
    }
}