package com.mcuhq.projetfinaltest.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mcuhq.projetfinaltest.R
import com.mcuhq.projetfinaltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        fun getStartIntent(Context: Context): Intent{
            return Intent(Context, MainActivity::class.java)
        }
    }

    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}