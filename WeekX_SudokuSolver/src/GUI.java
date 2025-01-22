import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private static final int ROW = 9;
    private static final int COLUMN = 9;
    public static JTextField[][] numbers;

    public void mainFrame() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for the main panel
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel heading = new JLabel("Sudoku");
        heading.setFont(new Font("Arial", Font.BOLD, 26)); // Set font and size for the heading
        heading.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around the heading
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(heading, gbc); // Add heading to the mainPanel

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(ROW, COLUMN, 0, 0));
        numbers = new JTextField[ROW][COLUMN];

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                numbers[i][j] = new JTextField();
                numbers[i][j].setPreferredSize(new Dimension(50, 50)); // Adjust size as needed
                numbers[i][j].setHorizontalAlignment(JTextField.CENTER); // Center the text
                numbers[i][j].setFont(new Font("Arial", Font.PLAIN, 26)); // Set font and size

                // Set borders for 3x3 subgrids
                int top = (i % 3 == 0) ? 2 : 1;
                int left = (j % 3 == 0) ? 2 : 1;
                int bottom = (i == ROW - 1 || (i + 1) % 3 == 0) ? 2 : 1;
                int right = (j == COLUMN - 1 || (j + 1) % 3 == 0) ? 2 : 1;
                numbers[i][j].setText("2");
                numbers[i][j].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                gridPanel.add(numbers[i][j]);
                numbers[i][j].setText("0");
                numbers[i][j].setEditable(false);
            }

        }
        //CREATES A RANDOM SUDOKU BOARD!
        GameLogic gl = new GameLogic();
        gl.createGame();
        gridPanel.setPreferredSize(new Dimension(500, 500)); // Set preferred size of the gridPanel

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(gridPanel, gbc); // Add gridPanel to the mainPanel

        JPanel buttonPanel = new JPanel(); // Create a panel for the buttons
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();

        JButton submitButton = new JButton("Submit"); // Create a new button
        submitButton.setPreferredSize(new Dimension(120, 40)); // Set preferred size for the button
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(submitButton, buttonGbc); // Add the submit button to the buttonPanel
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks if the answer given is correct!
                GameLogic gl = new GameLogic();
                if(gl.isValid(numbers)){
                    JOptionPane.showMessageDialog(frame, "Correct!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Incorrect, try again!");
                }


            }
        });

        JButton fillButton = new JButton("Fill"); // Create another button
        fillButton.setPreferredSize(new Dimension(120, 40)); // Set preferred size for the button
        buttonGbc.gridx = 1;
        buttonGbc.insets = new Insets(0, 50, 0, 0); // Add gap between the buttons
        buttonPanel.add(fillButton, buttonGbc); // Add the reset button to the buttonPanel
        fillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //fills the grid with the correct answers!!
                GameLogic gl = new GameLogic();
                gl.fillAnswer();
            }
        });


        gbc.gridy = 2; // Position the buttonPanel below the gridPanel
        mainPanel.add(buttonPanel, gbc); // Add the buttonPanel to the mainPanel

        frame.add(mainPanel);
        frame.pack();  // Adjusts frame size to fit preferred sizes of components
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
