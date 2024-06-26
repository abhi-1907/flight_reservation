import booking.BookingFrame;
import help.HelpPageFrame;
import java.awt.*;
import javax.swing.*;
import loginsystem.FlightManagementSystem;
import search.FlightSearchFrame;

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

public class MainForm extends JFrame {

    private JButton homeButton;
    private JButton loginButton;
    private JButton bookingButton;
    private JButton searchButton;
    private JButton helpButton;
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JPanel detailsPanel;
    private JLabel welcomeLabel;
    private JLabel optionLabel;
    private final String[] imagePaths = {
        "C:\\Users\\abhij\\Desktop\\imageproject\\jsg.png",
        "C:\\Users\\abhij\\Desktop\\imageproject\\6.png",
        "C:\\Users\\abhij\\Desktop\\imageproject\\3.png",
        "C:\\Users\\abhij\\Desktop\\imageproject\\7.png",
        "C:\\Users\\abhij\\Desktop\\imageproject\\3.png",
        "C:\\Users\\abhij\\Desktop\\imageproject\\travel2.jpg",
        "C:\\Users\\abhij\\Desktop\\imageproject\\travel1.jpg",
        "C:\\Users\\abhij\\Desktop\\imageproject\\travel2.jpg"
    };
    private JLabel imageLabel;
    private int currentImageIndex = 0;
    private String backgroundImagePath = "C:\\Users\\abhij\\Desktop\\imageproject\\backimage.jpg";

    public MainForm() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents() {
        // Initialize components
        homeButton = new JButton("Home");
        loginButton = new JButton("Login");
        bookingButton = new JButton("Booking");
        searchButton = new JButton("Search");
        helpButton = new JButton("Help");
        welcomeLabel = new JLabel(new ImageIcon(scaleImage("C:\\Users\\abhij\\Desktop\\imageproject\\wel4.jpg", 800, 200))); // Resize welcome image
        optionLabel = new JLabel("Choose an option");
        imageLabel = new JLabel(new ImageIcon(imagePaths[currentImageIndex]));
        imagePanel = new JPanel();
        imagePanel.setOpaque(false); // Make image panel transparent
        imagePanel.setPreferredSize(new Dimension(800, 600));
        imagePanel.add(imageLabel);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        JScrollPane imageScrollPane = new JScrollPane(imagePanel);
        imageScrollPane.setPreferredSize(new Dimension(800, 600));
        imageScrollPane.setOpaque(false); // Make scroll pane transparent
        imageScrollPane.getViewport().setOpaque(false); // Make viewport transparent

        detailsPanel = new JPanel();
        detailsPanel.setOpaque(false); // Make details panel transparent
        detailsPanel.setLayout(new BorderLayout());

        String detailsText = "<html><body style='text-align: justify; font-size: 14pt; color: white; padding: 10px;'>"
                + "<h2 style='font-size: 18pt;'>The World Awaits: Unleash Your Wanderlust with JETSETGO!!</h2>"
                + "Do you dream of sinking your toes into the warm sand of a secluded beach paradise, or exploring the ancient wonders of a historic city steeped in culture? "
                + "Perhaps the thrill of a bustling metropolis or the awe-inspiring beauty of a natural landscape beckons you. Whatever your travel desires, JETSETGO is your key to unlocking a world of possibilities.<br><br>"
                + "We understand that planning a trip can be both exciting and daunting. That's why we've built a user-friendly platform that takes the stress out of finding the perfect flight. "
                + "With just a few clicks, you can search for flights to destinations across the globe, compare prices from top airlines, and book your journey with ease. We offer a wide range of options to fit your budget and travel style, "
                + "whether you're a seasoned explorer or embarking on your first adventure.<br><br>"
                + "But JETSETGO is more than just a booking tool. It's your gateway to unforgettable experiences. We curate travel inspiration and highlight hidden gems, helping you discover new places and plan your dream itinerary. "
                + "Imagine strolling through charming European villages, sampling exotic cuisine in bustling Asian markets, or witnessing breathtaking natural wonders. With JETSETGO, the world is your oyster.<br><br>"
                + "So, pack your bags, unleash your wanderlust, and let us help you take the first step towards your next unforgettable adventure. Search for flights today and get ready to explore!"
                + "</body></html>";

        JTextPane detailsTextPane = new JTextPane();
        detailsTextPane.setContentType("text/html");
        detailsTextPane.setText(detailsText);
        detailsTextPane.setEditable(false);
        detailsTextPane.setOpaque(false);

        JScrollPane detailsScrollPane = new JScrollPane(detailsTextPane);
        detailsScrollPane.setOpaque(false); // Make scroll pane transparent
        detailsScrollPane.getViewport().setOpaque(false); // Make viewport transparent
        detailsPanel.add(detailsScrollPane, BorderLayout.CENTER);
        detailsPanel.setPreferredSize(new Dimension(500, 600));

        // Set default close operation
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set button action listeners
        homeButton.addActionListener(evt -> showHome());
        loginButton.addActionListener(evt -> showLogin());
        bookingButton.addActionListener(evt -> showBooking());
        searchButton.addActionListener(evt -> showSearch());
        helpButton.addActionListener(evt -> showHelp());

        // Create and add next button for image carousel
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(evt -> nextImage());

        // Start image carousel timer
        Timer timer = new Timer(2000, e -> nextImage());
        timer.start();

        // Configure layout
        mainPanel = new BackgroundPanel(backgroundImagePath);
        mainPanel.setLayout(new BorderLayout());

        // Add welcome label at the top
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Add buttons below the welcome label
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(homeButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(bookingButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(helpButton);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Create a bottom panel to hold the details panel and the image panel side by side
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BorderLayout());

        // Add details panel to the left
        bottomPanel.add(detailsPanel, BorderLayout.WEST);

        // Add image panel to the right
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(imageScrollPane, BorderLayout.CENTER);
        rightPanel.add(nextButton, BorderLayout.SOUTH);
        bottomPanel.add(rightPanel, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
        pack();
        updateImage();
    }

    private Image scaleImage(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    private void showHome() {
        // Implement home button functionality
    }

    private void showLogin() {
        FlightManagementSystem frame = new FlightManagementSystem();
        frame.setVisible(true);
    }

    private void showBooking() {
        BookingFrame frame = new BookingFrame();
        frame.setVisible(true);
    }

    private void showSearch() {
        FlightSearchFrame frame = new FlightSearchFrame();
        frame.setVisible(true);
    }

    private void showHelp() {
        HelpPageFrame frame = new HelpPageFrame();
        frame.setVisible(true);
    }

    private void nextImage() {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        updateImage();
    }

    private void updateImage() {
        ImageIcon originalIcon = new ImageIcon(imagePaths[currentImageIndex]);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(resizedImage));
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainForm().setVisible(true));
    }
}