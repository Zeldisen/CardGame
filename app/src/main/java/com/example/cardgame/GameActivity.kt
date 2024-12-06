package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cardgame.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {

   // lateinit var vm: SharedViewModel  // tryed to have ViewModel for highscores but did not make it work

    lateinit var binding: ActivityGameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // vm = ViewModelProvider(this).get(SharedViewModel::class.java)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //get points from players 1 and 2 from Player2Fragment
        val points1 = intent.getIntExtra("points1",0)
        val points2 = intent.getIntExtra("points2",0)
        //Saves points from Player2Fragment in to HighScorePlayer1 and 2
        val sharedPreferences = getSharedPreferences("HighScores", MODE_PRIVATE)
        val highScoresPlayer1 = sharedPreferences.getStringSet("player1_highscores", mutableSetOf())?.toMutableList() ?: mutableListOf()
        val highScoresPlayer2 = sharedPreferences.getStringSet("player2_highscores", mutableSetOf())?.toMutableList() ?: mutableListOf()
        val editor = sharedPreferences.edit()

        //adds Score
        highScoresPlayer1.add(points1.toString())
        highScoresPlayer2.add(points2.toString())


        editor.putStringSet("player1_highscores", highScoresPlayer1.toSet())
        editor.putStringSet("player2_highscores", highScoresPlayer2.toSet())
        editor.apply()

        // Set a text and the value of hihgScore 1 and 2
        val highScoresText1 = highScoresPlayer1.map { it.toInt() }.joinToString("\n") { "Player 1: $it" }
        val highScoresText2 = highScoresPlayer2.map { it.toInt() }.joinToString("\n") { "Player 2: $it" }

        //sets text in textViews
        binding.HighScores.text = highScoresText1
        binding.higescores2.text = highScoresText2


        //back to GuessCardActivity
        binding.Hbackbtn.setOnClickListener {
            val intent = Intent(this,GuessCardActivity::class.java)
            startActivity(intent)
        }
        // resets Highscore
        binding.resetButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("HighScores", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("player1_highscores")
            editor.remove("player2_highscores")
            editor.apply()

            binding.HighScores.text = ""
            binding.higescores2.text = ""

            Toast.makeText(this, "Highscores reset", Toast.LENGTH_SHORT).show()


        }


    }
}