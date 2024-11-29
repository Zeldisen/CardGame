package com.example.cardgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Deck {

   private val _cards: MutableLiveData<MutableList<Card>> = MutableLiveData(mutableListOf())
    val cards: LiveData<MutableList<Card>> get() = _cards
    val drawnCards = mutableListOf<Card>()
    init{
        createDeck()
    }
    fun createDeck(){
        _cards.value?.add(Card(1,R.drawable.heartsess))
        _cards.value?.add(Card(2,R.drawable.hearts2))
        _cards.value?.add(Card(3,R.drawable.hearts3))
        _cards.value?.add(Card(4,R.drawable.hearts4))
        _cards.value?.add(Card(5,R.drawable.hearts5))
        _cards.value?.add(Card(6,R.drawable.hearts6))
        _cards.value?.add(Card(7,R.drawable.hearts7))
        _cards.value?.add(Card(8,R.drawable.hearts8))
        _cards.value?.add(Card(9,R.drawable.hearts9))
        _cards.value?.add(Card(10,R.drawable.hearts10))
        _cards.value?.add(Card(11,R.drawable.heartsjack))
        _cards.value?.add(Card(12,R.drawable.heartsqueen))
        _cards.value?.add(Card(13,R.drawable.heartsking))
        _cards.value?.add(Card(1,R.drawable.diamonsd1))
        _cards.value?.add(Card(2,R.drawable.diamonds2))
        _cards.value?.add(Card(3,R.drawable.diamonds3))
        _cards.value?.add(Card(4,R.drawable.diamonds4))
        _cards.value?.add(Card(5,R.drawable.diamonds5))
        _cards.value?.add(Card(6,R.drawable.diamonds6))
        _cards.value?.add(Card(7,R.drawable.diamonds7))
        _cards.value?.add(Card(8,R.drawable.diamonds8))
        _cards.value?.add(Card(9,R.drawable.diamonds9))
        _cards.value?.add(Card(10,R.drawable.diamonds10))
        _cards.value?.add(Card(11,R.drawable.diamondsjack))
        _cards.value?.add(Card(12,R.drawable.diamondsqueen))
        _cards.value?.add(Card(13,R.drawable.diamondsking))
        _cards.value?.add(Card(1,R.drawable.clover1))
        _cards.value?.add(Card(2,R.drawable.clover2))
        _cards.value?.add(Card(3,R.drawable.clover3))
        _cards.value?.add(Card(4,R.drawable.clover4))
        _cards.value?.add(Card(5,R.drawable.clover5))
        _cards.value?.add(Card(6,R.drawable.clover6))
        _cards.value?.add(Card(7,R.drawable.clover7))
        _cards.value?.add(Card(8,R.drawable.clover8))
        _cards.value?.add(Card(9,R.drawable.clover9))
        _cards.value?.add(Card(10,R.drawable.clover10))
        _cards.value?.add(Card(11,R.drawable.cloverjack))
        _cards.value?.add(Card(12,R.drawable.cloverqueen))
        _cards.value?.add(Card(13,R.drawable.cloverking))
                _cards.value?.add(Card(1,R.drawable.spades1))
        _cards.value?.add(Card(2,R.drawable.spades2))
        _cards.value?.add(Card(3,R.drawable.spades3))
        _cards.value?.add(Card(4,R.drawable.spades4))
        _cards.value?.add(Card(5,R.drawable.spades5))
        _cards.value?.add(Card(6,R.drawable.spades6))
        _cards.value?.add(Card(7,R.drawable.spades7))
        _cards.value?.add(Card(8,R.drawable.spades8))
        _cards.value?.add(Card(9,R.drawable.spades9))
        _cards.value?.add(Card(10,R.drawable.spades10))
        _cards.value?.add(Card(11,R.drawable.spadesjack))
        _cards.value?.add(Card(12,R.drawable.spadesqueen))
        _cards.value?.add(Card(13,R.drawable.spadesking))
    }


    fun drawRandomCard(): Card? {
        val avalibleCards = _cards.value?.filter {it !in drawnCards}
        val card = avalibleCards?.randomOrNull()
        if( card != null){
            drawnCards.add(card)
        }
        return card
    }
}