package com.example.cardgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    lateinit var vm: SharedViewModel
    //val bundle = Bundle()
    lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vm = ViewModelProvider(this).get(SharedViewModel::class.java)
        vm.drawRandomCard()

        val player1Fragment = Player1Fragment()

        binding.startGameButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.gameFrLayout,player1Fragment)
            transaction.commit()
        }
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}