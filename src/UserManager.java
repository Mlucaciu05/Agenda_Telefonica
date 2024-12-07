import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class UserManager {
    private static final String FILENAME = "users.txt";
    private static User currentUser = null;
    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
        try {
            FileReader fr = new FileReader(FILENAME);
            LineNumberReader lnr = new LineNumberReader(fr);

            while (lnr.ready()) {
                String line = lnr.readLine();
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1], new Agenda()));
            }

            lnr.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addUser(User user){
        users.add(user);
        updateUserList();
    }

    public void removeUser(User user){
        if (currentUser.isAdmin()){
            users.remove(user);
            updateUserList();
        }
    }

    public User searchUser(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean login(String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password){
        try {
            FileWriter fw = new FileWriter(FILENAME, true);
            System.out.println(username + " " + password);
            fw.write(username + "," + password + "\n");

            fw.close();

            addUser(new User(username, password, new Agenda()));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printUsers(){
        for(User user : users){
            System.out.println(user);
        }
    }

    public void updateUserList(){
        try{
            FileWriter fw = new FileWriter(FILENAME);
            for(User user : users){
                fw.write(user.getUsername() + "," + user.getPassword() + "\n");
            }
            fw.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
