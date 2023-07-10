package com.example.cardgame
import Card
import Game
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

fun main() {
    val game = Game()
    game.start()

            while (game.player1Cards.isNotEmpty() && game.player2Cards.isNotEmpty()) {
                game.playRound()
                println("Player ${game.currentPlayer} wins the round.")
            }

    val winner = game.getWinner()
    if (winner == 0) {
        println("It's a tie!")
    } else {
        println("Player $winner wins the game!")
    }

    println("Player 1 won ${game.getPlayer1CardsWon()} cards.")
    println("Player 2 won ${game.getPlayer2CardsWon()} cards.")
}

