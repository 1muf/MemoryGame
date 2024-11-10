import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Card> cards;
    private boolean[] matchedCards; // Array to track if a card has been matched
    private int score; // Score variable to track the total score
    private int moves; // Variable to track the number of moves made

    public Game() {
        initializeGame(); // Call the initialization method when the game is created
    }

    private void initializeGame() {
        cards = new ArrayList<>();
        matchedCards = new boolean[6]; // Reset the matched cards array
        score = 0;
        moves = 0; // Initialize moves

        // Add cards with the same type for matching pairs
        cards.add(new SpellCard("Spell 1"));
        cards.add(new SpellCard("Spell 2"));
        cards.add(new TroopCard("Troop 1"));
        cards.add(new TroopCard("Troop 2"));
        cards.add(new BuildingCard("Building 1"));
        cards.add(new BuildingCard("Building 2"));

        Collections.shuffle(cards); // Shuffle the cards

        // Ensure all cards are face-down at the start of the game
        for (Card card : cards) {
            if (card.isFaceUp()) {
                card.flip(); // Set each card to face-down
            }
        }
    }

    public Card getCard(int index) {
        return cards.get(index); // Return the card at the specified index
    }

    public void flipCard(int index) {
        if (!matchedCards[index]) { // Only flip cards that are not already matched
            cards.get(index).flip(); // Flip the card at the specified index
        }
    }

    public int getCardCount() {
        return cards.size(); // Return the total number of cards
    }

    public boolean isCardMatched(int index) {
        return matchedCards[index]; // Return whether the card at the given index is matched
    }

    public void matchCards(int firstCardIndex, int secondCardIndex) {
        // Compare the types of the two cards and mark them as matched if they are the same type
        Card firstCard = cards.get(firstCardIndex);
        Card secondCard = cards.get(secondCardIndex);

        if (firstCard.getType().equals(secondCard.getType())) {
            matchedCards[firstCardIndex] = true;
            matchedCards[secondCardIndex] = true;

            // Award points based on the type of card
            if (firstCard instanceof SpellCard) {
                score += 1;
            } else if (firstCard instanceof TroopCard) {
                score += 2;
            } else if (firstCard instanceof BuildingCard) {
                score += 3;
            }

            moves++; // Increment the moves after a match is made
        }
    }

    public int getScore() {
        return score; // Return the current score
    }

    public int getMoves() {
        return moves; // Return the current move count
    }

    public void resetCard(int index) {
        // Reset the card to face down
        cards.get(index).flip();
    }

    // Check if the game is over (all cards have been matched)
    public boolean isGameOver() {
        for (boolean matched : matchedCards) {
            if (!matched) { // If any card is not matched
                return false;
            }
        }
        return true; // All cards are matched
    }

    public void resetGame() {
        initializeGame(); // Reinitialize game state
        moves = 0; // Reset moves to 0 when the game is reset
    }
}
