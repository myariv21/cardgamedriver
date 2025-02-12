package edu.guilford;

import java.util.Random;

public class Card implements Comparable<Card> {
    // enum for the suits
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // enum for the ranks
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    // instance variables
    private Suit suit;
    private Rank rank;

    // constructor with specified suit and rank
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // default constructor with random suit and rank
    public Card() {
        Random rand = new Random();
        // Corrected random suit and rank assignment
        // The original code worked, but the random selection process is more concise now
        // by directly assigning values from the enums using nextInt() for both suit and rank.
        this.suit = Suit.values()[rand.nextInt(Suit.values().length)];
        this.rank = Rank.values()[rand.nextInt(Rank.values().length)];
    }

    // getters
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    // toString method
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // compareTo method to compare cards by rank, then suit
    @Override
    public int compareTo(Card otherCard) {
        // Corrected: Simplified rank comparison using Integer.compare
        // Using Integer.compare for rank comparison instead of manual conditional checks makes it cleaner and more efficient.
        // The ordinal() method returns the index of the enum value, which allows a numerical comparison.
        int rankComparison = Integer.compare(this.rank.ordinal(), otherCard.rank.ordinal());
        
        if (rankComparison != 0) {
            return rankComparison; // if ranks are different, return result
        }
        
        // If ranks are equal, compare by suit
        // Used Integer.compare to streamline the suit comparison as well
        return Integer.compare(this.suit.ordinal(), otherCard.suit.ordinal());
    }
}
