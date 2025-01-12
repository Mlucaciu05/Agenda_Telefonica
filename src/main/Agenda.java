package main;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class Agenda {

    private int userId;
    private ArrayList<Contact> listaContacte;
    private ArrayList<Grup> listaGrup;

    /**
     * Constructor pentru clasa main.Agenda; citeste contactele din fisierul agenda.txt si le adauga in lista de contacte; de asemenea initializeaza
     * grupul "Favorite"
     */
    public Agenda(int userId) {
        this.userId = userId;
        listaContacte = new ArrayList<>();
        listaGrup = new ArrayList<>();

        updateListaContacte();
        updateListaGrup();
    }

    public ArrayList<Contact> getListaContacte() {
        return listaContacte;
    }

    public ArrayList<Grup> getListaGrup() {
        return listaGrup;
    }

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacte (contact_id, nume, prenume, nr_tel, email, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contact.getId());
            statement.setString(2, contact.getNume());
            statement.setString(3, contact.getPrenume());
            statement.setString(4, contact.getNumarTelefon());
            statement.setString(5, contact.getEmail());
            statement.setInt(6, userId);
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        listaContacte.add(contact);
        Collections.sort(listaContacte);
    }

    public void addContact(String nume, String prenume, String numarTelefon, String email, int userId) {
        String sql = "INSERT INTO contacte (contact_id, nume, prenume, nr_tel, email, user_id) " +
                "VALUES (contacte_seq.NEXTVAL, ?, ?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nume);
            statement.setString(2, prenume);
            statement.setString(3, numarTelefon);
            statement.setString(4, email);
            statement.setInt(5, userId);
            statement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }

        updateListaContacte();
        Collections.sort(listaContacte);
    }

    public void removeContact(Contact contact) {

        String sql = "DELETE FROM contacte WHERE contact_id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, contact.getId());
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

        updateListaContacte();
        Collections.sort(listaContacte);
    }

    public void addGrup(Grup grup) {
        String sql = "INSERT INTO grupuri (grup_id, user_id, nume) VALUES (?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grup.getId());
            statement.setInt(2, userId);
            statement.setString(3, grup.getNumeGrup());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        updateListaGrup();
    }

    public void addGrup(String nume) {
        String sql = "INSERT INTO grupuri (grup_id, user_id, nume) VALUES (grup_seq.NEXTVAL, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setString(2, nume);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        updateListaGrup();
    }

    public void removeGrup(Grup grup) {
        String sql = "DELETE FROM grupuri WHERE grup_id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, grup.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        listaGrup.remove(grup);
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

    public void updateListaContacte(){
        listaContacte.clear();

        String sql = "SELECT * FROM contacte WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("contact_id");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String tel = rs.getString("nr_tel");
                String email = rs.getString("email");

                listaContacte.add(new Contact(id, nume, prenume, tel, email));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateListaGrup(){
        listaGrup.clear();

        String sql2 = "SELECT * FROM grupuri WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement2 = connection.prepareStatement(sql2)){

            statement2.setInt(1,userId);
            ResultSet rs2 = statement2.executeQuery();

            while(rs2.next()) {
                int id = rs2.getInt("grup_id");
                String nume = rs2.getString("nume");

                listaGrup.add(new Grup(id, nume));
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
