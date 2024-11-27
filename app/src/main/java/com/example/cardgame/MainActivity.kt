package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val finishBtn = findViewById<Button>(R.id.button2)
        val playGuessCardBtn = findViewById<Button>(R.id.button)

        finishBtn.setOnClickListener {   // hamnar i onStart() och onStop() och kan om användaren vill återuppta hamna i onRestart()
            moveTaskToBack(true) // låter appen ligga i bakgrunden efter att den "stängs ner" istället för finish() eller
        }                                   // finischAffinity()
        playGuessCardBtn.setOnClickListener {
            val intent = Intent(this,GuessCardActivity::class.java)
            startActivity(intent)
        }
    }
}