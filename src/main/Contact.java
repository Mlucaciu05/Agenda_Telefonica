package main;

import java.util.ArrayList;
import java.util.Objects;

public class Contact {

    private String nume;
    private String prenume;
    private String numarTelefon;
    private String email;
    private ArrayList<String> grupuri;

    /**
     *
     * @param nume Numele persoanei
     * @param prenume Prenumele persoanei
     * @param numarTelefon Numarul de telefon
     * @param email Email-ul
     */

    public Contact(String nume, String prenume, String numarTelefon, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.numarTelefon = numarTelefon;
        this.email = email;
        grupuri = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getGrupuri() {
        return grupuri;
    }

    public void addToGroup(String grup) {
        grupuri.add(grup);
    }

    public void removeFromGroup(String grup) {
        grupuri.remove(grup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(nume, contact.nume) && Objects.equals(prenume, contact.prenume) && Objects.equals(numarTelefon, contact.numarTelefon) && Objects.equals(email, contact.email) && Objects.equals(grupuri, contact.grupuri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, numarTelefon, email, grupuri);
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + numarTelefon + " " + email + " Grupuri: " + grupuri;
    }

}
