package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class LamarckianPoker {
    private Hand player1Hand;
    private Hand player2Hand;
    private Hand pool;
    private Deck discard;
    private Deck deck;
    private Random rand = new Random();
    private int iTurn;

    // Constructor initializes the game with a reset
    public LamarckianPoker() {
        reset(true); // Calls reset to initialize a new deck and shuffle it
    }

    // Getter for player 1's hand
    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    // Getter for player 2's hand
    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    // Getter for the pool of cards
    public Hand getPool() {
        return pool;
    }

    // Resets the game with an option to create a new deck or continue with the current one
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck(); // Initializes a new deck
            discard = new Deck(); // Initializes a new discard deck
            discard.clear(); // Clears the discard deck
            deck.shuffle(); // Shuffles the deck
        }
        iTurn = 0; // Reset the turn counter
    }

    // Deals 4 cards to both player 1 and player 2
    public void deal() {
        player1Hand = new Hand(); // Creates a new hand for player 1
        player2Hand = new Hand(); // Creates a new hand for player 2
        for (int iCard = 0; iCard < 4; iCard++) {
            player1Hand.addCard(deck.deal()); // Deals a card to player 1
            player2Hand.addCard(deck.deal()); // Deals a card to player 2
        }
    }

    // Creates a pool of 4 cards from the deck
    public void makePool() {
        pool = new Hand(); // Creates a new hand for the pool
        for (int iCard = 0; iCard < 4; iCard++) {
            pool.addCard(deck.deal()); // Deals a card to the pool
        }
    }

    // Executes a turn of the game, compares cards, and distributes cards accordingly
    public boolean turn() {
        if (player1Hand.size() < 7 || player2Hand.size() < 7) {
            makePool(); // Create a pool if one does not exist

            // Selects random cards from player 1's and player 2's hands
            Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
            Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));

            Hand firstHand, secondHand;
            Card firstCard, secondCard;

            // Determines which card has a higher rank, and assigns first and second hands accordingly
            if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            } else {
                // In case of a tie on rank, compare the suit
                if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
                    firstHand = player1Hand;
                    secondHand = player2Hand;
                    firstCard = player1Card;
                    secondCard = player2Card;
                } else {
                    firstHand = player2Hand;
                    secondHand = player1Hand;
                    firstCard = player2Card;
                    secondCard = player1Card;
                }
            }

            ArrayList<Card> poolRemove = new ArrayList<Card>(); // List to keep track of cards removed from pool

            // Adds cards from pool to the first hand based on matching rank or suit
            for (Card poolCard : pool.getHand()) {
                if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    firstHand.addCard(poolCard); // Adds the card to the first hand
                    poolRemove.add(poolCard); // Marks the card for removal from pool
                }
            }

            // Removes the cards from the pool
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            poolRemove.clear(); // Clears the temporary list

            // Adds the first selected card to the pool
            pool.addCard(firstCard);
            firstHand.removeCard(firstCard); // Removes the first selected card from the first hand

            // Repeats the process for the second player
            for (Card poolCard : pool.getHand()) {
                if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    secondHand.addCard(poolCard); // Adds the card to the second hand
                    poolRemove.add(poolCard); // Marks the card for removal from pool
                }
            }

            // Removes the cards from the pool
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }

            // Adds the second selected card to the pool
            pool.addCard(secondCard);
            secondHand.removeCard(secondCard); // Removes the second selected card from the second hand

            // Transfers all pool cards to the discard deck
            for (Card poolCard : pool.getHand()) {
                discard.getDeck().add(poolCard);
            }
            pool.getHand().clear(); // Clears the pool

            // If the deck has fewer than 4 cards, reshuffle the discard deck into the main deck
            if (deck.size() < 4) {
                for (Card card : discard.getDeck()) {
                    deck.getDeck().add(card); // Adds cards from discard to deck
                }
                discard.clear(); // Clears the discard deck
            }

            iTurn++; // Increments the turn counter
            return true; // Indicates that the turn was successful
        } else {
            return false; // Game ends if any player has less than 7 cards
        }
    }

    // Returns a string representation of the game state (player hands, pool)
    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }
}
