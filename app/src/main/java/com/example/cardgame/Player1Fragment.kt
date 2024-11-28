package com.example.cardgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.BundleCompat.getParcelable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Player1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Player1Fragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var deck: Deck
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player1,container,false)
        val playerGuess = view.findViewById<EditText>(R.id.yourGuess_et)
        val guessBtn = view.findViewById<Button>(R.id.guessButton)
        val showCard = view.findViewById<ImageView>(R.id.show_card)
        val pointView = view.findViewById<TextView>(R.id.tv_showPoints)
        val hihger = 0
        val points = 0
        deck = (activity as GameActivity).deck
        @Suppress("DEPRECATION")
        val card:Card? = arguments?.getParcelable("selectedCard")
        guessBtn.setOnClickListener {
            //showCard.setImageResource(card?.value? ,card.imageResId?)
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Player1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Player1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}