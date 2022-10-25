package com.example.madlibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_menu_principal.*
import kotlinx.android.synthetic.main.activity_principal.*
import java.util.*

class MenuPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val intent = Intent(this, MainActivity::class.java)
        continuarxd.setOnClickListener(){
            startActivity(intent)
        }

    }

}