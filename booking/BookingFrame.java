package booking;
import booking.flight.flightdata;
import java.awt.*;
import javax.swing.*;

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

public class BookingFrame extends JFrame {
    public BookingFrame() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Flight Booking");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        BackgroundPanel panel = new BackgroundPanel("C:\\Users\\abhij\\Desktop\\imageproject\\backimage.jpg");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        
        JLabel lblFlightSelection = createLabel("Select Flight:");
        String[] flights = flightdata.listAllFlights();
        
        JComboBox<String> cmbFlights = new JComboBox<>(flights);
        JLabel lblPassengerName = createLabel("Passenger Name:");
        JTextField txtPassengerName = new JTextField(20);
        JLabel lblPassengerEmail = createLabel("Passenger Email:");
        JTextField txtPassengerEmail = new JTextField(20);
        JButton btnProceedToPayment = new JButton("Proceed to Payment");
        JButton btnHome = new JButton("Home");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblFlightSelection, gbc);
        gbc.gridx = 1;
        panel.add(cmbFlights, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblPassengerName, gbc);
        gbc.gridx = 1;
        panel.add(txtPassengerName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPassengerEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtPassengerEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(btnProceedToPayment, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnHome, gbc);

        btnProceedToPayment.addActionListener(e -> {
            String flight = (String) cmbFlights.getSelectedItem();
            String passengerName = txtPassengerName.getText();
            String passengerEmail = txtPassengerEmail.getText();

            if (passengerName.isEmpty() || passengerEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all details.");
            } else {
                PaymentFrame paymentFrame = new PaymentFrame(flight, passengerName, passengerEmail);
                paymentFrame.setVisible(true);
                dispose();
            }
        });

        btnHome.addActionListener(e -> setVisible(false));

        add(panel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);  // Change the text color here
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingFrame bookingFrame = new BookingFrame();
            bookingFrame.setVisible(true);
        });
    }
}

class PaymentFrame extends JFrame {
    private String flight;
    private String passengerName;
    private String passengerEmail;

    public PaymentFrame(String flight, String passengerName, String passengerEmail) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.flight = flight;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;

        setTitle("Payment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblCardNumber = createLabel("Card Number:");
        JTextField txtCardNumber = new JTextField(20);
        JLabel lblCardExpiry = createLabel("Expiry Date (MM/YY):");
        JTextField txtCardExpiry = new JTextField(5);
        JLabel lblCardCVV = createLabel("CVV:");
        JTextField txtCardCVV = new JTextField(3);
        JButton btnPay = new JButton("Pay");
        JButton btnHome = new JButton("Home");
        JButton btnClose = new JButton("Close");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblCardNumber, gbc);
        gbc.gridx = 1;
        panel.add(txtCardNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblCardExpiry, gbc);
        gbc.gridx = 1;
        panel.add(txtCardExpiry, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblCardCVV, gbc);
        gbc.gridx = 1;
        panel.add(txtCardCVV, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnClose, gbc);

        gbc.gridx = 1;
        panel.add(btnPay, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(btnHome, gbc);

        btnPay.addActionListener(e -> {
            String cardNumber = txtCardNumber.getText();
            String cardExpiry = txtCardExpiry.getText();
            String cardCVV = txtCardCVV.getText();

            if (cardNumber.isEmpty() || cardExpiry.isEmpty() || cardCVV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all payment details.");
            } else {
                JOptionPane.showMessageDialog(this, "Payment successful. Booking confirmed for " + flight + ".");
                dispose();
            }
        });

        btnHome.addActionListener(e -> setVisible(false));
        btnClose.addActionListener(e -> {
            setVisible(false);
        });

        add(panel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);  // Change the text color here
        return label;
    }
}
