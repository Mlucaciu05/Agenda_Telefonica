package test;

import main.Agenda;
import main.Contact;
import main.Grup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AgendaTest {
    Agenda agenda;

    @Test
    void addContact() {
        agenda = new Agenda(1);
        Contact contact = new Contact(1, "test", "test", "test", "test");
        agenda.addContact(contact);
        assertTrue(agenda.getListaContacte().contains(contact));
    }

    @Test
    void removeContact() {
        agenda = new Agenda(1);
        Contact contact = new Contact(1, "test", "test", "test", "test");
        agenda.addContact(contact);
        agenda.removeContact(contact);
        assertFalse(agenda.getListaContacte().contains(contact));
    }

    @Test
    void addGrup() {
        agenda = new Agenda(1);
        Grup grup = new Grup(1,"test");
        agenda.addGrup(grup);
        assertTrue(agenda.getListaGrup().contains(grup));
    }

    @Test
    void removeGrup() {
        agenda = new Agenda(1);
        Grup grup = new Grup(1,"test");
        agenda.addGrup(grup);
        agenda.removeGrup(grup);
        assertFalse(agenda.getListaGrup().contains(grup));
    }

    @Test
    void searchContactNume() {
        agenda = new Agenda(1);
        Contact contact = new Contact(1, "test", "test", "test", "test");
        agenda.addContact(contact);
        assertTrue(agenda.searchContactNume("test").contains(contact));
    }

    @Test
    void searchContactNrTelefon() {
        agenda = new Agenda(1);
        Contact contact = new Contact(1, "test", "test", "test", "test");
        agenda.addContact(contact);
        assertEquals(agenda.searchContactNrTelefon("test"), contact);
    }

    @Test
    void searchContactEmail() {
        agenda = new Agenda(1);
        Contact contact = new Contact(1, "test", "test", "test", "test");
        agenda.addContact(contact);
        assertEquals(agenda.searchContactEmail("test"), contact);
    }
}