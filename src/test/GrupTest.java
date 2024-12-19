package test;

import main.Contact;
import org.junit.jupiter.api.Test;

import main.Grup;

import static org.junit.jupiter.api.Assertions.*;

class GrupTest {
    private Grup grup;

    @Test
    void addContact() {
        grup = new Grup("TestGrup");
        Contact contact = new Contact("nume","prenume","0","email");
        grup.addContact(contact);
        assertTrue(grup.getContacte().contains(contact));
    }

    @Test
    void removeContact() {
        grup = new Grup("TestGrup");
        Contact contact = new Contact("nume","prenume","0","email");
        grup.addContact(contact);
        grup.removeContact(contact);
        assertFalse(grup.getContacte().contains(contact));
    }
}