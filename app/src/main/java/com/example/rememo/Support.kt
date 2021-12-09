package com.example.rememo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.SupportBinding

class Support : AppCompatActivity(){

    private lateinit var bindingSupport : SupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSupport = SupportBinding.inflate(layoutInflater)
        setContentView(bindingSupport.root)

        bindingSupport.btMailMarkus.setOnClickListener{}
    }

    private fun openMail(){
        val mailIntent: Intent = Uri.parse("mailto:lukas.riegler@napalmrecords.com").let {
            Intent(Intent.ACTION_SENDTO, it).apply {
                putExtra(Intent.EXTRA_SUBJECT, "Testnachricht")
            }
        }
        
        try {
            startActivity(mailIntent)
        }catch (e: ActivityNotFoundException){
            //Feedback to User
        }
    }
}