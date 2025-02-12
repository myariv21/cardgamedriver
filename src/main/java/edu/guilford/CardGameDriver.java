package edu.guilford;

public class CardGameDriver {
    public static void main(String[] args) {
        final int NGAMES = 10000; // Number of games to simulate
        int dealerWins = 0; // Counter for dealer wins
        int playerWins = 0; // Counter for player wins
        Blackjack game = new Blackjack(); // Creates a new Blackjack game instance
        game.deal(); // Deals initial cards for the first round

        int iGame = 0;
        while (iGame < NGAMES) {
            game.deal(); // Deals new cards for each game
            // Checks if the player or dealer has a blackjack (21 points)
            if (game.getPlayerHand().getTotalValue() == 21) {
                playerWins++; // Player wins if they get 21
            } else if (game.getDealerHand().getTotalValue() == 21) {
                dealerWins++; // Dealer wins if they get 21
            } else {
                // Conducts the player and dealer turns
                boolean playerResult = game.playerTurn();
                boolean dealerResult = game.dealerTurn();
                if (!playerResult) {
                    dealerWins++; // Dealer wins if the player does not complete their turn
                } else if (!dealerResult) {
                    playerWins++; // Player wins if the dealer does not complete their turn
                } else if (game.getPlayerHand().getTotalValue() < game.getDealerHand().getTotalValue()) {
                    dealerWins++; // Dealer wins if player's total is less than dealer's
                } else if (game.getPlayerHand().getTotalValue() > game.getDealerHand().getTotalValue()) {
                    playerWins++; // Player wins if player's total is greater than dealer's
                }
            }

            // Resets the game if the deck has fewer than 10 cards
            if (game.getDeck().size() < 10) {
                game.reset(true); // Resets and shuffles the deck
            }

            iGame++; // Increments the game counter
        }

        // Prints the results of the simulation
        System.out.println("Dealer wins: " + dealerWins);
        System.out.println("Player wins: " + playerWins);
        System.out.println("Pushes: " + (NGAMES - dealerWins - playerWins)); // Pushes are ties (no wins for both)

        // Starts a new LamarckianPoker game and deals initial cards
        LamarckianPoker lmpGame = new LamarckianPoker();
        lmpGame.deal();
        System.out.println("\nInitial Lamarckian hands\n" + lmpGame);

        boolean gameDone = false;
        while (!gameDone) {
            // The game continues until no more turns can be made
            gameDone = !lmpGame.turn(); // Ends the game if no more turns are available
        }

        // Prints the final state of the Lamarckian poker game
        System.out.println("Final Lamarckian hands\n" + lmpGame);
    }
}
