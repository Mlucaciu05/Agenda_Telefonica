public class User {
    private String username;
    private String password;
    private Agenda agenda;
    private String role;

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

    public boolean isAdmin() {
        return role.equals("ADMIN");
    }

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
