import kotlin.collections.shuffle

import kotlin.random.Random


class Game {
    private val deck = mutableListOf<Card>()
    val player1Cards = mutableListOf<Card>()
    val player2Cards = mutableListOf<Card>()
    private val player1Discard = mutableListOf<Card>()
    private val player2Discard = mutableListOf<Card>()

    private val suitOrder = mutableListOf<Suit>()
    var currentPlayer = 1

    fun start() {
        // Generate the deck of cards
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                deck.add(Card(suit, rank))
            }
        }

        // Shuffle the deck
        deck.shuffle()

        // Split the deck into two piles of equal size
        player1Cards.addAll(deck.subList(0, deck.size / 2))
        player2Cards.addAll(deck.subList(deck.size / 2, deck.size))

        // Set the suit order at random
        suitOrder.addAll(Suit.values())
        suitOrder.shuffle()
    }

    fun playRound() {
        // Both players draw the top card of their pile
        val player1Card = player1Cards.removeAt(0)
        val player2Card = player2Cards.removeAt(0)

        // Compare the cards to determine the winner
        val result = compareCards(player1Card, player2Card)
        when (result) {
            1 -> {
                // Player 1 wins
                player1Discard.add(player1Card)
                player1Discard.add(player2Card)
                currentPlayer = 1
            }
            -1 -> {
                // Player 2 wins
                player2Discard.add(player1Card)
                player2Discard.add(player2Card)
                currentPlayer = 2
            }
            else -> {
                // It's a tie, so we need to draw another round
                player1Discard.add(player1Card)
                player2Discard.add(player2Card)
                playRound()
            }
        }
    }

    fun compareCards(card1: Card, card2: Card): Int {
        // Compare the rank first
        val rankCompare = card1.rank.compareTo(card2.rank)
        if (rankCompare != 0) {
            return rankCompare
        }

        // If the ranks are equal, compare the suits
        return suitOrder.indexOf(card1.suit).compareTo(suitOrder.indexOf(card2.suit))
    }
    fun getWinner(): Int {
        // Determine the winner based on the number of cards in the discard piles
        return when {
            player1Discard.size > player2Discard.size -> 1
            player2Discard.size > player1Discard.size -> 2
            else -> 0
        }
    }

    fun getPlayer1CardsWon(): Int {
        return player1Discard.size
    }

    fun getPlayer2CardsWon(): Int {
        return player2Discard.size
    }
}

