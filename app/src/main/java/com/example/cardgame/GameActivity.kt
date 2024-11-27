package com.example.cardgame

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameActivity : AppCompatActivity() {
    lateinit var deck :Deck
    val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        deck = Deck()
        val randomCard = deck.showRandomCard()

        bundle.putParcelable("selectedCard",randomCard)



        val player1Fragment = Player1Fragment()
        val gameFrLayout = findViewById<FrameLayout>(R.id.gameFrLayout)
        val startGameBtn = findViewById<Button>(R.id.startGameButton)
        val goBackBtn = findViewById<Button>(R.id.buttonBack)

        startGameBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.gameFrLayout,player1Fragment)
            transaction.commit()
        }
        goBackBtn.setOnClickListener {
            finish()
        }
    }
}