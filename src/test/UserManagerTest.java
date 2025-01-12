package test;

import main.Agenda;
import main.User;
import main.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    UserManager userManager = new UserManager();


    @Test
    void removeUser() {
        User user = new User(1,"test","test","USER" ,new Agenda(1));
        userManager.removeUser(user.getId());
        assertFalse(userManager.getUsers().contains(user));
    }

    @Test
    void login() {
        User user = new User(1,"test","test", "USER" ,new Agenda(1));
        assertTrue(userManager.login("test","test"));
    }

    @Test
    void register() {
        userManager.register("test","test");
        assertTrue(userManager.login("test","test"));
    }
}