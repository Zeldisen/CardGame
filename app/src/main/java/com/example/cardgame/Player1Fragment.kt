package com.example.cardgame

import android.os.Bundle
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


    private var _binding:FragmentPlayer1Binding?=null
    private val binding get() = _binding!!
    lateinit var vm: SharedViewModel


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayer1Binding.inflate(inflater,container,false)
        vm = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val hihger = 0
        val points = 0

       binding.guessButton.setOnClickListener {
           vm.drawRandomCard()
           vm.selectedCard.observe(viewLifecycleOwner) { selectedCard ->
               // Hämta och visa slumpmässigt kort
               if (selectedCard != null) {
                   binding.showCard.setImageResource(selectedCard.imageResId)
               }
           }
        }


        return binding.root
    }

    override fun onDestroyView() { // added to avoid memoryleks
        super.onDestroyView()
        _binding = null
    }


}