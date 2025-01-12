package main;

import java.util.ArrayList;
import java.util.Objects;
import java.sql.*;

public class Grup {
    private final int id;
    private String numeGrup;

    public Grup(int id, String numeGrup) {
        this.id = id;
        this.numeGrup = numeGrup;
    }

    public int getId(){
        return id;
    }

    public String getNumeGrup() {
        return numeGrup;
    }

    public void addContact(Contact contact) {
        contact.addToGroup(id);
    }

    public void removeContact(Contact contact) {
        contact.removeFromGroup(id);
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
