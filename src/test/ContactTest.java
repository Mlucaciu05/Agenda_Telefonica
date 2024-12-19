package test;

import static org.junit.jupiter.api.Assertions.*;

import main.Contact;
import org.junit.jupiter.api.Test;

class ContactTest {

    private Contact contact;

    @org.junit.jupiter.api.Test
    void addToGroup() {
        contact = new Contact("test","test","test","test");
        contact.addToGroup("test");
        assertTrue(contact.getGrupuri().contains("test"));
    }

    @org.junit.jupiter.api.Test
    void removeFromGroup() {
        contact = new Contact("test","test","test","test");
        contact.addToGroup("test");
        contact.removeFromGroup("test");
        assertFalse(contact.getGrupuri().contains("test"));
    }
}