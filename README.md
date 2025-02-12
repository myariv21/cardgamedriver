# Two card games: Blackjack and Lamarckian Poker

## Utility classes

Both projects require the following classes.

### `Card`

The `Card` class implements the `Comparable<Card>` interface

#### Attributes

* A suit to be chosen from `CLUBS, DIAMONDS, HEARTS, SPADES`
* A rank to be chosen from `ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING` 
* The suit and rank should be declared using `enum` structures named `Suit` and `Rank`

#### Constructors

* `public Card()` should produce a randomly chosen `Card` object
* `public Card(Suit suit, Rank rank)` should produce a `Card` object of the specified suit and rank

#### Methods

* `public void getSuit()` returns the suit of the `Card` object
* `pubilc void getRank()` returns the rank of the `Card` object
* `public String toString()` returns a well-formatted `String` repesentation of this `Card` object
* `public int compareTo(Card otherCard)` compares this `Card` object to `otherCard`. Rank is compared first and then suit.

### `Deck`

A `Deck` object holds a sequence of `Card` objects that can be used in card games

#### Attributes

* `deck` is a list of `Card` objects
* A `Random` object 

#### Constructors

* `public Deck()` constructs a standard 52-card deck using the `build()` method

#### Methods

* `public ArrayList<Card> getDeck()` returns `deck` 
* `public void clear()` empties `deck`
* `public void build()` adds all 52 `Card` objects in a standard deck to `deck` in a specified order
* `public void shuffle()` shuffles the order of objects in `deck` using some standard algorithm that ensures a randomly arranged deck. It should operate on any `deck` that contains more than 0 `Card` objects
* `public Card pick (int i)` returns the `Card` object in `deck` at index `i` and removes that `Card` object from `deck`
* `public Card deal()` returns the `Card` object at index 0 (assumed to the the "top" of the deck) and removes that `Card` object from `deck`
* `public int size()` returns the number of `Card` objects in `deck` 
* `public String toString()` returns a well-formatted `String` representation of this `Deck` object

### `Hand`

A `Hand` object holds a sequence of `Card` objects that represent the cards a single player has in a card game

#### Attributes

* `hand` is a list of `Card` objects

#### Constructors

* `public Hand()` assigns an empty list to `hand`

#### Methods

* `public ArrayList<Card> getHand()` returns `hand`
* `public void addCard(Card card)` adds `card` to `hand`
* `public void removeCard(Card card)` removes `card` from `hand`
* `public void reset()` empties `hand` 
* `public Card getCard(int index)` returns the `Card` object at the specified `index`
* `public int getTotalValue()` calculates a total for `hand` according to the rules of Blackjack. Number cards add their face value. Face cards add 10. Aces should count as 1 or 11, whichever provides the higher score without exceeding 21.
* `public String toString()` returns a well-formatted `String` representation of this `Hand` object

## Blackjack

We will implement a simple version of player and dealer actions in the Blackjack game.

Starting the game is when the player and dealer are each dealt two random cards. 

A player turn is when the player draws cards until the hand total is greater than or equal to 16. The player busts if the total is over 21.

A dealer turn is when the dealer draws cards until the hand total is greater than or equal to 17. The dealer busts if the total is over 21. 

### `Blackjack`

The `Blackjack` class implements the actions described above.

#### Attributes

* Two `Hand` objects for the player and dealer hands
* A `Deck` object for the deck to be used in the game

#### Constructor

* `public Blackjack()` uses the `reset()` method to prepare the `Deck` object for a new game


#### Methods
* `public Hand getPlayerHand()` returns the `Hand` object representing the player's hand
* `public Hand getDealerHand()` returns the `Hand` object representing the dealer's hand
* `public Deck getDeck()` retuns the current state of the object's `Deck`
* `public void reset()` instantiates a new `Deck` object and applies its `shuffle()` method
* `public void deal()` instantiates two `Hand` objects, assigning them to the appropriate player and dealer attributes. It then adds two `Card` objects from the `Deck` object to each `Hand` object
* `public boolean playerTurn()` applies the rules described above to the player's hand. It returns `true` if the value of the player's hand is less than or equal to 21 and false otherwise
* `public boolean dealerTurn()` applies the rules described above to the dealer's hand. It returns `true` if the value of the dealer's hand is less than or equal to 21 and false otherwise
* * `public String toString()` returns a well-formatted `String` representation of this `Blackjack` object

## Lamarckian Poker

Lamarckian poker is a two-player game where players attempt to construct the best poker hand by giving up cards in their hand to select what they perceive to be better cards from a pool towards the goal of building the best poker hand. 

