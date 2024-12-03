package com.example.cardgame

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayer2Binding.inflate(inflater,container,false)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        var previusCard1 = -1
        var currentCard1 = 1
        var previusCard2 = -1
        var currentCard2 = 1
        var wrongcounter1 = 0
        var wrongcounter2 = 0
        var currentPlayer = 1
        val player1Turn = "Player 1 turn"
        val player2Turn = "Player 2 turn"
        var points1 = 0
        var points2 = 0
        var isHigherGuess = false
        var isHigherGuess2 = false

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
        vm.drawRandomCardPlayer1()  // drar ett första slumpmässigt kort i hög 1
        vm.selectedCard1.observe(viewLifecycleOwner) { selectedCard ->
            // Hämta och visa slumpmässigt kort
            if (selectedCard != null) {
                binding.showCard1.setImageResource(selectedCard.imageResId)
                currentCard1 = selectedCard.value
                if (previusCard1 != -1) { // Kontrollera bara om det redan finns ett tidigare kort
                    updateUiForGuess(isHigherGuess, currentCard1, previusCard1)
                }
                previusCard1 = currentCard1
            }
        }
        vm.drawRandomCardPlayer2()  // drar ett första slumpmässigt kort i hög 2
        vm.selectedCard2.observe(viewLifecycleOwner) { selectedCard ->
            // Hämta och visa slumpmässigt kort
            if (selectedCard != null) {
                binding.showCard2.setImageResource(selectedCard.imageResId)
                currentCard2 = selectedCard.value
                if (previusCard2 != -1) { // Kontrollera bara om det redan finns ett tidigare kort
                    updateUiForGuess2(isHigherGuess2, currentCard2, previusCard2)
                }
                previusCard2 = currentCard2
            }
        }

        binding.imageButtonHigher.setOnClickListener {
            if (previusCard1 == -1) return@setOnClickListener
            isHigherGuess = true
            vm.drawRandomCardPlayer1()
            currentPlayer=2
            upDateTurnUI()
            binding.tvPlayerTurn.text = player2Turn
            Toast.makeText(requireContext(), "Player 2 Turn", Toast.LENGTH_LONG).show()
        }
        binding.imageButtonLower.setOnClickListener {
            if (previusCard1 == -1) return@setOnClickListener
            isHigherGuess = false
            vm.drawRandomCardPlayer1()
            currentPlayer=2
            upDateTurnUI()
            binding.tvPlayerTurn.text = player2Turn
            Toast.makeText(requireContext(), "Player 2 Turn", Toast.LENGTH_LONG).show()
        }
        binding.imageButtonHigher2.setOnClickListener {
            if (previusCard2 == -1) return@setOnClickListener
            isHigherGuess2 = true
            vm.drawRandomCardPlayer2()
            currentPlayer = 1
            upDateTurnUI()
            binding.tvPlayerTurn.text = player1Turn
            Toast.makeText(requireContext(), "Player 1 Turn", Toast.LENGTH_LONG).show()
        }
        binding.imageButtonLower2.setOnClickListener {
            if (previusCard2 == -1) return@setOnClickListener
            isHigherGuess2 = false
            vm.drawRandomCardPlayer2()
            currentPlayer = 1
            upDateTurnUI()
            binding.tvPlayerTurn.text = player1Turn
            Toast.makeText(requireContext(), "Player 1 Turn", Toast.LENGTH_LONG).show()
        }


        return binding.root
    }

    override fun onDestroyView() { // added to avoid memoryleks
        super.onDestroyView()
        _binding = null
    }

}