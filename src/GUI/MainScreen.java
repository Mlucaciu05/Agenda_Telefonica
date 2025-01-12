package GUI;

import main.*;

import javax.swing.*;

public class MainScreen extends JFrame {
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JList<Contact> list1;
    private JButton viewContactButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton addButton;
    private JComboBox<Grup> comboBox1;
    private JButton adminOperationsButton;
    private JButton adaugaUnGrupButton;
    private JButton stergeGrupulSelectatButton;
    private JTextField textField1;

    public MainScreen(UserManager userManager) {
        User currentUser = userManager.getCurrentUser();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Agenda Telefonica");
        setSize(960, 600);
        setLocationRelativeTo(null);

        setContentPane(mainPanel);
        setVisible(true);

        usernameLabel.setText(currentUser.toString());

        DefaultListModel<Contact> model = new DefaultListModel<>();
        updateContactList(model, currentUser);

        updateGroupComboBox(comboBox1,currentUser);

        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setVisible(true);

        adminOperationsButton.setVisible(currentUser.isAdmin());

        addButton.addActionListener(e -> {
            String nume = JOptionPane.showInputDialog(MainScreen.this,"Introduceti numele:","Adauga contact",JOptionPane.PLAIN_MESSAGE);
            if (nume != null) {
                String prenume = JOptionPane.showInputDialog(MainScreen.this,"Introduceti prenumele:","Adauga contact",JOptionPane.PLAIN_MESSAGE);
                if (prenume != null) {
                    String nrTel = JOptionPane.showInputDialog(MainScreen.this,"Introduceti numerul de telefon:","Adauga contact",JOptionPane.PLAIN_MESSAGE);
                    if (nrTel != null) {
                        String email = JOptionPane.showInputDialog(MainScreen.this,"Introduceti email:","Adauga contact",JOptionPane.PLAIN_MESSAGE);
                        if (email != null) {
                            currentUser.getAgenda().addContact(nume, prenume, nrTel, email, currentUser.getId());
                        }
                    }
                }
            }
            updateContactList(model, currentUser);
        });

        deleteButton.addActionListener(e -> {
            currentUser.getAgenda().removeContact(list1.getSelectedValue());
            updateContactList(model, currentUser);
        });

        comboBox1.addActionListener(e -> {
            Grup selectedGrup = (Grup) comboBox1.getSelectedItem();
            if(selectedGrup != null) {
                if(selectedGrup.getNumeGrup().equals("Toate grupurile"))
                    updateContactList(model, currentUser);
                else
                    updateContactList(model, currentUser, selectedGrup.getNumeGrup());
            }
        });

        stergeGrupulSelectatButton.addActionListener(e -> {
            Grup selectedGrup = (Grup) comboBox1.getSelectedItem();
            if (selectedGrup != null && !selectedGrup.getNumeGrup().equals("Toate grupurile") && !selectedGrup.getNumeGrup().equals("Favorite")) {
                currentUser.getAgenda().removeGrup(selectedGrup);
            }
            updateGroupComboBox(comboBox1,currentUser);
        });

        adaugaUnGrupButton.addActionListener(e -> {

            String groupName = JOptionPane.showInputDialog(MainScreen.this, "Introduceti numele noului grup:", "Adauga Grup", JOptionPane.PLAIN_MESSAGE);

            if (groupName != null && !groupName.isEmpty()) {
                currentUser.getAgenda().addGrup(groupName);
                updateGroupComboBox(comboBox1, currentUser);

            }
        });

        searchButton.addActionListener(e -> {
            String nume = textField1.getText();
            model.clear();
            if (nume != null && !nume.isEmpty()) {
                for(Contact c : currentUser.getAgenda().search(nume)) {
                    model.addElement(c);
                }
            } else {
                for(Contact c : currentUser.getAgenda().getListaContacte())
                    model.addElement(c);
            }
            list1.setModel(model);
        });

        viewContactButton.addActionListener(e -> {
            Contact selectedContact = list1.getSelectedValue();
            for(Contact c : currentUser.getAgenda().getListaContacte())
                if(c.equals(selectedContact)) {
                    new ContactScreen(currentUser,c).setLocationRelativeTo(this);
                    break;
                }
        });

        adminOperationsButton.addActionListener(e -> {
            new AdminScreen(userManager).setLocationRelativeTo(this);
        });

    }

    public void updateContactList(DefaultListModel<Contact> model, User currentUser) {
        model.clear();

        for(Contact c : currentUser.getAgenda().getListaContacte())
            model.addElement(c);

        list1.setModel(model);
    }

    public void updateContactList(DefaultListModel<Contact> model, User currentUser, String grup) {
        model.clear();

        for(Contact c : currentUser.getAgenda().getListaContacte())
            for(Grup g : c.getGrupuri())
                if(g.getNumeGrup().equals(grup))
                    model.addElement(c);

        list1.setModel(model);
    }

    public void updateGroupComboBox(JComboBox<Grup> comboBox, User currentUser) {
        DefaultComboBoxModel<Grup> model = (DefaultComboBoxModel<Grup>) comboBox.getModel();
        model.removeAllElements();
        model.addElement(new Grup(0,"Toate grupurile"));

        for (Grup g : currentUser.getAgenda().getListaGrup()) {
            model.addElement(g);
        }
    }

}