The version implemented here is based on rules that are best described [here](https://boardgamegeek.com/blog/743/blogpost/17479/lamarckian-poker-a-surprising-diamond-in-the-rough), but with modifications that lend themselves well to random choices when taking turns.

In essence, each player is dealt four cards to begin with, and four cards are placed face up in a pool. 

At the beginning of each turn, each player chooses a card from their hand that they wish to sacrifice in order to obtain more desirable cards from the pool. The players show their card and the following actions take place:

* The player with the higher sacrificial card selects all cards with the same suit or rank from the pool and adds them to their hand. The sacrificial card is then placed in the discard pile.
* The second player does the same with their sacrificial card.
* Any unselected cards in the pool go into the discard pile.
* A new four-card pool is dealt from the deck.

If the deck does not have enough cards to create a four-card pool, the cards in the deck and discard pile are combined and shuffled, and game play continues.

The game continues until each player has at least 7 cards in their hand. At that point, each hand is evaluated for the best five-card poker hand that can be created, and that determines the winner of the game.

### `LamarckianPoker`

The `LamarckianPoker` class implements the start of a new game and the playing of a single turn.

#### Attributes

* Two `Hand` objects for the player and dealer hands
* A `Deck` object for the deck to be used in the game
* A `Hand` object for the pool
* A `Deck` object for the discard pile
* A `Random` object 

#### Constructors

* `public LamarckianPoker()` creates a new game using the `reset()` method

#### Methods

* `public Hand getPlayer1Hand()` returns the hand of the first player
* `public Hand getPlayer1Hand()` returns the hand of the second player
* `public Hand pool()` returns the hand representing the pool
* `public void reset(boolean newDeck)` creates a game with a new shuffled deck and discard pile
* `public void deal()` instantiates new `Hand` objects for each player and deals 4 `Card` objects from the deck to each player
* `public void makePool()` creates a `Hand` object and deals 4 `Card` objects from the deck to this object
* `public boolean turn()` implements the turn rules described above with the `Card` object from each player chosen randomly. The method returns `true` if the size of both player hand is less than 7 and `false` otherwise
* `public String toString()` returns a well-formatted `String` representation of this `LamarckianPoker` object

## Driver program

The driver program is contained in the `CardGameDriver` program. This program performs tests on both the `Blackjack` and `LamarckianPoker` classes. In doing so, it implicitly tests all other classes.

### Blackjack testing

This testing approach and the `Blackjack` and utility classes are based on [an approach](https://cs.colby.edu/courses/F17/cs231-labs/proj01.php) described by Caitrin Eaton for a Data Structures & Algorithms project at Colby College 

The driver program runs 10000 games of blackjack by instantiating a `Blackjack` object and then using its methods to deal cards, execute player and dealer turns, and evaluate the result. It records for each game whether the dealer wins, the player wins, or there is a tie (which is called a *push*). During the testing, when the deck has fewer than 10 cards, a new deck is used in the game. The number of dealer wins, player wins, and pushes is displayed.

#### Lamarckian Poker testing

The driver program runs one simulated Lamarckian Poker game. It does so by instantiating a `LamarckianPoker` object and dealing hands to the two players. It then has the object execute game turns until the `turn()` method indicates that the game is complete and the hands are ready to be evaluated. The evaluate of the hands and determination of a winner is not yet implemented.


MY VERSION


## Two Card Games: Blackjack and Lamarckian Poker

### Utility Classes

Both card games will require the following classes:

### `Card`

The `Card` class implements the `Comparable<Card>` interface to enable comparison between cards, mainly for sorting purposes.

#### Attributes:
- **Suit**: Chosen from the `Suit` enum (`CLUBS`, `DIAMONDS`, `HEARTS`, `SPADES`).
- **Rank**: Chosen from the `Rank` enum (`ACE`, `TWO`, `THREE`, `FOUR`, `FIVE`, `SIX`, `SEVEN`, `EIGHT`, `NINE`, `TEN`, `JACK`, `QUEEN`, `KING`).

#### Constructors:
- `public Card()` generates a randomly chosen card.
- `public Card(Suit suit, Rank rank)` creates a card with the specified suit and rank.

#### Methods:
- `public Suit getSuit()` returns the suit of the card.
- `public Rank getRank()` returns the rank of the card.
- `public String toString()` returns a well-formatted string representation of the card.
- `public int compareTo(Card otherCard)` compares this card to another based on rank first and suit second.

### `Deck`

The `Deck` class represents a deck of cards used in card games.

#### Attributes:
- **deck**: A list of `Card` objects.
- **Random**: A random number generator used for shuffling.

#### Constructors:
- `public Deck()` initializes a standard 52-card deck using the `build()` method.

#### Methods:
- `public ArrayList<Card> getDeck()` returns the deck.
- `public void clear()` empties the deck.
- `public void build()` populates the deck with 52 cards in a standard order.
- `public void shuffle()` shuffles the deck using a standard shuffling algorithm.
- `public Card pick(int i)` returns the card at index `i` and removes it from the deck.
- `public Card deal()` returns the card at index 0 (top of the deck) and removes it.
- `public int size()` returns the number of cards remaining in the deck.
- `public String toString()` returns a well-formatted string representation of the deck.

### `Hand`

The `Hand` class represents a collection of cards held by a player.

#### Attributes:
- **hand**: A list of `Card` objects.

#### Constructors:
- `public Hand()` initializes an empty list for the hand.

#### Methods:
- `public ArrayList<Card> getHand()` returns the hand.
- `public void addCard(Card card)` adds a card to the hand.
- `public void removeCard(Card card)` removes a card from the hand.
- `public void reset()` clears the hand.
- `public Card getCard(int index)` returns the card at the specified index.
- `public int getTotalValue()` calculates the total value of the hand based on Blackjack rules (number cards by face value, face cards by 10, and Aces as either 1 or 11).
- `public String toString()` returns a well-formatted string representation of the hand.

---

### Blackjack Game

Blackjack is a simple two-player game where the player competes against a dealer.

#### Rules:
- Each player (including the dealer) is dealt two cards.
- The player draws cards until the total hand value is at least 16, or they bust if the total exceeds 21.
- The dealer draws cards until their total hand value is at least 17, or they bust if the total exceeds 21.

#### `Blackjack` Class

The `Blackjack` class implements the core game mechanics.

#### Attributes:
- **playerHand**: A `Hand` object representing the player's hand.
- **dealerHand**: A `Hand` object representing the dealer's hand.
- **deck**: A `Deck` object representing the deck used in the game.

#### Constructor:
- `public Blackjack()` initializes the game and prepares the deck using the `reset()` method.

#### Methods:
- `public Hand getPlayerHand()` returns the player's hand.
- `public Hand getDealerHand()` returns the dealer's hand.
- `public Deck getDeck()` returns the current deck.
- `public void reset()` initializes a new shuffled deck.
- `public void deal()` deals two cards to both the player and the dealer.
- `public boolean playerTurn()` executes the player's turn and returns `true` if the player hasn't busted.
- `public boolean dealerTurn()` executes the dealer's turn and returns `true` if the dealer hasn't busted.
- `public String toString()` returns a string representation of the game.

---

### Lamarckian Poker Game

Lamarckian Poker is a unique card game where players attempt to improve their poker hands by sacrificing cards from their hand to draw better ones from a pool.

#### Rules:
- Each player starts with four cards, and there is a pool of four cards available to both players.
- Each player sacrifices a card from their hand, and the player who sacrifices the higher card takes all matching cards (by rank or suit) from the pool and adds them to their hand.
- The process continues until both players have at least 7 cards in their hands.
- At the end of the game, players evaluate their hands, and the best five-card hand wins.

#### `LamarckianPoker` Class

The `LamarckianPoker` class implements the core game mechanics.

#### Attributes:
- **player1Hand**: A `Hand` object representing the first player's hand.
- **player2Hand**: A `Hand` object representing the second player's hand.
- **pool**: A `Hand` object representing the pool of cards.
- **deck**: A `Deck` object representing the deck used in the game.
- **discardPile**: A `Deck` object representing the discard pile.
- **random**: A `Random` object for random selections during the turn.

#### Constructor:
- `public LamarckianPoker()` initializes a new game using the `reset()` method.

#### Methods:
- `public Hand getPlayer1Hand()` returns the first player's hand.
- `public Hand getPlayer2Hand()` returns the second player's hand.
- `public Hand getPool()` returns the pool of cards.
- `public void reset(boolean newDeck)` sets up a new game with a shuffled deck and discard pile.
- `public void deal()` deals 4 cards to each player.
- `public void makePool()` generates a new pool of 4 cards from the deck.
- `public boolean turn()` implements the rules of the game for a single turn, returning `true` if both players have less than 7 cards in hand, otherwise `false`.
- `public String toString()` returns a string representation of the game.

---

### Driver Program

The `CardGameDriver` class is used to test both the `Blackjack` and `LamarckianPoker` games.

#### Blackjack Testing:
- Simulates 10,000 games of Blackjack.
- For each game, the driver will deal cards, execute the player and dealer turns, and determine the outcome (dealer win, player win, or tie).
- Results are displayed, including the number of dealer wins, player wins, and pushes (ties).

#### Lamarckian Poker Testing:
- Simulates one game of Lamarckian Poker.
- Deals hands to both players, executes turns, and evaluates whether both players have at least 7 cards.
- The evaluation of hands and determination of the winner is not yet implemented.

---

### Final Notes:
This design is focused on implementing and simulating card games with clean code that follows object-oriented principles. It is structured to test individual components like `Card`, `Deck`, and `Hand`, ensuring that the main game logic in `Blackjack` and `LamarckianPoker` works smoothly. Further improvements can include more complex rules, better hand evaluation for poker, and advanced game strategies.