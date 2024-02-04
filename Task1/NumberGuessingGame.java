import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int numberOfGuesses;

    private JLabel instructionLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JButton playAgainButton;

    public NumberGuessingGame() {
        // Generating Target Number
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;

        // GUI Component
        instructionLabel = new JLabel("Guess the number between 1-100:");
        guessTextField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        playAgainButton = new JButton("Play Again");

        // Action listener
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        setLayout(new FlowLayout());

        add(instructionLabel);
        add(guessTextField);
        add(guessButton);
        add(resultLabel);
        add(playAgainButton);

        // Set up the frame
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleGuess() {
        try {
            int playerGuess = Integer.parseInt(guessTextField.getText());
            numberOfGuesses++;

            if (playerGuess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed it in " + numberOfGuesses + " guesses.");
                guessButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            } else if (playerGuess < randomNumber) {
                resultLabel.setText("Guess is Low. Try again.");
            } else {
                resultLabel.setText("Guess is high. Try again.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        numberOfGuesses = 0;

        guessTextField.setText("");
        resultLabel.setText("");
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
