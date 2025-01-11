package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Agenda {
    private static final String FILENAME = "agenda.txt";
    private ArrayList<Contact> listaContacte;
    private ArrayList<Grup> listaGrup;

    /**
     * Constructor pentru clasa main.Agenda; citeste contactele din fisierul agenda.txt si le adauga in lista de contacte; de asemenea initializeaza
     * grupul "Favorite"
     */
    public Agenda() {
        try {
            FileReader fr = new FileReader(FILENAME);
            LineNumberReader lnr = new LineNumberReader(fr);
            listaContacte = new ArrayList<>();

            listaGrup = new ArrayList<>();
            listaGrup.add(new Grup("Favorite"));

            while(lnr.ready()) {
                String line = lnr.readLine();
                String[] parts = line.split(",");
                listaContacte.add(new Contact(parts[0], parts[1], parts[2], parts[3]));
            }
            Collections.sort(listaContacte);

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
        Collections.sort(listaContacte);
        updateContacts();
    }

    public void removeContact(Contact contact) {
        listaContacte.remove(contact);
        Collections.sort(listaContacte);
        updateContacts();
    }

    public void addGrup(Grup grup) {
        listaGrup.add(grup);
    }

    public void removeGrup(Grup grup) {
        listaGrup.remove(grup);
        for(Contact c : listaContacte) {
            if(c.getGrupuri().contains(grup.getNumeGrup())) {
                c.removeFromGroup(grup.getNumeGrup());
            }
        }
    }

    /**
     * @param nume Numele contactului care este cautat
     *             Aceasta functie cauta un contact dupa nume. Numele introdus de utilizator este potrivit cu numele, prenumele sau doar o parte din acesta
     * @return lista de contacte care se potrivesc cu numele introdus de utilizator, sau null daca nu exista niciun contact care se potriveste
     */
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

    /**
     * @param nrTel Numarul de telefon al contactului care este cautat
     *              Aceasta metoda cauta un contact dupa un numar de telefon
     * @return Obiectul de tip main.Contact cu numarul de telefon introdus, sau null daca nu exista
     */
    public Contact searchContactNrTelefon(String nrTel) {
        for (Contact contact : getListaContacte()) {
            if (contact.getNumarTelefon().equals(nrTel)) {
                return contact;
            }
        }
        return null;
    }

    /**
     * @param email Emailul contactului care este cautat
     *              Aceasta metoda cauta un contact dupa o adresa de email introdusa de utilizator
     * @return Obiectul de tip main.Contact cu adresa de email introdusa, sau null daca nu exista
     */
    public Contact searchContactEmail(String email) {
        for (Contact contact : getListaContacte()) {
            if (contact.getEmail().equals(email)) {
                return contact;
            }
        }
        return null;
    }

    public ArrayList<Contact> search(String string){
        ArrayList<Contact> contactList = new ArrayList<>();

        if(searchContactNume(string)!=null){
            contactList = searchContactNume(string);
            return contactList;

        } else if(searchContactNrTelefon(string)!=null){
            contactList.add(searchContactNrTelefon(string));
            return contactList;

        } else if(searchContactEmail(string)!=null){
            contactList.add(searchContactEmail(string));
            return contactList;
        }

        return null;
    }

    public void printContactList() {
        for (Contact contact : getListaContacte()) {
            //d not forget to sort this shit
            System.out.println(contact);
        }
    }

    /**
     * Metoda auxoiliara care actualizeaza fisierul agenda.txt
     */
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
