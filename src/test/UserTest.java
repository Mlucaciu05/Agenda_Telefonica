package test;

import main.Agenda;
import main.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @Test
    void setRole() {
        user = new User(1,"test","test","USER" ,new Agenda(1));
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void isAdmin() {
        user = new User(1,"test","test", "USER", new Agenda(1));
        user.setRole("ADMIN");
        assertTrue(user.isAdmin());
    }
}