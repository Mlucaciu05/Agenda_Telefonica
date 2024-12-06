import java.util.ArrayList;

public class Grup {
    private String numeGrup;
    private ArrayList<Contact> contacte;

    public Grup(String numeGrup) {
        this.numeGrup = numeGrup;
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
    }

    public void removeContact(Contact contact) {
        contacte.remove(contact);
    }

}
