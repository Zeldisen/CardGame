package com.example.cardgame

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    private val deckInstance = Deck()  // List of Card from Deck class
    // to make diffrens between player1 cards and player1 cards
    private val _selectedCard1 = MutableLiveData<Card?>()  // List of selected Card from a deckList also from deck class
    private val _selectedCard2 = MutableLiveData<Card?>()  // List of selected Card from a deckList also from deck class

    val isDeckEmpty: LiveData<Boolean> get() = deckInstance.isDeckEmpty  // had to have this so I could check when deck is empty
    val selectedCard1: LiveData<Card?> get() = _selectedCard1
    val selectedCard2: LiveData<Card?> get() = _selectedCard2

    /**
     * draws a card of deck for player1 and singleplayer
     */
    fun drawRandomCardPlayer1(){
        _selectedCard1.value = deckInstance.drawRandomCard()
        isDeckEmpty()
    }

    /**
     * draws a card for player2
     */
    fun drawRandomCardPlayer2(){
        _selectedCard2.value = deckInstance.drawRandomCard()
        isDeckEmpty()
    }

    /**
     * reset the deck when user wants to play again
     */
    fun resetDeck(){
       deckInstance.resetDeck()
    }

    /**
     * checks if deck is empty and uses to chewinner in player2Fragment
     */
    fun isDeckEmpty():Boolean{
        val isEmpty = deckInstance.checkDeckIsEmpty()
        Log.d("SharedViewModel", "isDeckEmpty(): $isEmpty")
        return deckInstance.checkDeckIsEmpty()
    }

}