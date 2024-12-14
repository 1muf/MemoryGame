
/**
 * Lead Author(s):
 * 
 * @author Tyler Wang
 * 
 *         Other contributors:
 *         None
 * 
 *         Version/date: 8.0 / 12/13/2024
 * 
 *         Responsibilities of class:
 *         This class contains unit tests for the Memory Game. It includes test
 *         cases to verify
 *         the functionality of various game components such as card creation,
 *         flipping cards,
 *         matching cards by type, scoring system, moves counter, game over
 *         condition, and
 *         restart game functionality. The tests ensure the correct behavior of
 *         the game's logic
 *         and interactions between different components.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryGameTest
{

	// Test Case 1: Test Card Creation
	@Test
	public void testCardCreation()
	{
		Card card = new SpellCard("Spell 1");
		assertEquals("Spell 1", card.getValue());
		assertFalse(card.isFaceUp(), "Card should initially be face down");
	}

	// Test Case 2: Test Card Flipping
	@Test
	public void testCardFlipping()
	{
		Card card = new SpellCard("Spell 1");
		card.flip();
		assertTrue(card.isFaceUp(), "Card should be face up after flipping");
		card.flip();
		assertFalse(card.isFaceUp(),
				"Card should be face down after flipping again");
	}

	// Test Case 3: Test Card Matching by Type
	@Test
	public void testCardMatchingByType()
	{
		Card card1 = new TroopCard("Troop 1");
		Card card2 = new TroopCard("Troop 2");
		assertEquals(card1.getType(), card2.getType(),
				"Cards with the same type should match");

		Card card3 = new BuildingCard("Building 1");
		assertNotEquals(card1.getType(), card3.getType(),
				"Cards with different types should not match");
	}

	// Test Case 4: Test Score Increases with Each Match
	@Test
	public void testScoringSystem()
	{
		Game game = new Game();
		int initialScore = game.getScore(); // Initial score before clicking any
											// cards

		// Loop through all pairs of cards (using indices 0-5 for 6 cards)
		for (int i = 0; i < game.getCardCount(); i++)
		{
			for (int j = i + 1; j < game.getCardCount(); j++)
			{
				// Simulate the card clicks: flip the first card
				game.flipCard(i);
				// Simulate the card clicks: flip the second card
				game.flipCard(j);

				// After flipping both cards, attempt to match them
				game.matchCards(i, j);
			}
		}

		// After clicking all combinations, check if the score has increased
		int updatedScore = game.getScore(); // Get the updated score after all
											// clicks
		assertTrue(updatedScore > initialScore,
				"Score should increase after matching cards");
	}

	// Test Case 5: Test Moves Counter
	@Test
	public void testMovesCounter()
	{
		Game game = new Game();

		// Initial moves should be 0
		int initialMoves = game.getMoves();

		// Loop through all pairs of cards (using indices 0-15 for 16 cards)
		for (int i = 0; i < game.getCardCount(); i++)
		{
			for (int j = i + 1; j < game.getCardCount(); j++)
			{
				// Simulate flipping the first card
				game.flipCard(i);
				// Simulate flipping the second card
				game.flipCard(j);

				// After flipping both cards, match them
				game.matchCards(i, j);
			}
		}

		// After all pairs have been flipped, the number of moves should be the
		// number of pairs (120)
		int finalMoves = game.getMoves();
		// Expect 120 moves (since we're matching all pairs of 16 cards)
		assertEquals(8, finalMoves,
				"Moves should be 8 after flipping all pairs");
	}

	// Test Case 6: Test Game Over Condition
	@Test
	public void testGameOverCondition()
	{
		Game game = new Game();

		// Loop through all pairs of cards (using indices 0-5 for 6 cards)
		for (int i = 0; i < game.getCardCount(); i++)
		{
			for (int j = i + 1; j < game.getCardCount(); j++)
			{
				// Simulate flipping the first card
				game.flipCard(i);
				// Simulate flipping the second card
				game.flipCard(j);

				// After flipping both cards, match them
				game.matchCards(i, j);
			}
		}

		// Check if the game is over after all cards have been matched
		assertTrue(game.isGameOver(),
				"Game should be over when all cards are matched");
	}

	// Test Case 7: Test Restart Game Functionality
	@Test
	public void testRestartGame()
	{
		Game game = new Game();

		// Perform some actions
		game.matchCards(0, 1); // First match
		game.matchCards(2, 3); // Second match
		int initialScore = game.getScore();
		int initialMoves = game.getMoves();

		// Reset game
		game.resetGame();

		// Ensure the game state is reset
		assertEquals(0, game.getScore(),
				"Score should be reset to 0 on restart");
		assertEquals(0, game.getMoves(),
				"Moves should be reset to 0 on restart");
		assertFalse(game.isGameOver(),
				"Game should not be over after restarting");

		// Check that cards are shuffled and flipped back to face-down
		assertFalse(game.getCard(0).isFaceUp(),
				"Card 0 should be face down after reset");
		assertFalse(game.getCard(1).isFaceUp(),
				"Card 1 should be face down after reset");
	}
}
