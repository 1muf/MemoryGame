import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private JButton[][] cardButtons; // Grid of buttons for cards
    private JButton showAllButton;   // Button to show all cards
    private JButton restartButton;    // Button to restart the game
    private JLabel scoreLabel;        // Display score
    private JLabel movesLabel;        // Display number of moves
    private int score = 0;            // Player's score
    private int moves = 0;            // Moves counter

    public GameView() {
        // Set up the frame
        setTitle("Memory Game");
        setSize(400, 300); // Adjusted height for a 3x2 grid
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the grid for cards
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(2, 3)); // Set to 2 rows and 3 columns
        cardButtons = new JButton[2][3]; // 2x3 grid

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cardButtons[i][j] = new JButton("Card");
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

        setVisible(true); // Make the frame visible
    }

    // Method to update the score and moves labels
    private void updateScoreAndMoves() {
        scoreLabel.setText("Score: " + score);
        movesLabel.setText("Moves: " + moves);
    }
}
