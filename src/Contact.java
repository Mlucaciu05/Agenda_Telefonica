import java.util.ArrayList;

public class Contact {
    private String nume;
    private String prenume;
    private String numarTelefon;
    private String email;
    private ArrayList<String> grupuri;

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

    public void addToGroup(String grup) {
        grupuri.add(grup);
    }

    public void removeFromGroup(String grup) {
        grupuri.remove(grup);
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + numarTelefon + " " + email + " Grupuri: " + grupuri;
    }

}
