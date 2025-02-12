package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random rand = new Random();

    // Constructor that builds the deck when instantiated
    public Deck() {
        build(); // Calls build() to initialize the deck with 52 cards
    }

    // Returns the current deck
    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    // Clears the deck (empties the list)
    public void clear() {
        deck.clear(); // Utilizes the built-in ArrayList method to remove all elements
    }

    // Builds a standard 52-card deck
    public void build() {
        // Nested for-each loop to iterate over all suits and ranks and create a full deck
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank)); // Adds a new Card object to the deck
            }
        }
    }

    // Shuffles the deck by randomly selecting cards and reordering them
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>(); // Temporary list to hold shuffled cards
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size()); // Randomly selects a location in the deck
            tempDeck.add(deck.get(loc)); // Adds the card from that location to the temporary list
            deck.remove(loc); // Removes the selected card from the original deck
        }
        deck = tempDeck; // Replaces the original deck with the shuffled one
    }

    // Picks and removes a card at the specified index
    public Card pick(int i) {
        // Card picked is removed from the deck and returned
        return deck.remove(i); // Simplified to return the result of remove() directly
    }

    // Deals the top card (first card) from the deck
    public Card deal() {
        return deck.remove(0); // Removes the first card in the deck (index 0)
    }

    // Returns the size (number of cards) in the deck
    public int size() {
        return deck.size(); // Utilizes the ArrayList size() method to get the current deck size
    }

    // Returns a formatted string representation of the deck
    @Override
    public String toString() {
        StringBuilder deckString = new StringBuilder(); // Using StringBuilder for efficient string concatenation
        for (Card card : deck) {
            deckString.append(card.toString()).append("\n"); // Appends each card's string representation followed by a newline
        }
        return deckString.toString(); // Returns the complete string of deck cards
    }
}
