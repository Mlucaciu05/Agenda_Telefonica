import java.util.ArrayList;

public class UserManager {
    ArrayList<User> users;

    public UserManager() {}

    public void addUser(User user){
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public User searchUser(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public User login(String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

}
