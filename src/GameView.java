import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {
    private JButton[][] cardButtons; // Grid of buttons for cards
    private Game game; // Game instance
    private int firstCardIndex = -1; // Index of the first card clicked
    private int secondCardIndex = -1; // Index of the second card clicked
    private int moves = 0; // Counter for moves
    private JLabel scoreLabel; // Label to display score
    private JLabel movesLabel; // Label to display moves

    public GameView() {
        game = new Game();

        setTitle("Memory Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for Restart and Score
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        showSolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSolution();
            }
        });
        bottomPanel.add(movesLabel, BorderLayout.WEST);
        bottomPanel.add(showSolutionButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Card grid in the center
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(2, 3));
        cardButtons = new JButton[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cardButtons[i][j] = new JButton("Card");
                final int index = i * 3 + j;
                cardButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCardClick(index);
                    }
                });
                cardPanel.add(cardButtons[i][j]);
            }
        }
        add(cardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleCardClick(int index) {
        if (game.isCardMatched(index) || firstCardIndex == index || secondCardIndex == index || secondCardIndex != -1) {
            return;
        }

        moves++; // Increment moves
        movesLabel.setText("Moves: " + moves); // Update moves label

        game.flipCard(index);
        Card card = game.getCard(index);
        JButton button = cardButtons[index / 3][index % 3];
        if (card.isFaceUp()) {
            button.setBackground(card.getColor());
            button.setText(card.getValue());
        } else {
            button.setBackground(null);
            button.setText("Card");
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
        } else if (secondCardIndex == -1) {
            secondCardIndex = index;
            Card firstCard = game.getCard(firstCardIndex);
            Card secondCard = game.getCard(secondCardIndex);

            if (firstCard.getType().equals(secondCard.getType())) {
                game.matchCards(firstCardIndex, secondCardIndex);
                // Update button text to "Solved"
                cardButtons[firstCardIndex / 3][firstCardIndex % 3].setText("Solved");
                cardButtons[secondCardIndex / 3][secondCardIndex % 3].setText("Solved");

                cardButtons[firstCardIndex / 3][firstCardIndex % 3].setEnabled(false);
                cardButtons[secondCardIndex / 3][secondCardIndex % 3].setEnabled(false);

                firstCardIndex = -1;
                secondCardIndex = -1;

                // Update score
                scoreLabel.setText("Score: " + game.getScore());

            } else {
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resetCardSelection();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }

        if (game.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You've solved all matches!");
        }
    }

    private void resetCardSelection() {
        game.resetCard(firstCardIndex);
        game.resetCard(secondCardIndex);
        cardButtons[firstCardIndex / 3][firstCardIndex % 3].setBackground(null);
        cardButtons[firstCardIndex / 3][firstCardIndex % 3].setText("Card");
        cardButtons[secondCardIndex / 3][secondCardIndex % 3].setBackground(null);
        cardButtons[secondCardIndex / 3][secondCardIndex % 3].setText("Card");
        firstCardIndex = -1;
        secondCardIndex = -1;
    }

    private void resetGame() {
        game.resetGame();
        moves = 0;
        movesLabel.setText("Moves: " + moves);
        scoreLabel.setText("Score: 0");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cardButtons[i][j].setEnabled(true);
                cardButtons[i][j].setBackground(null);
                cardButtons[i][j].setText("Card");
            }
        }
        firstCardIndex = -1;
        secondCardIndex = -1;
    }

    private void showSolution() {
        for (int i = 0; i < game.getCardCount(); i++) {
            Card card = game.getCard(i);
            cardButtons[i / 3][i % 3].setText(card.getValue());
            cardButtons[i / 3][i % 3].setBackground(card.getColor());
        }
    }
}