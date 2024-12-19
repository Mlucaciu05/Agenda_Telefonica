package main;

import java.util.ArrayList;

public class Grup {
    private String numeGrup;
    private ArrayList<Contact> contacte;

    public Grup(String numeGrup) {
        this.numeGrup = numeGrup;
        contacte = new ArrayList<>();
    }

    public String getNumeGrup() {
        return numeGrup;
    }

    public void setNumeGrup(String numeGrup) {
        this.numeGrup = numeGrup;
    }

    public ArrayList<Contact> getContacte() {
        return contacte;
    }

    public void addContact(Contact contact) {
        contacte.add(contact);
        contact.addToGroup(getNumeGrup());
    }

    public void removeContact(Contact contact) {
        contacte.remove(contact);
        contact.removeFromGroup(getNumeGrup());
    }

    public void printContacts() {
        for (Contact contact : getContacte()) {
            System.out.println("\t" + contact);
        }
    }
}
