package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.databinding.ActivityGuessCardBinding

class GuessCardActivity : AppCompatActivity() {
    lateinit var binding:ActivityGuessCardBinding
    lateinit var vm: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGuessCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val player1Fragment = Player1Fragment()
        val player2Fragment = Player2Fragment()

        vm = ViewModelProvider(this).get(SharedViewModel::class.java)
        vm.drawRandomCardPlayer1()
        vm.drawRandomCardPlayer2()
        vm.resetDeck()
        vm.isDeckEmpty()

        binding.btnPlayer1.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container,player1Fragment)
            transaction.commit()
        }

        binding.btnPlayer2.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fr_container,player2Fragment)
            transaction.commit()
        }
        binding.btnHighscore?.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
        binding.btnQuit.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}