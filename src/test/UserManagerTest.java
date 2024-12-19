package test;

import main.Agenda;
import main.User;
import main.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    UserManager userManager = new UserManager();

    @Test
    void addUser() {
        User user = new User("test","test", new Agenda());
        userManager.addUser(user);
        assertTrue(userManager.getUsers().contains(user));
    }

    @Test
    void removeUser() {
        User user = new User("test","test", new Agenda());
        userManager.removeUser(user);
        assertFalse(userManager.getUsers().contains(user));
    }

    @Test
    void searchUser() {
        User user = new User("test","test", new Agenda());
        userManager.addUser(user);
        assertEquals(userManager.searchUser("test"), user);
    }

    @Test
    void login() {
        User user = new User("test","test", new Agenda());
        userManager.addUser(user);
        assertTrue(userManager.login("test","test"));
    }

    @Test
    void register() {
        userManager.register("test","test");
        assertTrue(userManager.login("test","test"));
    }
}