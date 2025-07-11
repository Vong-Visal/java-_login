import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class AuthSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField loginUsernameField, registerUsernameField;
    private JPasswordField loginPasswordField, registerPasswordField, confirmPasswordField;
    private final String DATA_DIR = "user_data";
    private final String USER_DATA_FILE = DATA_DIR + File.separator + "users.txt";
    private Map<String, String> users = new HashMap<>();

    public AuthSystem() {
        createDataDirectory();
        loadUserData();
        initializeGUI();
    }

    private void createDataDirectory() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            if (!Files.exists(Paths.get(USER_DATA_FILE))) {
                Files.createFile(Paths.get(USER_DATA_FILE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            users.put(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeGUI() {
        frame = new JFrame("Java Login/Register System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Login Panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        loginUsernameField = new JTextField();
        loginPasswordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton switchToRegister = new JButton("Create Account");

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(loginUsernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(loginPasswordField);
        loginPanel.add(loginButton);
        loginPanel.add(switchToRegister);

        // Register Panel
        JPanel registerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        registerUsernameField = new JTextField();
        registerPasswordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        JButton registerButton = new JButton("Register");
        JButton switchToLogin = new JButton("Back to Login");

        registerPanel.add(new JLabel("Username:"));
        registerPanel.add(registerUsernameField);
        registerPanel.add(new JLabel("Password:"));
        registerPanel.add(registerPasswordField);
        registerPanel.add(new JLabel("Confirm Password:"));
        registerPanel.add(confirmPasswordField);
        registerPanel.add(registerButton);
        registerPanel.add(switchToLogin);

        // Add cards
        cardPanel.add(loginPanel, "login");
        cardPanel.add(registerPanel, "register");

        // Button actions
        switchToRegister.addActionListener(e -> cardLayout.show(cardPanel, "register"));
        switchToLogin.addActionListener(e -> cardLayout.show(cardPanel, "login"));

        loginButton.addActionListener(e -> {
            String username = loginUsernameField.getText();
            String password = new String(loginPasswordField.getPassword());
            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        });

        registerButton.addActionListener(e -> {
            String username = registerUsernameField.getText();
            String password = new String(registerPasswordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.");
            } else if (users.containsKey(username)) {
                JOptionPane.showMessageDialog(frame, "User already exists.");
            } else {
                saveUser(username, password);
                JOptionPane.showMessageDialog(frame, "Registered successfully.");
                cardLayout.show(cardPanel, "login");
            }
        });

        frame.add(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
