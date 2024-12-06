package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cardgame.databinding.FragmentPlayer2Binding


/**
 * A simple [Fragment] subclass.
 * Use the [Player2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Player2Fragment : Fragment() {

    private var _binding: FragmentPlayer2Binding? = null
    private val binding get() = _binding!!
    lateinit var vm: SharedViewModel

    private var points1:Int = 0
    private var points2:Int = 0
    private var wrongcounter1:Int = 0
    private var wrongcounter2:Int = 0

    private val player1Turn:String = "Player 1 turn"
    private val player2Turn:String = "Player 2 turn"

    /**
     * saving value in variable when phone rotate it does not disappear, this is instead of using VIewModel
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("points1",points1)
        outState.putInt("points2",points2)
        outState.putInt("wrongcounter1",wrongcounter1)
        outState.putInt("wrongcounter2",wrongcounter2)
        outState.putString("player1Turn",player1Turn)
        outState.putString("player2Turn",player2Turn)
    }
    /**
     * get the value in variable when the phone rotate, this is instead of using VIewModel
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState != null){
            points1 = savedInstanceState.getInt("points1")
            points2 = savedInstanceState.getInt("points2")
            wrongcounter1 = savedInstanceState.getInt("wrongcounter1")
            wrongcounter2 = savedInstanceState.getInt("wrongcounter2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayer2Binding.inflate(inflater,container,false)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.p1ShowPoints.text = points1.toString()
        binding.p1WrongGuess.text = wrongcounter1.toString()
        binding.p2ShowPoints.text = points2.toString()
        binding.p2WrongGuess.text = wrongcounter2.toString()

        var previusCard1 = -1
        var currentCard1 = 1
        var previusCard2 = -1
        var currentCard2 = 1
        var currentPlayer = 1

        var isHigherGuess = false
        var isHigherGuess2 = false

        /**
         * if deck is empty, points1 and point2 compared with each other to se who wins or if its a tie
         */
        fun checkwinner(){
            if (vm.isDeckEmpty.value == true) {  // checks if deck is empty to
                val winnerMessage = when { // prints message in toast depending what happend in the game
                    points1 > points2 -> "Player 1 wins!"
                    points1 < points2 -> "Player 2 wins!"
                    else -> "It's a tie!"
                }
                Toast.makeText(requireContext(), winnerMessage, Toast.LENGTH_LONG).show()
                // makes higher and lower buttons not in use until user wants to play again
                binding.imageButtonHigher.isEnabled = false
                binding.imageButtonLower.isEnabled = false
                binding.imageButtonHigher2.isEnabled = false
                binding.imageButtonLower2.isEnabled = false

                binding.imageView2.visibility = View.GONE // backside cards disapears when deck is empty
                binding.imageView3.visibility = View.GONE


                binding.tvPlayerTurn.text = "Game Over! Play again to restart."
               // Log.d("HighScores", scoreManager.highScores.joinToString { "P1: ${it.player1score}, P2: ${it.player2score}" })
            }
        }

        /**
         * set higher and lower buttons enabled or not enabled depending on whos turn it is
         */
        fun upDateTurnUI(){
            if (currentPlayer == 1){
                binding.imageButtonHigher2.isEnabled = false
                binding.imageButtonLower2.isEnabled = false
                binding.imageButtonHigher.isEnabled = true
                binding.imageButtonLower.isEnabled = true
            }else{
                binding.imageButtonHigher2.isEnabled = true
                binding.imageButtonLower2.isEnabled = true
                binding.imageButtonHigher.isEnabled = false
                binding.imageButtonLower.isEnabled = false
            }
        }
        /**
         * updates guesses higher to check if the guess is correct or not for player 1
         * updates points and prints points and wrongpoints
         */
        fun updateUiForGuess(isHigherGuess: Boolean, currentCard1: Int, previusCard1: Int) {

            if (isHigherGuess && currentCard1 > previusCard1 ||
                !isHigherGuess && currentCard1 < previusCard1) {
                points1++

                Log.d("Debug", "Points increased: $points1")
                binding.p1ShowPoints.text = points1.toString()
            } else {
                wrongcounter1++
                Log.d("Debug", "Wrong counter increased: $wrongcounter1")
                binding.p1WrongGuess.text = wrongcounter1.toString()
            }

        }

        /**
         * updates guesses higher to check if the guess is correct or not for player 2
         * updates points and prints points and wrongpoints
         */
        fun updateUiForGuess2(isHigherGuess2: Boolean, currentCard2: Int, previusCard2: Int) {
            if (isHigherGuess2 && currentCard2 > previusCard2 ||
                !isHigherGuess2 && currentCard2 < previusCard2) {
                points2++

                Log.d("Debug", "Points increased: $points2")
                binding.p2ShowPoints.text = points2.toString()
            } else {
                wrongcounter2++
                Log.d("Debug", "Wrong counter increased: $wrongcounter2")
                binding.p2WrongGuess.text = wrongcounter2.toString()

            }

        }
        vm.isDeckEmpty.observe(viewLifecycleOwner) { isEmpty ->  // observes if deck is empty or not
            if (isEmpty) {
                Log.d("CheckWinner", "Deck is empty, calling checkwinner()")
                checkwinner() // calls on checkwinner if deck is empty
                val intent = Intent(requireActivity(), GameActivity::class.java)
                intent.putExtra("points1", points1) // sends points to GameActivity
                intent.putExtra("points2",points2)
                startActivity(intent)  // start Gameactivity to show highscores
            }
        }
        vm.drawRandomCardPlayer1()  // draws a first random card in pile 1
        vm.selectedCard1.observe(viewLifecycleOwner) { selectedCard ->
            // gets and shows card
            if (selectedCard != null) {
                binding.showCard1.setImageResource(selectedCard.imageResId)
                currentCard1 = selectedCard.value
                if (previusCard1 != -1) {
                    updateUiForGuess(isHigherGuess, currentCard1, previusCard1)
                }
                previusCard1 = currentCard1 // sets previusCard to currentCard so same card not can be drawn again

            }

            Log.d("CheckWinner", "checkWinner called")
        }
        vm.drawRandomCardPlayer2()  // draws a first card from pile player2
        vm.selectedCard2.observe(viewLifecycleOwner) { selectedCard ->
            // gets and shows a card
            if (selectedCard != null) {
                binding.showCard2.setImageResource(selectedCard.imageResId)
                currentCard2 = selectedCard.value
                if (previusCard2 != -1) {
                    updateUiForGuess2(isHigherGuess2, currentCard2, previusCard2)
                }
                previusCard2 = currentCard2 // sets previusCard to currentCard so same card not can be drawn again

            }

            Log.d("CheckWinner", "checkWinner called")
        }

        binding.imageButtonHigher.setOnClickListener {
            if (previusCard1 == -1) return@setOnClickListener
            isHigherGuess = true
            vm.drawRandomCardPlayer1()
            currentPlayer=2
            upDateTurnUI()
            binding.tvPlayerTurn.text = player2Turn
            checkwinner()
        }
        binding.imageButtonLower.setOnClickListener {
            if (previusCard1 == -1) return@setOnClickListener
            isHigherGuess = false
            vm.drawRandomCardPlayer1()
            currentPlayer=2
            upDateTurnUI()
            binding.tvPlayerTurn.text = player2Turn
            checkwinner()
        }

        binding.imageButtonHigher2.setOnClickListener {
            if (previusCard2 == -1) return@setOnClickListener
            isHigherGuess2 = true
            vm.drawRandomCardPlayer2()
            currentPlayer = 1
            upDateTurnUI()
            binding.tvPlayerTurn.text = player1Turn
            checkwinner()
        }

        binding.imageButtonLower2.setOnClickListener {

            if (previusCard2 == -1) return@setOnClickListener
            isHigherGuess2 = false
            vm.drawRandomCardPlayer2()
            currentPlayer = 1
            upDateTurnUI()
            binding.tvPlayerTurn.text = player1Turn
            checkwinner()
        }

        binding.player2BackBtn.setOnClickListener {
            val intent = Intent(requireActivity(), GuessCardActivity::class.java)
            startActivity(intent)
        }

        binding.playAgainBtn.setOnClickListener {
            vm.resetDeck()
            points1=0  //resets all variables to ordinary value from points1 -> currentPlayer
            points2=0
            wrongcounter1=0
            wrongcounter2=0
            previusCard1 = -1
            previusCard2 = -1
            currentCard1 = 1
            currentCard2 = 1
            currentPlayer = 1
            upDateTurnUI()
            binding.p1ShowPoints.text = points1.toString()
            binding.p2ShowPoints.text = points2.toString()
            binding.p1WrongGuess.text = wrongcounter1.toString()
            binding.p2WrongGuess.text = wrongcounter2.toString()
            binding.tvPlayerTurn.text = "Player 1 turn"

            binding.imageButtonHigher.isEnabled = true
            binding.imageButtonLower.isEnabled = true
            binding.imageButtonHigher2.isEnabled = true
            binding.imageButtonLower2.isEnabled = true
            vm.drawRandomCardPlayer1()   // draws new card for new game player 1 and 2
            vm.drawRandomCardPlayer2()
            binding.imageView2.visibility = View.VISIBLE   // set backside card visible again to simulate a new deck of cards
            binding.imageView3.visibility = View.VISIBLE
            Log.d("PlayAgain", "Points reset: $points1, $points2")
            Log.d("PlayAgain", "Deck reset, cards available: ${vm.isDeckEmpty.value}")
            Log.d("PlayAgain", "Turn reset: Current player = $currentPlayer")
        }

        return binding.root
    }

    override fun onDestroyView() { // added to avoid memoryleks
        super.onDestroyView()
        _binding = null
    }

}