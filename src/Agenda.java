import javax.swing.*;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contact> listaContacte;
    private ArrayList<Grup> listaGrup;

    public Agenda() {}

    public ArrayList<Contact> getListaContacte() {
        return listaContacte;
    }

    public ArrayList<Grup> getListaGrup() {
        return listaGrup;
    }

    public void addContact(Contact contact) {
        listaContacte.add(contact);
    }

    public void removeContact(Contact contact) {
        listaContacte.remove(contact);
    }

    public void addGrup(Grup grup) {
        listaGrup.add(grup);
    }

    public void removeGrup(Grup grup) {
        listaGrup.remove(grup);
    }

    public ArrayList<Contact> searchContactNume(String nume) {
        ArrayList<Contact> contactList = new ArrayList<>();
        for (Contact contact : getListaContacte()) {
            if (contact.getNume().equals(nume)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }

    public ArrayList<Contact> searchContactNrTelefon(String nrTel) {
        ArrayList<Contact> contactList = new ArrayList<>();
        for (Contact contact : getListaContacte()) {
            if (contact.getNumarTelefon().equals(nrTel)) {
                contactList.add(contact);
            }
        }
        return contactList;
    }

    public ArrayList<Contact> searchContactGrup(String numeGrup) {
        for (Grup grup : getListaGrup()) {
            if (grup.getNumeGrup().equals(numeGrup)) {
                return grup.getContacte();
            }
        }
        return null;
    }

    public void printContactList() {
        for (Contact contact : getListaContacte()) {
            System.out.println(contact);
        }
    }
}
