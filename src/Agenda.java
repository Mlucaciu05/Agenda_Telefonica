import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class Agenda {
    private static String FILENAME = "agenda.txt";
    private ArrayList<Contact> listaContacte;
    private ArrayList<Grup> listaGrup;
    private Grup contacteFavorite;

    public Agenda() {
        try {
            FileReader fr = new FileReader(FILENAME);
            LineNumberReader lnr = new LineNumberReader(fr);
            listaContacte = new ArrayList<>();

            while(lnr.ready()) {
                String line = lnr.readLine();
                String[] parts = line.split(",");
                listaContacte.add(new Contact(parts[0], parts[1], parts[2], parts[3]));
            }

            lnr.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> getListaContacte() {
        return listaContacte;
    }

    public ArrayList<Grup> getListaGrup() {
        return listaGrup;
    }

    public void addContact(Contact contact) {
        listaContacte.add(contact);
        updateContacts();
    }

    public void removeContact(Contact contact) {
        listaContacte.remove(contact);
        updateContacts();
    }

    public void addGrup(Grup grup) {
        listaGrup.add(grup);
    }

    public void removeGrup(Grup grup) {
        listaGrup.remove(grup);
    }

    public ArrayList<Contact> searchContactNume(String nume) {
        ArrayList<Contact> contactList = new ArrayList<>();
        nume = nume.toLowerCase();

        for (Contact contact : getListaContacte()) {
            String numeIntreg = contact.getNume() + " " + contact.getPrenume();
            numeIntreg = numeIntreg.toLowerCase();

            if (numeIntreg.contains(nume) || contact.getPrenume().toLowerCase().equals(nume) || contact.getNume().toLowerCase().equals(nume)) {
                contactList.add(contact);
            }
        }
        if(contactList.isEmpty()){
            return null;
        } else {
            return contactList;
        }
    }

    public ArrayList<Contact> searchContactNrTelefon(String nrTel) {
        ArrayList<Contact> contactList = new ArrayList<>();
        for (Contact contact : getListaContacte()) {
            if (contact.getNumarTelefon().equals(nrTel)) {
                contactList.add(contact);
            }
        }
        if(contactList.isEmpty()){
            return null;
        } else {
            return contactList;
        }
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

    public void updateContacts(){
        try {
            FileWriter fw = new FileWriter(FILENAME);
            for(Contact contact : getListaContacte()){
                fw.write(contact.getNume() + "," + contact.getPrenume() + "," + contact.getNumarTelefon() + "," + contact.getEmail() + "\n");
            }
            fw.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
