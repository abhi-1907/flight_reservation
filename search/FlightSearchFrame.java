package search;

import java.awt.*;
import javax.swing.*;
import search.searchbackend.FlightSearch;

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

public class FlightSearchFrame extends JFrame {

    public FlightSearchFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Flight Search");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("C:\\Users\\abhij\\Desktop\\imageproject\\backimage.jpg");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblDepartureCity = new JLabel("Departure City:");
        JTextField txtDepartureCity = new JTextField(20);
        JLabel lblArrivalCity = new JLabel("Arrival City:");
        JTextField txtArrivalCity = new JTextField(20);
        JLabel lblTravelDate = new JLabel("Travel Date (YYYY-MM-DD):");
        JTextField txtTravelDate = new JTextField(10);
        JButton btnSearchFlights = new JButton("Search Flights");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblDepartureCity, gbc);
        gbc.gridx = 1;
        panel.add(txtDepartureCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblArrivalCity, gbc);
        gbc.gridx = 1;
        panel.add(txtArrivalCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblTravelDate, gbc);
        gbc.gridx = 1;
        panel.add(txtTravelDate, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnSearchFlights, gbc);

        btnSearchFlights.addActionListener(e -> {
            String departureCity = txtDepartureCity.getText();
            String arrivalCity = txtArrivalCity.getText();
            String travelDate = txtTravelDate.getText();
            String flights;
            FlightSearch ob = new FlightSearch();

            if (departureCity.isEmpty() || arrivalCity.isEmpty() || travelDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all search details.");
            } else {
                flights = ob.searchFlights(departureCity, arrivalCity);
                showSearchResults(flights);
            }
        });

        add(panel);
    }

    private void showSearchResults(String flights) {
        JFrame resultsFrame = new JFrame("Search Results");
        resultsFrame.setSize(400, 300);
        resultsFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea txtResults = new JTextArea(10, 30);
        txtResults.setText(flights);

        JScrollPane scrollPane = new JScrollPane(txtResults);
        panel.add(scrollPane, BorderLayout.CENTER);

        resultsFrame.add(panel);
        resultsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightSearchFrame flightSearchFrame = new FlightSearchFrame();
            flightSearchFrame.setVisible(true);
        });
    }
}