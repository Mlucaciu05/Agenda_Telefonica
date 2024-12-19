package test;

import main.Agenda;
import main.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @Test
    void setRole() {
        user = new User("test","test",new Agenda());
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void isAdmin() {
        user = new User("test","test",new Agenda());
        user.setRole("ADMIN");
        assertTrue(user.isAdmin());
    }
}