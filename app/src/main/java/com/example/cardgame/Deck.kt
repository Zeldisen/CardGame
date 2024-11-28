package com.example.cardgame

class Deck {

    val cards: List<Card> = listOf(
        Card(1,R.drawable.heartsess),
        Card(2,R.drawable.hearts2),
        Card(3,R.drawable.hearts3),
        Card(4,R.drawable.hearts4),
        Card(5,R.drawable.hearts5),
        Card(6,R.drawable.hearts6),
        Card(7,R.drawable.hearts7),
        Card(8,R.drawable.hearts8),
        Card(9,R.drawable.hearts9),
        Card(10,R.drawable.hearts10),
        Card(11,R.drawable.heartsjack),
        Card(12,R.drawable.heartsqueen),
        Card(13,R.drawable.heartsking),
        Card(1,R.drawable.diamonsd1),
        Card(2,R.drawable.diamonds2),
        Card(3,R.drawable.diamonds3),
        Card(4,R.drawable.diamonds4),
        Card(5,R.drawable.diamonds5),
        Card(6,R.drawable.diamonds6),
        Card(7,R.drawable.diamonds7),
        Card(8,R.drawable.diamonds8),
        Card(9,R.drawable.diamonds9),
        Card(10,R.drawable.diamonds10),
        Card(11,R.drawable.diamondsjack),
        Card(12,R.drawable.diamondsqueen),
        Card(13,R.drawable.diamondsking),
        Card(1,R.drawable.clover1),
        Card(2,R.drawable.clover2),
        Card(3,R.drawable.clover3),
        Card(4,R.drawable.clover4),
        Card(5,R.drawable.clover5),
        Card(6,R.drawable.clover6),
        Card(7,R.drawable.clover7),
        Card(8,R.drawable.clover8),
        Card(9,R.drawable.clover9),
        Card(10,R.drawable.clover10),
        Card(11,R.drawable.cloverjack),
        Card(12,R.drawable.cloverqueen),
        Card(13,R.drawable.cloverking),
        Card(1,R.drawable.spades1),
        Card(2,R.drawable.spades2),
        Card(3,R.drawable.spades3),
        Card(4,R.drawable.spades4),
        Card(5,R.drawable.spades5),
        Card(6,R.drawable.spades6),
        Card(7,R.drawable.spades7),
        Card(8,R.drawable.spades8),
        Card(9,R.drawable.spades9),
        Card(10,R.drawable.spades10),
        Card(11,R.drawable.spadesjack),
        Card(12,R.drawable.spadesqueen),
        Card(13,R.drawable.spadesking),

        )
    fun showRandomCard():Card{
        return cards.random()

    }
}