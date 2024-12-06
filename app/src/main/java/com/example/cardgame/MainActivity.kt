package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cardgame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val rulesFragment = RulesFragment()
        binding.button2.setOnClickListener {   // lands in onStart() and onStop(),if user want to play again it lands in onRestart()
            moveTaskToBack(true) // the app lays in the background after pressed on quit "shuts down" instead of finish() or
        }                                   // finischAffinity()
        binding.button.setOnClickListener {
            val intent = Intent(this,GuessCardActivity::class.java)
            startActivity(intent)

        }

        binding.btnRules?.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main,rulesFragment)
            transaction.commit()
            binding.btnRules.isEnabled = false
            binding.button.isEnabled = false
        }

    }
}