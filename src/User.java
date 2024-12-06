public class User {
    String username;
    String password;
    Agenda agenda;

    public User(String nume, String password, Agenda agenda) {
        this.username = nume;
        this.password = password;
        this.agenda = agenda;
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

    public void setNewPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public void printData(){
        System.out.println(username);
        agenda.printContactList();
    }
}
