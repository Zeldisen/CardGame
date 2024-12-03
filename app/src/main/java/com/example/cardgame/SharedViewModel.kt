package com.example.cardgame

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    private val deckInstance = Deck()
    private val _selectedCard1 = MutableLiveData<Card?>()
    private val _selectedCard2 = MutableLiveData<Card?>()
    val isDeckEmpty: LiveData<Boolean> get() = deckInstance.isDeckEmpty
    val selectedCard1: LiveData<Card?> get() = _selectedCard1
    val selectedCard2: LiveData<Card?> get() = _selectedCard2

    fun drawRandomCardPlayer1(){
        _selectedCard1.value = deckInstance.drawRandomCard()
        isDeckEmpty()
    }
    fun drawRandomCardPlayer2(){
        _selectedCard2.value = deckInstance.drawRandomCard()
        isDeckEmpty()
    }
    fun resetDeck(){
       deckInstance.resetDeck()
    }
    fun isDeckEmpty():Boolean{
        val isEmpty = deckInstance.checkDeckIsEmpty()
        Log.d("SharedViewModel", "isDeckEmpty(): $isEmpty")
        return deckInstance.checkDeckIsEmpty()


    }
}