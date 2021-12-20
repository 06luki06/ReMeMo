package com.example.rememo.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rememo.databinding.SupportBinding

class Support : AppCompatActivity(){

    private lateinit var bindingSupport : SupportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSupport = SupportBinding.inflate(layoutInflater)
        setContentView(bindingSupport.root)

        bindingSupport.btMailMarkus.setOnClickListener{sendMailToMarkus()}
        bindingSupport.btMailVanessa.setOnClickListener{sendMailToVanessa()}
        bindingSupport.btMailLukas.setOnClickListener{sendMailToLukas()}
    }

    private fun sendMailToMarkus(){
        val mailIntent: Intent = Uri.parse("mailto:markus.almer@edu.fh-joanneum.at").let {
            Intent(Intent.ACTION_SENDTO, it).apply {
                putExtra(Intent.EXTRA_SUBJECT, "SUPPORT-Anfrage ReMeMo")
            }
        }
        
        try {
            startActivity(mailIntent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(
                applicationContext, "Mail konnte nicht gesendet werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendMailToVanessa(){
        val mailIntent: Intent = Uri.parse("mailto:vanessa.riedl@edu.fh-joanneum.at").let {
            Intent(Intent.ACTION_SENDTO, it).apply {
                putExtra(Intent.EXTRA_SUBJECT, "SUPPORT-Anfrage ReMeMo")
            }
        }

        try {
            startActivity(mailIntent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(
                applicationContext, "Mail konnte nicht gesendet werden", Toast.LENGTH_LONG).show()
        }
    }

    private fun sendMailToLukas(){
        val mailIntent: Intent = Uri.parse("mailto:lukas.riegler@edu.fh-joanneum.at").let {
            Intent(Intent.ACTION_SENDTO, it).apply {
                putExtra(Intent.EXTRA_SUBJECT, "SUPPORT-Anfrage ReMeMo")
            }
        }

        try {
            startActivity(mailIntent)
        }catch (e: ActivityNotFoundException){
            Toast.makeText(
                applicationContext, "Mail konnte nicht gesendet werden", Toast.LENGTH_LONG).show()
        }
    }
}