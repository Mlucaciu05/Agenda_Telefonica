package test;

import static org.junit.jupiter.api.Assertions.*;

import main.Contact;

class ContactTest {

    private Contact contact;

    @org.junit.jupiter.api.Test
    void addToGroup() {
        contact = new Contact(1, "test", "test", "test", "test");
        contact.addToGroup(1);
        assertTrue(contact.getGrupuri().contains("test"));
    }

    @org.junit.jupiter.api.Test
    void removeFromGroup() {
        contact = new Contact(1, "test", "test", "test", "test");
        contact.addToGroup(1);
        contact.removeFromGroup(1);
        assertFalse(contact.getGrupuri().contains("test"));
    }
}