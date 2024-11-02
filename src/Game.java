import java.util.Random;

public class Game
{
	private String[] cards; // Array to hold card values
	private boolean[] flipped; // Array to track which cards are face-up
	private int score; // Player's score
	private int moves; // Number of moves made
	private int totalPairs; // Total pairs in the game

	public Game()
	{
		initializeGame();
	}

	private void initializeGame()
	{
		cards = new String[16]; // 4x4 grid
		flipped = new boolean[16]; // All cards start face down
		score = 0;
		moves = 0;
		totalPairs = 8; // Assuming 8 pairs of cards

		// Initialize the card values (you can customize this)
		String[] values = { "A", "A", "B", "B", "C", "C", "D", "D", "E", "E",
				"F", "F", "G", "G", "H", "H" };

		// Shuffle the card values
		shuffleCards(values);
	}

	private void shuffleCards(String[] values)
	{
		Random rand = new Random();
		for (int i = 0; i < values.length; i++)
		{
			int randomIndex = rand.nextInt(values.length);
			String temp = values[i];
			values[i] = values[randomIndex];
			values[randomIndex] = temp;
		}
		cards = values; // Assign shuffled values to cards
	}

	public boolean flipCard(int index)
	{
		if (index >= 0 && index < cards.length)
		{
			flipped[index] = !flipped[index]; // Toggle the flipped state
			return true; // Successfully flipped
		}
		return false; // Invalid index
	}

	public String getCardValue(int index)
	{
		if (index >= 0 && index < cards.length)
		{
			return flipped[index] ? cards[index] : "Card"; // Show card value if
															// flipped, else
															// "Card"
		}
		return null; // Invalid index
	}

	public boolean isCardMatched(int index)
	{
		if (index >= 0 && index < cards.length)
		{
			return flipped[index] && cards[index].equals("Matched"); // Check if
																		// the
																		// card
																		// is
																		// matched
		}
		return false; // Invalid index
	}

	public void incrementScore()
	{
		score++; // Increase score
	}

	public int getScore()
	{
		return score; // Return current score
	}

	public void incrementMoves()
	{
		moves++; // Increase moves
	}

	public int getMoves()
	{
		return moves; // Return current number of moves
	}

	public void resetGame()
	{
		initializeGame(); // Reinitialize game state
	}
}