package com.example.rememo.games.memorylvls

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.MemoryLvl1Binding

class Memory_lvl1 : AppCompatActivity(){

private lateinit var bindingMemorylvl1 : MemoryLvl1Binding

 override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMemorylvl1=MemoryLvl1Binding.inflate(layoutInflater)
        setContentView(bindingMemorylvl1.root)

    b
    }

}