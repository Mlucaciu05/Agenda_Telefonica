package main;

import java.sql.*;

public class User {

    private int id;
    private String username;
    private String password;
    private Agenda agenda;
    private String role;

    /**
     * @param nume Username-ul
     * @param password Parola
     * @param agenda Obiect de tip main.Agenda, unde sunt stocate contactele
     *               Constructor pentru clasa main.User
     */
    public User(int id, String nume, String password, String role, Agenda agenda) {
        this.id = id;
        this.username = nume;
        this.password = password;
        this.agenda = agenda;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setRole(String role) {
        this.role = role;

        String sql = "UPDATE users SET role = ? WHERE user_id = ?";

        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, role);
            statement.setInt(2, id);
            statement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean isAdmin() {
        return role.equals("ADMIN");
    }

    @Override
    public String toString() {
        return username + " " + role;
    }
}
