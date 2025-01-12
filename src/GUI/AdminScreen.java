package GUI;

import main.User;
import main.UserManager;

import javax.swing.*;

public class AdminScreen extends JFrame {
    private JPanel AdminScreen;
    private JList<User> list1;
    private JButton deleteButton;
    private JButton changePrivilegeButton;
    private JLabel usernameLabel;
    private JTextField textField1;
    private JButton searchButton;

    public AdminScreen(UserManager userManager) {
        setTitle("Admin Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480,300);
        setContentPane(AdminScreen);
        setVisible(true);

        usernameLabel.setText(userManager.getCurrentUser().getUsername());

        DefaultListModel<User> model = new DefaultListModel<>();
        updateUsersList(model, userManager);

        deleteButton.addActionListener(e -> {
            User selectedUser = list1.getSelectedValue();
            if (selectedUser != null) {
                userManager.removeUser(selectedUser.getId());
            }
            updateUsersList(model, userManager);
        });

        changePrivilegeButton.addActionListener(e -> {
            User selectedUser = list1.getSelectedValue();
            if (selectedUser != null) {
                if(selectedUser.isAdmin())
                    selectedUser.setRole("USER");
                else
                    selectedUser.setRole("ADMIN");
            }
            updateUsersList(model, userManager);
        });

        searchButton.addActionListener(e -> {
            String username = textField1.getText();
            model.clear();

            if(username != null && !username.isEmpty()) {
                for (User u : userManager.getUsers()) {
                    if (u.getUsername().equals(username)) {
                        model.addElement(u);
                    }
                }
                list1.setModel(model);
            } else updateUsersList(model, userManager);
        });

    }

    public void updateUsersList(DefaultListModel<User> model, UserManager userManager) {
        model.clear();
        for(User u : userManager.getUsers()) {
            model.addElement(u);
        }
        list1.setModel(model);
    }
}
