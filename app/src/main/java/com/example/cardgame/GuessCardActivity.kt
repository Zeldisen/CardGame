package com.example.cardgame

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GuessCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_guess_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rulesFragment = RulesFragment()
        val rulesBtn = findViewById<Button>(R.id.btnRules)
        rulesBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container,rulesFragment)
            transaction.commit()
        }
    }
}