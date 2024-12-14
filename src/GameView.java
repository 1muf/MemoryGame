
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
 *         This class handles the user interface for the Memory Game. It creates
 *         and manages
 *         the graphical components of the game, including the display of the
 *         card grid,
 *         score, and number of moves. It interacts with the Game model to flip
 *         cards, check
 *         for matches, update the score, and manage game state transitions. It
 *         also provides
 *         functionality for restarting the game and displaying the solution.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame
{
	private JButton[][] cardButtons; // Grid of buttons for cards
	private Game game; // Game instance
	private int firstCardIndex = -1; // Index of the first card clicked
	private int secondCardIndex = -1; // Index of the second card clicked
	private int moves = 0; // Counter for moves
	private JLabel scoreLabel; // Label to display score
	private JLabel movesLabel; // Label to display moves
	private boolean isSolutionVisible = false;
	private boolean isTimerActive = false; // Flag to track if a timer is
											// running

	public GameView()
	{
		game = new Game();

		setTitle("Memory Game");
		setSize(600, 600); // Adjust the size for the 4x4 grid
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Top panel for Restart and Score
		JPanel topPanel = new JPanel(new BorderLayout());
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				resetGame();
			}
		});
		scoreLabel = new JLabel("Score: 0"); // Placeholder for score
		topPanel.add(restartButton, BorderLayout.WEST);
		topPanel.add(scoreLabel, BorderLayout.EAST);
		add(topPanel, BorderLayout.NORTH);

		// Bottom panel for Moves and Show Solution
		JPanel bottomPanel = new JPanel(new BorderLayout());
		movesLabel = new JLabel("Moves: 0");
		JButton showSolutionButton = new JButton("Show Solution");
		showSolutionButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showSolution();
			}
		});
		bottomPanel.add(movesLabel, BorderLayout.WEST);
		bottomPanel.add(showSolutionButton, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		// Card grid in the center (4x4 grid)
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new GridLayout(4, 4)); // 4x4 grid
		cardButtons = new JButton[4][4]; // 4x4 grid of buttons
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				cardButtons[i][j] = new JButton("Card");
				final int index = i * 4 + j; // Calculate index for 4x4 grid
				cardButtons[i][j].addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						handleCardClick(index);
					}
				});
				cardPanel.add(cardButtons[i][j]);
			}
		}
		add(cardPanel, BorderLayout.CENTER);

		setVisible(true);
	}

	private void handleCardClick(int index)
	{
		// Prevent interaction if the solution is visible or if the card is
		// already matched
		if (isSolutionVisible || game.isCardMatched(index)
				|| firstCardIndex == index || secondCardIndex == index
				|| secondCardIndex != -1)
		{
			return; // Don't allow clicks if solution is visible
		}

		// Flip the card (this happens every time a card is clicked)
		game.flipCard(index);
		Card card = game.getCard(index);
		JButton button = cardButtons[index / 4][index % 4]; // Updated indexing
															// for 4x4 grid
		if (card.isFaceUp())
		{
			button.setBackground(card.getColor());
			button.setText(card.getValue());
		}
		else
		{
			button.setBackground(null);
			button.setText("Card");
		}

		if (firstCardIndex == -1)
		{
			firstCardIndex = index;
		}
		else if (secondCardIndex == -1)
		{
			secondCardIndex = index;

			// Increment moves only after the second card is clicked
			moves++;
			movesLabel.setText("Moves: " + moves); // Update moves label

			Card firstCard = game.getCard(firstCardIndex);
			Card secondCard = game.getCard(secondCardIndex);

			if (firstCard.getType().equals(secondCard.getType()))
			{
				game.matchCards(firstCardIndex, secondCardIndex);
				// Update button text to "Solved"
				cardButtons[firstCardIndex / 4][firstCardIndex % 4]
						.setText("Solved");
				cardButtons[secondCardIndex / 4][secondCardIndex % 4]
						.setText("Solved");

				cardButtons[firstCardIndex / 4][firstCardIndex % 4]
						.setEnabled(false);
				cardButtons[secondCardIndex / 4][secondCardIndex % 4]
						.setEnabled(false);

				firstCardIndex = -1;
				secondCardIndex = -1;

				// Update score
				scoreLabel.setText("Score: " + game.getScore());
			}
			else
			{
				// Set the timer only if it is not active
				if (!isTimerActive)
				{
					isTimerActive = true; // Set timer flag to true
					Timer timer = new Timer(1000, new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							if (!isSolutionVisible)
							{
								resetCardSelection(); // Reset only if solution
														// is not visible
							}
							isTimerActive = false; // Reset timer flag
						}
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		}

		if (game.isGameOver())
		{
			JOptionPane.showMessageDialog(this,
					"Congratulations! You've solved all matches!");
		}
	}

	private void resetCardSelection()
	{
		if (firstCardIndex != -1 && secondCardIndex != -1)
		{
			game.resetCard(firstCardIndex);
			game.resetCard(secondCardIndex);
			cardButtons[firstCardIndex / 4][firstCardIndex % 4]
					.setBackground(null);
			cardButtons[firstCardIndex / 4][firstCardIndex % 4].setText("Card");
			cardButtons[secondCardIndex / 4][secondCardIndex % 4]
					.setBackground(null);
			cardButtons[secondCardIndex / 4][secondCardIndex % 4]
					.setText("Card");
		}
		firstCardIndex = -1;
		secondCardIndex = -1;
	}

	private void resetGame()
	{
		game.resetGame();
		moves = 0;
		movesLabel.setText("Moves: " + moves);
		scoreLabel.setText("Score: 0");

		// Reset the solution visibility flag
		isSolutionVisible = false;

		for (int i = 0; i < 4; i++)
		{ // Reset for 4x4 grid
			for (int j = 0; j < 4; j++)
			{
				cardButtons[i][j].setEnabled(true); // Enable all card buttons
				cardButtons[i][j].setBackground(null); // Reset background
				cardButtons[i][j].setText("Card"); // Reset text
			}
		}

		firstCardIndex = -1;
		secondCardIndex = -1;
	}

	private void showSolution()
	{
		// Disable card buttons to prevent interaction
		isSolutionVisible = true;
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				cardButtons[i][j].setEnabled(false); // Disable all card buttons
			}
		}

		// Show the solution by revealing the cards
		for (int i = 0; i < game.getCardCount(); i++)
		{
			Card card = game.getCard(i);
			cardButtons[i / 4][i % 4].setText(card.getValue());
			cardButtons[i / 4][i % 4].setBackground(card.getColor());
		}

		// If the solution is visible, ensure that the timer won't reset cards
		if (isTimerActive)
		{
			isTimerActive = false; // Stop the timer if the solution is shown
		}
	}
}