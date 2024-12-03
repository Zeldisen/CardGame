package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider


import com.example.cardgame.databinding.FragmentPlayer1Binding



/**
 * A simple [Fragment] subclass.
 * Use the [Player1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Player1Fragment : Fragment() {


    private var _binding: FragmentPlayer1Binding? = null
    private val binding get() = _binding!!
    lateinit var vm: SharedViewModel


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayer1Binding.inflate(inflater, container, false)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        var previusCard = -1
        var currentCard = 1
        var wrongcounter = 0
        var points = 0
        var isHigherGuess = false



        fun updateUiForGuess(isHigherGuess: Boolean, currentCard: Int, previusCard: Int) {
            if (isHigherGuess && currentCard > previusCard ||
                !isHigherGuess && currentCard < previusCard) {
                points++
                Log.d("Debug", "Points increased: $points")
                binding.tvShowPoints.text = points.toString()
            } else {
                wrongcounter++
                Log.d("Debug", "Wrong counter increased: $wrongcounter")
                binding.tvWrongCounter.text = wrongcounter.toString()
            }

        }

        vm.drawRandomCardPlayer1()  // drar ett första slumpmässigt kort
        vm.selectedCard1.observe(viewLifecycleOwner) { selectedCard ->
            // Hämta och visa slumpmässigt kort
            if (selectedCard != null) {
                binding.showCard.setImageResource(selectedCard.imageResId)
                currentCard = selectedCard.value
                if (previusCard != -1) { // Kontrollera bara om det redan finns ett tidigare kort
                    updateUiForGuess(isHigherGuess, currentCard, previusCard)
                }
                previusCard = currentCard
            }
        }


            binding.higherButton.setOnClickListener {
                if (previusCard == -1) return@setOnClickListener
                isHigherGuess = true
                vm.drawRandomCardPlayer1()
            }
            binding.lowerButton.setOnClickListener {
                if (previusCard == -1) return@setOnClickListener
                isHigherGuess = false
                vm.drawRandomCardPlayer1()
                }



            binding.p1BackBtn.setOnClickListener {
                val intent = Intent(requireActivity(), GameActivity::class.java)
                startActivity(intent)
            }


            return binding.root

        }
        override fun onDestroyView() { // added to avoid memoryleks
            super.onDestroyView()
            _binding = null
        }


    }
