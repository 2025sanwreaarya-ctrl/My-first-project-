import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GuessingGameGUI {

    static int number;
    static int attempts = 0;
    static int maxAttempts;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessingGameGUI::createGUI);
    }

    public static void createGUI() {

        number = new Random().nextInt(100) + 1;

        // Frame
        JFrame frame = new JFrame("Guess The Number");
        frame.setSize(450, 520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Blue Theme Colors
        Color bgColor = new Color(224, 242, 254);       // Light Sky Blue
        Color buttonColor = new Color(147, 197, 253);   // Soft Blue
        Color titleColor = new Color(30, 64, 175);      // Dark Blue
        Color textColor = new Color(37, 99, 235);       // Royal Blue

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgColor);
        frame.add(topPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Title
        JLabel title = new JLabel("Guess The Number (1 - 100)", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        title.setForeground(titleColor);

        // Subtitle
        JLabel subtitle = new JLabel("Choose a difficulty and start guessing!", JLabel.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 13));
        subtitle.setForeground(Color.DARK_GRAY);

        // Difficulty Levels
        String[] levels = {
                "Easy",
                "Medium - 10 tries",
                "Hard - 5 tries"
        };

        JComboBox<String> difficultyBox = new JComboBox<>(levels);
        difficultyBox.setBackground(Color.WHITE);
        difficultyBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Input Field
        JTextField input = new JTextField();
        input.setFont(new Font("Arial", Font.PLAIN, 15));
        input.setHorizontalAlignment(JTextField.CENTER);

        // Attempts Label
        JLabel attemptLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        attemptLabel.setFont(new Font("Arial", Font.BOLD, 14));
        attemptLabel.setForeground(titleColor);

        // Result Label
        JLabel result = new JLabel("Enter your guess.", JLabel.CENTER);
        result.setFont(new Font("Arial", Font.BOLD, 14));
        result.setForeground(textColor);

        // Buttons
        JButton guessBtn = new JButton("Guess");
        JButton newGameBtn = new JButton("New Game");

        guessBtn.setFont(new Font("Arial", Font.BOLD, 15));
        newGameBtn.setFont(new Font("Arial", Font.BOLD, 15));

        guessBtn.setBackground(buttonColor);
        newGameBtn.setBackground(buttonColor);

        guessBtn.setFocusPainted(false);
        newGameBtn.setFocusPainted(false);

        // Add Components
        panel.add(title);
        panel.add(subtitle);
        panel.add(difficultyBox);
        panel.add(input);
        panel.add(attemptLabel);
        panel.add(result);
        panel.add(guessBtn);
        panel.add(newGameBtn);

        frame.add(panel, BorderLayout.CENTER);

        // Press Enter to Guess
        input.addActionListener(e -> guessBtn.doClick());

        // Guess Logic
        guessBtn.addActionListener(e -> {

            try {

                int guess = Integer.parseInt(input.getText());

                if (guess < 1 || guess > 100) {
                    result.setText("Enter a number between 1 and 100.");
                    return;
                }

                // Set difficulty once
                if (attempts == 0) {
                    String level = (String) difficultyBox.getSelectedItem();

                    if (level.startsWith("Easy"))
                        maxAttempts = Integer.MAX_VALUE;
                    else if (level.startsWith("Medium"))
                        maxAttempts = 10;
                    else
                        maxAttempts = 5;

                    difficultyBox.setEnabled(false);
                }

                attempts++;
                attemptLabel.setText("Attempts: " + attempts);

                if (attempts > maxAttempts) {
                    result.setText("Game Over! The number was " + number + ".");
                    guessBtn.setEnabled(false);
                    return;
                }

                if (guess < number) {

                    if (maxAttempts == Integer.MAX_VALUE)
                        result.setText("Too Low!");
                    else
                        result.setText("Too Low! Remaining: " + (maxAttempts - attempts));

                } else if (guess > number) {

                    if (maxAttempts == Integer.MAX_VALUE)
                        result.setText("Too High!");
                    else
                        result.setText("Too High! Remaining: " + (maxAttempts - attempts));

                } else {

                    String message;

                    if (attempts <= 3)
                        message = "Excellent! You guessed it in " + attempts + " attempts.";
                    else if (attempts <= 6)
                        message = "Great job! You guessed it in " + attempts + " attempts.";
                    else
                        message = "Correct! You guessed it in " + attempts + " attempts.";

                    result.setText(message);
                    guessBtn.setEnabled(false);
                }

                input.setText("");

            } catch (NumberFormatException ex) {
                result.setText("Please enter a valid number.");
            }
        });

        // New Game Button
        newGameBtn.addActionListener(e -> {

            number = new Random().nextInt(100) + 1;
            attempts = 0;

            input.setText("");
            input.requestFocus();

            attemptLabel.setText("Attempts: 0");
            result.setText("New game started. Good luck!");

            difficultyBox.setEnabled(true);
            difficultyBox.setSelectedIndex(0);

            guessBtn.setEnabled(true);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}