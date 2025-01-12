package main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.sql.*;

/**
 * Clasa care administreaza utilizatori
 */
public class UserManager {

    private static User currentUser = null;
    private ArrayList<User> users;

    /**
     * Constructor pentru clasa
     * Citeste utilizatori din users.txt si initializeaza un obiect de tip main.Agenda pentru fiecare
     */
    public UserManager() {
        users = new ArrayList<>();

        updateUsersList();
    }

    public User findUser(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void removeUser(int id){
        String sql = "DELETE FROM users WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        users.remove(findUser(id));
    }

    /**
     * @param username Username-ul unui utilizator
     * @param password Parola pentru contul respectiv
     * @return true, daca username-ul si parola convin, false in caz contrar
     * Daca username-ul si parola sunt corecte, currentUser este setat la utilizatorul corespunzator
     */
    public boolean login(String username, String password){
        String sql = "SELECT user_id FROM users WHERE username = ? AND password = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("user_id");
                currentUser = findUser(id);
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param username Username-ul unui utilizator
     * @param password Parola contului
     *                 Metoda inregistreaza un utilizator nou si salveaza username-ul si parola in users.txt
     */
    public void register(String username, String password){
        String sql = "INSERT INTO users(user_id,username,password,role) VALUES(users_seq.NEXTVAL,?,?,?)";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, "USER");
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

        updateUsersList();

    }

    public void updateUsersList(){
        users.clear();

        String sql = "SELECT * FROM users";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                users.add(new User(id,username,password,role, new Agenda(id)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
