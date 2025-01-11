package GUI;

import javax.swing.*;
import main.*;


public class LoginScreen extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel LoginScreen;
    private JLabel loginErrMessage;
    private JLabel registerLabel;
    private int incercari = 0;

    public LoginScreen() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480,300);
        setLocationRelativeTo(null);
        setVisible(true);

        UserManager userManager = new UserManager();

        setContentPane(LoginScreen);

        loginErrMessage.setVisible(false);
        registerLabel.setVisible(false);

        loginButton.addActionListener(e -> {
            String username = textField1.getText();
            String password = passwordField1.getText();

            if (userManager.login(username, password)) {

                new MainScreen(userManager);
                dispose();
            } else {
                incercari++;
                loginErrMessage.setVisible(true);
            }
            if(incercari == 3){
                registerLabel.setVisible(true);
            }
        });

        registerButton.addActionListener(e -> {
            incercari = 0;
            String username = JOptionPane.showInputDialog(LoginScreen.this, "Introuceti un username:", "Register", JOptionPane.PLAIN_MESSAGE);

            if(username != null) {
                String password = JOptionPane.showInputDialog(LoginScreen.this, "Introduceti o parola:", "Register", JOptionPane.PLAIN_MESSAGE);

                if(password != null) {
                    userManager.register(username, password);
                }
            }
        });

    }

    public static void main(String[] args) {
        new LoginScreen();
    }

}
