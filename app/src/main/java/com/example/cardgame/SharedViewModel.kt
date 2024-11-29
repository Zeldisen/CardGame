package com.example.cardgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
    private val deckInstance = Deck()
    private val _selectedCard = MutableLiveData<Card?>()
    val selectedCard: LiveData<Card?> get() = _selectedCard

    fun drawRandomCard(){
        _selectedCard.value = deckInstance.drawRandomCard()
    }
}