package com.example.yourcloset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ScannerActivity : AppCompatActivity() {
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener() {
            val intent: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)


        }
    }
}