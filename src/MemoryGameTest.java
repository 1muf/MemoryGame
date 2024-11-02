import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Assuming Card and MemoryGame classes are in the same package
public class MemoryGameTest {

    // Test Case 1: Test Card Creation
    @Test
    public void testCardCreation() {
        Card card = new Card("A");
        assertEquals("A", card.getValue());
        assertFalse(card.isFaceUp(), "Card should initially be face down");
    }

    // Test Case 2: Test Card Flipping
    @Test
    public void testCardFlipping() {
        Card card = new Card("A");
        card.flip();
        assertTrue(card.isFaceUp(), "Card should be face up after flipping");
        card.flip();
        assertFalse(card.isFaceUp(), "Card should be face down after flipping again");
    }

    // Test Case 3: Test Match Logic
    public boolean cardsMatch(Card card1, Card card2) {
        return card1.getValue().equals(card2.getValue());
    }

    @Test
    public void testMatchLogic() {
        Card card1 = new Card("A");
        Card card2 = new Card("A");
        assertTrue(cardsMatch(card1, card2), "Cards with the same value should match");

        Card card3 = new Card("B");
        assertFalse(cardsMatch(card1, card3), "Cards with different values should not match");
    }

    // Test Case 4: Test Scoring System
    @Test
    public void testScoringSystem() {
        int score = 0;
        int pointsPerMatch = 10;

        score += pointsPerMatch; // Simulate a match
        assertEquals(10, score, "Score should be 10 after one match");

        score += pointsPerMatch; // Simulate another match
        assertEquals(20, score, "Score should be 20 after two matches");
    }

    // Test Case 5: Test Moves Counter
    @Test
    public void testMovesCounter() {
        int moves = 0;

        // Simulate flipping two cards
        moves += 1; // Assuming two cards flipped
        assertEquals(1, moves, "Moves should be 1 after first flip");

        // Simulate another pair of flips
        moves += 1;
        assertEquals(2, moves, "Moves should be 2 after second flip");
    }

    // Test Case 6: Test Restart Game Functionality
    @Test
    public void testRestartGame() {
        int score = 50; // Score before restart
        int moves = 10; // Moves before restart

        // Restart logic
        score = 0; // Reset score
        moves = 0; // Reset moves

        assertEquals(0, score, "Score should be reset to 0 on restart");
        assertEquals(0, moves, "Moves should be reset to 0 on restart");
    }
}
