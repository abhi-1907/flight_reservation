
package loginsystem;

import java.awt.*;
import javax.swing.*;
import loginsystem.userbackend.UserAccount;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String fileName) {
        backgroundImage = new ImageIcon(fileName).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

public class FlightManagementSystem extends JFrame {
    public CardLayout cardLayout;
    public JPanel mainPanel;
    public User currentUser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightManagementSystem flightManagementSystem = new FlightManagementSystem();
            flightManagementSystem.setVisible(true);
        });
    }

    public FlightManagementSystem() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Flight Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel background = new BackgroundPanel("C:\\Users\\abhij\\Desktop\\imageproject\\backimage.jpg");
        background.setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new CardLayout());

        setContentPane(background);

        background.add(mainPanel, BorderLayout.CENTER);

        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        currentUser = null;

        LoginPanel loginPanel = new LoginPanel(this);
        RegistrationPanel registrationPanel = new RegistrationPanel(this);
        UserProfilePanel userProfilePanel = new UserProfilePanel(this);

        mainPanel.add(loginPanel, "Login");
        mainPanel.add(registrationPanel, "Register");
        mainPanel.add(userProfilePanel, "UserProfile");

        cardLayout.show(mainPanel, "Login");
    }

    public void showCard(String card) {
        cardLayout.show(mainPanel, card);
    }
}

class LoginPanel extends JPanel {
    public LoginPanel(FlightManagementSystem mainFrame) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(20);
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(20);
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(btnLogin, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnRegister, gbc);

        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            UserAccount user = new UserAccount(username, password);
            if (user.isValidUser()) {
                mainFrame.currentUser = new User(username, password);
                mainFrame.showCard("UserProfile");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Invalid username or password.");
            }
        });

        btnRegister.addActionListener(e -> mainFrame.showCard("Register"));
    }
}

class RegistrationPanel extends JPanel {
    public RegistrationPanel(FlightManagementSystem mainFrame) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUsername = new JLabel("Username:");
        JTextField txtUsername = new JTextField(20);
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField(20);
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        JPasswordField txtConfirmPassword = new JPasswordField(20);
        JButton btnRegister = new JButton("Register");
        JButton btnBackToLogin = new JButton("Back to Login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);
        gbc.gridx = 1;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPassword, gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblConfirmPassword, gbc);
        gbc.gridx = 1;
        add(txtConfirmPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnRegister, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(btnBackToLogin, gbc);

        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            if (password.equals(confirmPassword)) {
                UserAccount user = new UserAccount(username, password);
                if (!user.doesUserExist()) {
                    user.createUserAccount();
                    JOptionPane.showMessageDialog(mainFrame, "Registration successful. Please login.");
                    mainFrame.showCard("Login");
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Username already exists.");
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Passwords do not match.");
            }
        });

        btnBackToLogin.addActionListener(e -> mainFrame.showCard("Login"));
    }
}

class UserProfilePanel extends JPanel {
    public JTextArea txtUserInfo;

    public UserProfilePanel(FlightManagementSystem mainFrame) {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUserInfo = new JLabel("User Information:");
        txtUserInfo = new JTextArea(10, 30);
        txtUserInfo.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtUserInfo);

        JLabel lblUpdateProfile = new JLabel("Update Profile:");
        JButton btnChangePassword = new JButton("Change Password");
        JButton btnUpdatePreferences = new JButton("Update Preferences");
        JButton btnLogout = new JButton("Logout");
        JButton btnHome = new JButton("Home");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblUserInfo, gbc);

        gbc.gridy = 1;
        add(scrollPane, gbc);

        gbc.gridy = 2;
        add(lblUpdateProfile, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(btnChangePassword, gbc);
        gbc.gridx = 1;
        add(btnUpdatePreferences, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(btnHome, gbc);
        gbc.gridx = 1;
        add(btnLogout, gbc);

        btnChangePassword.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog(mainFrame, "Enter new password:");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                mainFrame.currentUser.setPassword(newPassword);
                UserAccount userAccount = new UserAccount(mainFrame.currentUser.getUsername(), newPassword);
                userAccount.createUserAccount(); // This should update the user's password in the file
                JOptionPane.showMessageDialog(mainFrame, "Password changed successfully.");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Password cannot be empty.");
            }
        });

        btnUpdatePreferences.addActionListener(e -> {
            String newPreferences = JOptionPane.showInputDialog(mainFrame, "Enter new communication preferences:");
            if (newPreferences != null && !newPreferences.trim().isEmpty()) {
                mainFrame.currentUser.setPreferences(newPreferences);
                JOptionPane.showMessageDialog(mainFrame, "Preferences updated successfully.");
                updateUserInfo(mainFrame);
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Preferences cannot be empty.");
            }
        });

        btnLogout.addActionListener(e -> mainFrame.showCard("Login"));
        btnHome.addActionListener(e -> mainFrame.showCard("Login"));
    }

    public void updateUserInfo(FlightManagementSystem mainFrame) {
        txtUserInfo.setText("Username: " + mainFrame.currentUser.getUsername() +
                "\nEmail: " + mainFrame.currentUser.getEmail() +
                "\nPast Bookings: " + mainFrame.currentUser.getPastBookings() +
                "\nUpcoming Flights: " + mainFrame.currentUser.getUpcomingFlights() +
                "\nPreferences: " + mainFrame.currentUser.getPreferences());
    }
}

class User {
    private String username;
    private String password;
    private String email;
    private String pastBookings;
    private String upcomingFlights;
    private String preferences;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = username + "@example.com";
        this.pastBookings = "Flight A, Flight B";
        this.upcomingFlights = "Flight C";
        this.preferences = "Default preferences";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPastBookings() {
        return pastBookings;
    }

    public String getUpcomingFlights() {
        return upcomingFlights;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}