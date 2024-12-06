package com.example.cardgame

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.cardgame.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {
    private var _binding: FragmentRulesBinding?=null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRulesBinding.inflate(inflater,container,false)


        binding.backbtn.setOnClickListener {
            val intent = Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    override fun onDestroyView() { // added to avoid memoryleks
        super.onDestroyView()
        _binding = null
    }


}