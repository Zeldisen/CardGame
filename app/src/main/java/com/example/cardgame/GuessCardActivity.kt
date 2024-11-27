package com.example.cardgame

import android.content.Intent
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
        val player1Btn = findViewById<Button>(R.id.btnPlayer1)
        val player2Btn = findViewById<Button>(R.id.btnPlayer2)
        val rulesBtn = findViewById<Button>(R.id.btnRules)
        val finishbtn= findViewById<Button>(R.id.btnQuit)

        player1Btn.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }

        player2Btn.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
        finishbtn.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        rulesBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container,rulesFragment)
            transaction.commit()
        }
    }
}