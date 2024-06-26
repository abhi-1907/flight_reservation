package help;
import java.awt.*;
import javax.swing.*;

public class HelpPageFrame extends JFrame {
    public HelpPageFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Help Page");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); 


        // Help content text area
        JTextArea txtHelpContent = new JTextArea(15, 50);
        txtHelpContent.setEditable(false);
        txtHelpContent.setMargin(new Insets(10, 10, 10, 10));
        txtHelpContent.setFont(new Font("Arial", Font.PLAIN, 14));
        txtHelpContent.setText(getHelpText());
        JScrollPane scrollPane = new JScrollPane(txtHelpContent);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for asking questions
        JPanel questionPanel = new JPanel(new GridBagLayout());
        questionPanel.setBackground(new Color(230, 230, 230));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblEmail = new JLabel("Your Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14)); 
        JTextField txtEmail = new JTextField(30);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel lblQuestion = new JLabel("Your Question:");
        JTextArea txtQuestion = new JTextArea(5, 30);
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        txtQuestion.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane questionScrollPane = new JScrollPane(txtQuestion);
        JButton btnSubmit = new JButton("Submit Question");
        btnSubmit.setBackground(new Color(50, 150, 250)); 
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        JButton btnHide = new JButton("Home");
        btnHide.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for button
        btnHide.setBackground(new Color(250, 100, 100)); // Red background
        btnHide.setForeground(Color.WHITE); // White text color
        btnHide.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 5);
        questionPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(10, 5, 5, 10);
        questionPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        questionPanel.add(lblQuestion, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 10);
        questionPanel.add(questionScrollPane, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(230, 230, 230)); 

        buttonPanel.add(btnHide);
        buttonPanel.add(btnSubmit);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // span across two columns
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 10, 10, 10);
        questionPanel.add(buttonPanel, gbc);

        btnSubmit.addActionListener(e -> {
            String email = txtEmail.getText();
            String question = txtQuestion.getText();

            if (email.isEmpty() || question.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your email and question.");
            } else {
                // Simulate sending the question
                JOptionPane.showMessageDialog(this, "Your question has been submitted. We will get back to you at " + email);
                txtEmail.setText("");
                txtQuestion.setText("");
            }
        });
        btnHide.addActionListener(e -> setVisible(false));

        mainPanel.add(questionPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private String getHelpText() {
        return "Welcome to the Flight Management System Help Page!\n\n" +
                "How to use the system:\n" +
                "1. Login or Register: Start by logging in or creating a new account if you don't have one.\n" +
                "2. User Profile: After logging in, you can view and update your profile, change password, and set preferences.\n" +
                "3. Book a Flight: Go to the booking page, select your flight, enter passenger details, and proceed to payment.\n" +
                "4. Search Flights: Use the search page to find available flights based on your criteria.\n\n" +
                "Frequently Asked Questions (FAQs):\n" +
                "Q: How do I reset my password?\n" +
                "A: Go to your user profile and click on 'Change Password'. Enter your new password and save changes.\n\n" +
                "Q: How can I update my communication preferences?\n" +
                "A: In your user profile, click on 'Update Preferences' and enter your new preferences.\n\n" +
                "Q: How do I search for flights?\n" +
                "A: Use the flight search page to enter departure city, arrival city, and travel date, then click 'Search Flights'.\n\n" +
                "Q: What payment methods are accepted?\n" +
                "A: We accept major credit cards including Visa, MasterCard, and American Express.\n\n" +
                "For further assistance, please contact our support team at support@example.com.";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HelpPageFrame helpPageFrame = new HelpPageFrame();
            helpPageFrame.setVisible(true);
        });
    }
}