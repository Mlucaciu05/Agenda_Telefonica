package main;

public class User {
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
    public User(String nume, String password, Agenda agenda) {
        this.username = nume;
        this.password = password;
        this.agenda = agenda;
        role = "USER";

        if(nume.equals("admin"))
            role = "ADMIN";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return role.equals("ADMIN");
    }

    /**
     * @param oldPassword Parola veche
     * @param newPassword Parola noua
     *                    Aceasta metoda schimba parola veche a unui user cu o parola noua (functionaloitatea nu este implementata deocamdata)
     */
    public void setNewPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public void printData(){
        System.out.println(username);
        agenda.printContactList();
    }

    @Override
    public String toString() {
        return username + " " + role;
    }
}
