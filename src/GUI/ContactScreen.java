package GUI;

import main.Contact;
import main.Grup;
import main.*;

import javax.swing.*;
import java.util.ArrayList;

public class ContactScreen extends JFrame {
    private JPanel ContactScreen;
    private JTextField numeTextField;
    private JTextField nrTelefonTextField;
    private JTextField emailTextField;
    private JButton modificaButton;
    private JList<Grup> list1;
    private JButton adaugaIntrUnGrupButton;
    private JButton scoateDintrUnGrupButton;

    public ContactScreen(User currentUser, Contact contact) {
        setTitle("Contact Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350,560);
        setVisible(true);
        setResizable(false);

        setContentPane(ContactScreen);

        numeTextField.setEditable(false);
        nrTelefonTextField.setEditable(false);
        emailTextField.setEditable(false);

        adaugaIntrUnGrupButton.setVisible(false);
        scoateDintrUnGrupButton.setVisible(false);

        numeTextField.setText(contact.getNume() + " " + contact.getPrenume());
        nrTelefonTextField.setText(contact.getNumarTelefon());
        emailTextField.setText(contact.getEmail());

        DefaultListModel<Grup> model = new DefaultListModel<>();
        updateGrupList(model, contact);
        list1.setModel(model);

        modificaButton.addActionListener(e -> {
            if(modificaButton.getText().equals("Modifica")) {
                modificaButton.setText("Salveaza");
                adaugaIntrUnGrupButton.setVisible(true);
                scoateDintrUnGrupButton.setVisible(true);

                numeTextField.setEditable(true);
                nrTelefonTextField.setEditable(true);
                emailTextField.setEditable(true);
            } else {
                String fullname = numeTextField.getText();
                String nume = fullname.trim().substring(0, fullname.trim().indexOf(" "));
                String prenume = fullname.trim().substring(fullname.trim().indexOf(" ") + 1);
                String telefon = nrTelefonTextField.getText();
                String email = emailTextField.getText();

                contact.modifcaContact(nume, prenume, telefon, email);
                //currentUser.getAgenda().updateContacts();
                dispose();

            }
        });

        adaugaIntrUnGrupButton.addActionListener(e -> {

            ArrayList<Grup> listData = currentUser.getAgenda().getListaGrup();

            DefaultListModel<Grup> listModel = new DefaultListModel<>();
            listModel.addAll(listData);

            Grup selectedGrup = (Grup) JOptionPane.showInputDialog(
                    this,
                    "Selecteaza un grup:",
                    "Selecteaza grup",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    listData.toArray(),
                    listData.get(0)
            );

            if (selectedGrup != null) {
                if(!contact.getGrupuri().contains(selectedGrup.getNumeGrup()))
                    contact.addToGroup(selectedGrup.getId());
                updateGrupList(model, contact);
            }

        });

        scoateDintrUnGrupButton.addActionListener(e -> {
            Grup selectedGrup = list1.getSelectedValue();
            if (selectedGrup != null) {
                contact.removeFromGroup(selectedGrup.getId());
                updateGrupList(model, contact);
            }
        });

    }

    public void updateGrupList(DefaultListModel<Grup> model, Contact contact) {
        model.clear();
        for(Grup g : contact.getGrupuri())
            model.addElement(g);
    }
}
