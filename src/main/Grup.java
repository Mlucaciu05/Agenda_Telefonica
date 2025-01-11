package main;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grup grup = (Grup) o;
        return Objects.equals(numeGrup, grup.numeGrup);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeGrup);
    }

    public String toString() {
        return numeGrup;
    }
}
