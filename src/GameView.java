import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame
{
	private JButton[][] cardButtons; // Grid of buttons for cards
	private JButton showAllButton; // Button to show all cards
	private JButton restartButton; // Button to restart the game
	private JLabel scoreLabel; // Display score
	private JLabel movesLabel; // Display number of moves
	private int score = 0; // Player's score
	private int moves = 0; // Moves counter
	private Game game; // Game instance
	private int firstCardIndex = -1; // First card index selected
	private int secondCardIndex = -1; // Second card index selected

	public GameView()
	{
		// Set up the frame
		setTitle("Memory Game");
		setSize(500, 500); // Adjusted height for a 4x4 grid
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Create the grid for cards
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new GridLayout(4, 4)); // Set to 4 rows and 4
													// columns
		cardButtons = new JButton[4][4]; // 4x4 grid

		// Create buttons for the 4x4 grid
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				cardButtons[i][j] = new JButton("Card");
				final int index = i * 4 + j; // Calculate the index for game
												// logic
				cardButtons[i][j].addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						handleCardClick(index); // Handle card click
					}
				});
				cardPanel.add(cardButtons[i][j]);
			}
		}

		// Add card panel to the center of the frame
		add(cardPanel, BorderLayout.CENTER);

		// Create score and moves display
		scoreLabel = new JLabel("Score: " + score);
		movesLabel = new JLabel("Moves: " + moves);
		JPanel infoPanel = new JPanel();
		infoPanel.add(scoreLabel);
		infoPanel.add(movesLabel);
		add(infoPanel, BorderLayout.NORTH);

		// Create show all and restart buttons
		showAllButton = new JButton("Show All Cards");
		restartButton = new JButton("Restart Game");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(showAllButton);
		buttonPanel.add(restartButton);
		add(buttonPanel, BorderLayout.SOUTH);

		// Add action listeners for buttons
		showAllButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showAllCards(); // Show all cards when the button is pressed
			}
		});

		restartButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				restartGame(); // Restart the game when the button is pressed
			}
		});

		game = new Game(); // Initialize the game instance
		setVisible(true); // Make the frame visible
	}

	private void handleCardClick(int index)
	{
		// Check if two cards are already flipped or if the clicked card is
		// already matched
		if (firstCardIndex != -1 && secondCardIndex != -1
				|| game.isCardMatched(index))
		{
			return; // Do nothing if two cards are already flipped or the card
					// is matched
		}

		if (game.flipCard(index))
		{ // Flip the card if it is face down
			String cardValue = game.getCardValue(index);
			cardButtons[index / 4][index % 4].setText(cardValue); // Update
																	// button
																	// text to
																	// show card
																	// value

			if (firstCardIndex == -1)
			{
				firstCardIndex = index; // First card selected
			}
			else
			{
				secondCardIndex = index; // Second card selected
				game.incrementMoves(); // Increment moves count

				// Check for a match
				if (game.getCardValue(firstCardIndex)
						.equals(game.getCardValue(secondCardIndex)))
				{
					game.incrementScore(); // Increment score if matched
					// Update matched cards
					cardButtons[firstCardIndex / 4][firstCardIndex % 4]
							.setText("Matched");
					cardButtons[secondCardIndex / 4][secondCardIndex % 4]
							.setText("Matched");
					cardButtons[firstCardIndex / 4][firstCardIndex % 4]
							.setEnabled(false); // Disable button
					cardButtons[secondCardIndex / 4][secondCardIndex % 4]
							.setEnabled(false); // Disable button
					updateScoreAndMoves(); // Update score display
					resetCardSelection(); // Reset selection
				}
				else
				{
					// No match found, schedule unflip
					Timer timer = new Timer(1000, new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							unflipCards(firstCardIndex, secondCardIndex); // Unflip
																			// cards
																			// after
																			// delay
							resetCardSelection(); // Reset selected cards
						}
					});
					timer.setRepeats(false); // Ensure the timer only runs once
					timer.start(); // Start the timer
				}
			}
			updateScoreAndMoves(); // Update the score and moves display
		}
	}

	// Method to show all cards
	private void showAllCards()
	{
		for (int i = 0; i < 16; i++)
		{
			cardButtons[i / 4][i % 4].setText(game.getCardValue(i)); // Update
																		// button
																		// text
																		// to
																		// show
																		// card
																		// values
			game.flipCard(i); // Mark the card as flipped in the game logic
			cardButtons[i / 4][i % 4].setEnabled(false); // Disable buttons to
															// prevent further
															// clicks
		}
	}

	// Method to restart the game
	private void restartGame()
	{
		game.resetGame(); // Reinitialize the game
		score = 0; // Reset score
		moves = 0; // Reset moves
		updateScoreAndMoves(); // Update the display
		for (int i = 0; i < 16; i++)
		{
			cardButtons[i / 4][i % 4].setText("Card"); // Reset button text
			cardButtons[i / 4][i % 4].setEnabled(true); // Enable buttons for
														// new game
		}
		resetCardSelection(); // Reset selected cards
	}

	// Method to unflip cards after a delay
	private void unflipCards(int firstIndex, int secondIndex)
	{
		// Check if indices are valid before accessing the array
		if (firstIndex >= 0 && firstIndex < 16 && secondIndex >= 0
				&& secondIndex < 16)
		{
			cardButtons[firstIndex / 4][firstIndex % 4].setText("Card"); // Reset
																			// text
																			// to
																			// "Card"
			cardButtons[secondIndex / 4][secondIndex % 4].setText("Card"); // Reset
																			// text
																			// to
																			// "Card"
			game.flipCard(firstIndex); // Unflip the first card
			game.flipCard(secondIndex); // Unflip the second card
		}
	}

	// Method to reset selected card indices
	private void resetCardSelection()
	{
		firstCardIndex = -1; // Reset first card index
		secondCardIndex = -1; // Reset second card index
	}

	// Method to update the score and moves labels
	private void updateScoreAndMoves()
	{
		scoreLabel.setText("Score: " + game.getScore());
		movesLabel.setText("Moves: " + game.getMoves());
	}
}
