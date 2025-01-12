package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Contact implements Comparable<Contact>{

    private final int id;
    private String nume;
    private String prenume;
    private String numarTelefon;
    private String email;

    /**
     * @param id
     * @param nume         Numele persoanei
     * @param prenume      Prenumele persoanei
     * @param numarTelefon Numarul de telefon
     * @param email        Email-ul
     */

    public Contact(int id, String nume, String prenume, String numarTelefon, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Grup> getGrupuri() {
        ArrayList<Grup> grupuri = new ArrayList<>();

        String sql = "SELECT g.nume, g.grup_id FROM grupuri g " +
                "JOIN contacte_grupuri cg ON g.grup_id = cg.grup_id " +
                "WHERE cg.contact_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nume = rs.getString("nume");
                int grupId = rs.getInt("grup_id");
                grupuri.add(new Grup(grupId,nume));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupuri;
    }

    public void addToGroup(int grupId) {
        String sql = "INSERT INTO contacte_grupuri (contact_id, grup_id) VALUES (?,?)";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.setInt(2, grupId);
            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeFromGroup(int grupId) {
        String sql = "DELETE FROM contacte_grupuri WHERE grup_id = ? AND contact_id = ?";

        try (Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1,grupId);
            stmt.setInt(2,id);
            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifcaContact(String nume, String prenume, String numarTelefon, String email) {
        String sql = "UPDATE contacte SET nume = ?, prenume = ?, nr_tel = ?, email = ? WHERE contact_id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setString(3, numarTelefon);
            statement.setString(4, email);
            statement.setInt(5, id);
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        this.nume = nume;
        this.prenume = prenume;
        this.numarTelefon = numarTelefon;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(nume, contact.nume) && Objects.equals(prenume, contact.prenume) && Objects.equals(numarTelefon, contact.numarTelefon) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, numarTelefon, email);
    }

    @Override
    public String toString() {
        return nume + " " + prenume + " " + numarTelefon;
    }

    @Override
    public int compareTo(Contact o) {
        return this.nume.compareTo(o.getNume());
    }
}
