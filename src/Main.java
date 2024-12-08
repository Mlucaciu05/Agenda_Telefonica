import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("Daca aveti un cont, va rugam introduceti 1. Daca doriti sa creati un cont, introduceti 0:");
            String n = scanner.nextLine();

            System.out.println("Username:");
            String username = scanner.nextLine();
            System.out.println("Password:");
            String password = scanner.nextLine();

            if (n.equals("0")) {
                userManager.register(username, password);
                System.out.println("Contul a fost creat, va rugam sa va logati");
            } else {
                if(userManager.login(username, password)) {
                    Meniu(userManager.getCurrentUser());
                    break;
                } else {
                    System.out.println("Username-ul sau parola sunt gresite, va rugam incercati din nou.");
                }
            }
        }

    }

    public static void Meniu(User currentUser) {
        String nume;
        Scanner scanner = new Scanner(System.in);
        Integer k=1;
        String n;

        while(true) {
            System.out.println("---Meniu---\n1.Vizualizati toate contactele\n2.Introduceti un contact nou\n3.Stergeti un contact" +
                    "\n4.Vizualizati toate grupurile si contactele din ele\n5.Adaugati un contact intr-un grup" +
                    "\n6.Stergeti un contact dintr-un grup\n7.Adaugati un grup nou\n8.Stergeti un grup\n" +
                    "9.Cauta un contact\n0.Iesire");

            if(currentUser.isAdmin()) {
                System.out.println("ADMIN. Vizualizati operatiile de admin");
            }

            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    System.out.println("Iesire...");
                    break;
                case "1":
                    currentUser.getAgenda().printContactList();
                    break;

                case "2":
                    System.out.println("Introduceti un contact nou:");
                    System.out.println("Nume:");
                    nume = scanner.nextLine();
                    System.out.println("Prenume:");
                    String prenume = scanner.nextLine();
                    System.out.println("Nr. Telefon:");
                    String telefon = scanner.nextLine();
                    System.out.println("Email:");
                    String email = scanner.nextLine();
                    currentUser.getAgenda().addContact(new Contact(nume, prenume, email, telefon));
                    break;

                case "3":
                    System.out.println("Introduceti numele contactului pe care doriti sa il stergeti:");
                    nume = scanner.nextLine();

                    ArrayList<Contact> contacteGasite = currentUser.getAgenda().searchContactNume(nume);

                    System.out.println("Selectati contactul pe care doriti sa il stergeti:");
                    for (int i = 0; i < contacteGasite.size(); i++) {
                        System.out.println(i+1 + "." + contacteGasite.get(i).toString());
                    }
                    n = scanner.nextLine();
                    currentUser.getAgenda().removeContact(contacteGasite.get(Integer.parseInt(n) - 1));
                    break;

                case "4":
                    k=1;
                    for(Grup grup : currentUser.getAgenda().getListaGrup()){
                        System.out.println(k+"."+grup.getNumeGrup());
                        grup.printContacts();
                        k++;
                    }
                    break;

                case "5":
                    System.out.println("Selectati grupul:");
                    k=1;
                    for (Grup grup : currentUser.getAgenda().getListaGrup()){
                        System.out.println(k+"."+grup.getNumeGrup());
                        k++;
                    }
                    n = scanner.nextLine();
                    Grup selectedGroup = currentUser.getAgenda().getListaGrup().get(Integer.parseInt(n) - 1);

                    System.out.println("Selectati contactul:");
                    for(k=0; k< currentUser.getAgenda().getListaContacte().size(); k++){
                        System.out.println(k+"."+currentUser.getAgenda().getListaContacte().get(k).toString());
                    }
                    n = scanner.nextLine();
                    selectedGroup.addContact(currentUser.getAgenda().getListaContacte().get(Integer.parseInt(n)));
                    break;

                case "6":
                    System.out.println("Selectati grupul:");
                    k=1;
                    for (Grup grup : currentUser.getAgenda().getListaGrup()){
                        System.out.println(k+"."+grup.getNumeGrup());
                        k++;
                    }
                    n = scanner.nextLine();
                    selectedGroup = currentUser.getAgenda().getListaGrup().get(Integer.parseInt(n) - 1);

                    System.out.println("Selectati contactul:");
                    for(k=0; k< selectedGroup.getContacte().size(); k++){
                        System.out.println(k+"."+selectedGroup.getContacte().get(k).toString());
                    }
                    n = scanner.nextLine();
                    Contact selectedContact = currentUser.getAgenda().getListaContacte().get(Integer.parseInt(n));
                    selectedGroup.removeContact(selectedContact);
                    break;

                case "7":
                    System.out.println("Numele grupului:");
                    nume = scanner.nextLine();
                    currentUser.getAgenda().addGrup(new Grup(nume));
                    break;

                case "8":
                    System.out.println("Selectati grupul:");

                    for(k=0; k< currentUser.getAgenda().getListaGrup().size(); k++){
                        System.out.println(k+"."+currentUser.getAgenda().getListaGrup().get(k).getNumeGrup());
                    }
                    n = scanner.nextLine();
                    currentUser.getAgenda().removeGrup(currentUser.getAgenda().getListaGrup().get(Integer.parseInt(n)));
                    break;

                case "9":
                    System.out.println("Introduceti numele/ numarul de telefon/ email-ul:");
                    String input = scanner.nextLine();

                    if(currentUser.getAgenda().searchContactNume(input) != null)
                        for(Contact contact : currentUser.getAgenda().searchContactNume(input)){
                            System.out.println(contact);
                        }
                    else if(currentUser.getAgenda().searchContactNrTelefon(input) != null)
                        System.out.println(currentUser.getAgenda().searchContactNrTelefon(input));
                    else if(currentUser.getAgenda().searchContactEmail(input) != null)
                        System.out.println(currentUser.getAgenda().searchContactEmail(input));
                    break;

                case "ADMIN":
                    MeniuAdmin();
                    break;

                default:
                    System.out.println("Introduceti una dintre optiuni!");
                    break;
            }
            if(option.equals("0")) {
                break;
            }
        }
    }

    public static void MeniuAdmin() {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            String username;
            System.out.println("---Meniu Admin---\n1.Vizualizati toti userii\n2.Adauga un user\n3.Stergeti un user\n4.Schimba privilegiile unui user\n0.Inapoi");
            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    System.out.println("Intoarcere...");
                    break;

                case "1":
                    userManager.printUsers();
                    break;

                case "2":
                    System.out.println("Username:");
                    username = scanner.nextLine();
                    System.out.println("Password:");
                    String password = scanner.nextLine();
                    userManager.addUser(new User(username, password, new Agenda()));
                    break;

                case "3":
                    System.out.println("Introduceti username-ul:");
                    username = scanner.nextLine();

                    if(username.equals("admin")) {
                        System.out.println("Acest user nu poate fi sters");
                        break;
                    }

                    userManager.removeUser(userManager.searchUser(username));
                    break;

                case "4":
                    System.out.println("Introduceti username-ul:");
                    username = scanner.nextLine();
                    User foundUser = userManager.searchUser(username);

                    if(username.equals("admin")) {
                        System.out.println("Nu poti schimba privilegiile acestui user");
                        break;
                    }

                    if(foundUser.isAdmin()) {
                        foundUser.setRole("USER");
                    } else {
                        foundUser.setRole("ADMIN");
                    }
                    break;

                default:
                    System.out.println("Introduceti una dintre optiuni!");
                    break;
            }
            if(option.equals("0")) {
                break;
            }
        }
    }

}