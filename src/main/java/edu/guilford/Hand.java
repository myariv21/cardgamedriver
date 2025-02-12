package edu.guilford;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    // Constructor initializes an empty hand
    public Hand() {
        hand = new ArrayList<Card>(); // Initializes the ArrayList to store the cards in the hand
    }

    // Adds a card to the hand
    public void addCard(Card card) {
        hand.add(card); // Adds a card to the end of the hand
    }

    // Removes a card from the hand
    public void removeCard(Card card) {
        hand.remove(card); // Removes the specified card from the hand
    }

    // Clears the hand, resetting it to empty
    public void reset() {
        hand.clear(); // Clears all cards from the hand
    }

    // Returns the number of cards in the hand
    public int size() {
        return hand.size(); // Returns the size of the ArrayList (number of cards in the hand)
    }

    // Returns the card at the specified index
    public Card getCard(int index) {
        return hand.get(index); // Returns the card at the specified index in the hand
    }

    // Calculates the total value of the hand
    public int getTotalValue() {
        int value = 0; // Variable to hold the cumulative value of the hand
        int aces = 0; // Variable to count the number of aces in the hand
        // Loop through each card in the hand to calculate its value
        for (Card card : hand) {
            switch (card.getRank()) {
                case TWO:
                    value += 2;
                    break;
                case THREE:
                    value += 3;
                    break;
                case FOUR:
                    value += 4;
                    break;
                case FIVE:
                    value += 5;
                    break;
                case SIX:
                    value += 6;
                    break;
                case SEVEN:
                    value += 7;
                    break;
                case EIGHT:
                    value += 8;
                    break;
                case NINE:
                    value += 9;
                    break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    value += 10; // Face cards (J, Q, K) and 10 all have a value of 10
                    break;
                case ACE:
                    aces++; // Count the number of Aces
                    break;
            }
        }
        // Adjust the value of the aces based on the total value (Ace can be worth 1 or 11)
        for (int i = 0; i < aces; i++) {
            if (value + 11 <= 21) {
                value += 11; // Use Ace as 11 if the total value does not exceed 21
            } else {
                value += 1; // Otherwise, use Ace as 1
            }
        }
        return value; // Return the total calculated value of the hand
    }

    // Override toString method to return a string representation of the hand
    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder(); // StringBuilder is used for efficient string concatenation
        for (Card card : hand) {
            handString.append(card.toString()).append("\n"); // Append each card's string representation to the result
        }
        return handString.toString(); // Return the string of all card descriptions in the hand
    }

    // Getter method to return the list of cards in the hand
    public ArrayList<Card> getHand() {
        return hand; // Returns the ArrayList holding the cards in the hand
    }
}
